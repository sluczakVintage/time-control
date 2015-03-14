package com.nfc.timecontrol.cl;

import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.tech.MifareUltralight;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nfc.timecontrol.cl.gson.CRequestStatus;
import com.nfc.timecontrol.cl.gson.CResponseStatus;



/**
 * @author Sebastian
 *
 */
public class TagScanActivity extends Activity {
	
	/**
	 * GUI text view used to show status info
	 */
	private static TextView sUpperTitle;
	
	/**
	 * GUI text view used to event status info
	 */
	private static TextView sEventStatus;
	
    // NFC Handling fields
	/**
	 * NFC Adapter instance
	 */
	private NfcAdapter mNfcAdapter;
	
    /**
     * Pending intent
     */
    PendingIntent mPendingIntent;

	/**
	 * List of supported NFC techs
	 */
	private String[][] mTechLists;

	/**
	 * Intent filter used to filter out intents that application can't handle
	 */
	private IntentFilter[] mFilters;
    
	
	/**
     * Standard projection for events
     * 
     */
    private static final String[] PROJECTION = new String[] {
        EventsColumns._ID, // 0
        EventsColumns.USER, // 1
        EventsColumns.TAG_ID, // 2
        EventsColumns.TOKEN, // 3
        EventsColumns.COMMENT, // 4
        EventsColumns.CREATED_DATE, // 5
    };   
   
    private Uri mUri;

	private Cursor mCursor;

	private TextView sEventComment;
	
	private ImageView sStatusIcon;
	
	private TextView sEventTime;
    
    private Handler mHandler;
	
	/**
	 * 
	 */
	private MediaPlayer mMediaPlayer;
	
  	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);

        Log.d(getText(R.string.logTag).toString(), "onCreate()");
        
        sUpperTitle = (TextView)findViewById(R.id.upperTitle);
        sUpperTitle.setText(R.string.hello);
        sEventStatus = (TextView)findViewById(R.id.eventStatus);
        sEventStatus.setText(R.string.eventStatusRefreshing);
    }
    
    
    /**
     * Closes activity and service
     * @param view
     */
    public void onCloseClickHandler(View view)
    {
    	Log.d(getText(R.string.logTag).toString(), "onClose()");
    	finish();
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	mHandler = new Handler();
    	Log.d(getText(R.string.logTag).toString(), "onResume()");
        
        mMediaPlayer = new MediaPlayer();
    	
        UserPreferences.getInstance().getPreferences(this);  
              
        if( ( UserPreferences.getInstance().mUsername.equals("null") || UserPreferences.getInstance().mPasswordHash.equals("null") ) )
        {
			showLoginPrompt(this);
       		return;
        }
        else
        {
        	Intent intent = getIntent();
			
			//if it is tag scanning, start TagScanResolver
        	try {
				if(intent.getAction().equals(Intent.ACTION_MAIN))
				{				
					intent.setClass(this, TagScanActivity.class);
				}
				else
				{
					intent.setClass(this, TagScanResolverActivity.class);
					finish();
					startActivity(intent);
					return;
				}
        	} catch(NullPointerException e) {
				e.printStackTrace();
				intent = new Intent(Intent.ACTION_MAIN);
				intent.setClass(this, TagScanActivity.class);
				
			}
        }
		
        // get default NFC Adapter
    	mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

    	String type = "text/vnd.timecontrol";
    	// set the Intent filter 
    	IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        
        try {
            ndef.addDataType(type);
        } catch (MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mFilters = new IntentFilter[] {
                ndef,
        };
        
        // Setup a tech list for MifareUltraLight 
        mTechLists = new String[][] { new String[] { MifareUltralight.class.getName() } };
        
        /* SQLite */        
        mUri = EventsColumns.CONTENT_URI;
        
    	new SynchroniseTask().execute();
        
        sStatusIcon = (ImageView)findViewById(R.id.statusIcon);
    	sEventStatus = (TextView)findViewById(R.id.eventStatus);
    	sEventComment = (TextView)findViewById(R.id.eventComment);	
    	sEventTime = (TextView)findViewById(R.id.eventTime);

    	
    	Intent intent = new Intent(this, TagScanResolverActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

    	// prepare pending intent to handle
    	mPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);   	
    	
    	// Enable Foreground Dispatch
     	NfcAdapter.getDefaultAdapter(this).enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
    }
    
    @Override
    protected void onPause()
    {
    	Log.d(getText(R.string.logTag).toString(), "onPause()");
    	mMediaPlayer.release();
    	super.onPause();
    	if(NfcAdapter.getDefaultAdapter(this) != null)
    		NfcAdapter.getDefaultAdapter(this).disableForegroundDispatch(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
    	Log.d(getText(R.string.logTag).toString(), "onNewIntent()");
    	Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
    }
    
    @Override
	protected void onDestroy() {
    	mMediaPlayer.release();
		super.onDestroy();
	}


	private final void synchronizeEvent() {
    	
    	getContentResolver().delete(mUri, null, null);
        ContentValues values = new ContentValues();
        CRequestStatus cRequestStatus = new CRequestStatus(UserPreferences.getInstance().mUsername, 
        		UserPreferences.getInstance().mPasswordHash, 
        		UserPreferences.getInstance().mDeviceId, 
        		UserPreferences.getInstance().mSubscriberId);
        CResponseStatus cResponseStatus;
        try {
        	cResponseStatus = HTTPAgent.getInstance().eventStatusHandler(cRequestStatus);
        	if (cResponseStatus != null && cResponseStatus.getStatus().equals("EVENT_STATUS_STARTED"))
        	{
	        	values.put(EventsColumns.USER, UserPreferences.getInstance().mUsername);
	    		values.put(EventsColumns.TAG_ID, cResponseStatus.getTag_id());
	    		values.put(EventsColumns.TOKEN, cResponseStatus.getToken());
	    		values.put(EventsColumns.COMMENT, cResponseStatus.getComment());
	    		values.put(EventsColumns.STATE, 1);
	    		getContentResolver().insert(mUri, values);
	        	Log.i(getText(R.string.logTag).toString(), "Event inserted to database");
        	}
        	else if(cResponseStatus != null && cResponseStatus.getStatus().contains("ERROR"))
        	{
        		mHandler.post(new Runnable() {
				public void run() {
					showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusNoConnection), R.raw.wrong);
				}
        		});        		
        	}
        } catch (ConnectException e) {
    		mHandler.post(new Runnable() {
				public void run() {
					showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusNoConnection), R.raw.wrong);
				}
			});
			e.printStackTrace();    	
		} catch(NullPointerException e1) {
			mHandler.post(new Runnable() {
				public void run() {
					showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusError), R.raw.wrong);
				}
			});
			e1.printStackTrace();
		}   catch (ClientProtocolException e1) {
    		mHandler.post(new Runnable() {
				public void run() {
					showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusError), R.raw.wrong);
				}
			});
			e1.printStackTrace();
		} catch (IOException e1) {
			mHandler.post(new Runnable() {
				public void run() {
					showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusError), R.raw.wrong);
				}
			});
			e1.printStackTrace();
		}
		
		  try {
          	getContentResolver().insert(mUri, values);
          } catch (NullPointerException e) {
              Log.e(getText(R.string.logTag).toString(), e.getMessage());
          }
    }
    
    private void showDialog(String state, int resId) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

    	builder.setMessage(state)
    	       .setCancelable(false)
    	       .setPositiveButton(getText(R.string.ok).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {

    	           }
    	       });
    	AlertDialog alert = builder.create();
    	playLocalAudio(this,resId);
    	alert.show();
    }
    
    private void showLoginPrompt(final Context context) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

    	builder.setMessage(R.string.loginHeader)
    	       .setNegativeButton(getText(R.string.no).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   finish();
    	           }
    	       })
    	       .setPositiveButton(getText(R.string.yes).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	    Intent intent = getIntent();
    	   				intent.setClass(context, LoginActivity.class);
    	   				finish();
    	   				context.startActivity(intent);
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    /**
     * Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu from XML resource
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);     

        Intent intent = new Intent(TagScanActivity.this, PreferencesActivity.class);
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0,
                new ComponentName(this, PreferencesActivity.class), null, intent, 0, null);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menuOptions:
            // Launch activity to set the settings
            startActivity(new Intent(TagScanActivity.this, PreferencesActivity.class));
            return true;
        case R.id.menuLogout:
            // Launch activity to set the settings
            startActivity(new Intent(TagScanActivity.this, LoginActivity.class));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    

    private void playLocalAudio(Context context, int resId) 
    {
    	if(UserPreferences.getInstance().mVibrationOn)
    		((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(300);
    	if(UserPreferences.getInstance().mSoundOn)
    	{
    		mMediaPlayer = MediaPlayer.create(context, resId);
    		mMediaPlayer.start();
    	}
    }
    
    private class SynchroniseTask extends AsyncTask<Void, Void, Void> {
        
        // can use UI thread here
        protected void onPreExecute() {
        	
        }
        // automatically done on worker thread (separate from UI thread)
        protected Void doInBackground(Void... args) {
        	synchronizeEvent();
        	
        	mCursor = managedQuery(mUri, PROJECTION, EventsColumns.STATE + " == '" + 1 + "'", null, null);
         	
         	//Connect to database
         	if (mCursor != null && mCursor.getCount() > 1) {
         		Log.i(getText(R.string.logTag).toString(), "ACTIVE EVENT found in db (resume)");
         		mCursor.moveToFirst();
                // Make sure we are at the one and only row in the cursor.   
         		mHandler.post(new Runnable() {
					public void run() {
						sEventStatus.setText(R.string.eventStatusStarted);
						
						Date date = new Date(mCursor.getLong(EventsColumns.CREATED_DATE_INDEX));
						SimpleDateFormat format = new SimpleDateFormat(
	                    "HH:mm:ss '  Dnia: 'dd.MM.yy");
						
						sEventTime.setText("Pocz¹tek: "+ format.format(date));
						sStatusIcon.setImageResource(R.drawable.in_progress);
		         		sEventComment.setText(mCursor.getString(EventsColumns.COMMENT_INDEX));
					}
				});
         	}
         	else
         	{
         		Log.i(getText(R.string.logTag).toString(), "ACTIVE EVENT NOT found in db (resume)");
         		mHandler.post(new Runnable() {
					public void run() {
						sEventStatus.setText(R.string.eventStatusIdle);
						sStatusIcon.setImageResource(R.drawable.waiting);
						sEventTime.setText(R.string.statusNone);
		         		sEventComment.setText(R.string.statusNone);
					}
				});
         	}     	
        	return null;
        }
        // can use UI thread here
        protected void onPostExecute(final Void unused) {
        	
        }
     }
}


