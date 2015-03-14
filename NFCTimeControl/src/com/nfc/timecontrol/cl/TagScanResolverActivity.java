package com.nfc.timecontrol.cl;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Vibrator;
import android.text.format.Time;
import android.util.Log;

import com.nfc.timecontrol.cl.gson.CRequestRegistration;
import com.nfc.timecontrol.cl.gson.CResponse;

public class TagScanResolverActivity extends Activity {

    // NFC Handling fields
	/**
	 * NFC Adapter instance
	 */
	private NfcAdapter mNfcAdapter;
	
	private static Context mContext;
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
    
	private Handler mHandler;
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

	private MediaPlayer mMediaPlayer;
	
    private static final int COLUMN_INDEX_TOKEN = 3;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tagscanview);
        
        mHandler = new Handler();
        
        Log.d(getText(R.string.logTag).toString(), "onCreate()");

        UserPreferences.getInstance().getPreferences(this);
        
        mMediaPlayer = new MediaPlayer();
              
        /* SQLite */
        
        mUri = EventsColumns.CONTENT_URI;  
        
        // Handle intent
        Intent intent = getIntent();
        
        resolveIntent(intent);
    }

    
    @Override
    public void onResume()
    {
    	mContext = this;
    	super.onResume();
    }
    
    @Override
    public void onPause()
    {
    	mMediaPlayer.release();
    	super.onPause();
    }
    
    @Override
    public void onDestroy()
    {
    	mMediaPlayer.release();
    	super.onDestroy();
    }
	
	/**
     * Resolves current intent (get's NDEF Records from NDEF Message and registers tag activity on server via HTTPAgent
     * @param intent
     */
    void resolveIntent(Intent intent) {
        // Parse the intent 	
    	
    	if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
    		
    		// Token from sqlite db
    		String token;
    		
    		List<TextRecord> text;
    		// Get TAG ID
    		byte[] byte_id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
    		// Convert byte data to Hex string
    		String tag_id = getHex(byte_id);
    		Log.i("Foreground dispatch", "TagID: " + tag_id );
    		
    		// Get NDEF messages from tag intent
    		Parcelable[] msgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
    	        
    		NdefMessage[] nmsgs = new NdefMessage[msgs.length];
    		
    		// parse NDEF messages to readable format
    		for(int i=0;i<msgs.length;i++) {
    		   	nmsgs[i] = (NdefMessage) msgs[i];
    		   	text = parse(nmsgs[i]);
    		   	if(text.size() != 0)
    		   		Log.i("Foreground dispatch", text.get(0).getText() + " :: " + text.get(0).getLanguageCode());
    		}
    		// get current android time
    		Time now = new Time();
			now.setToNow();
			
			/* SQLite event query - is there an active event for this tag */
	        mCursor = managedQuery(mUri, PROJECTION, EventsColumns.TAG_ID + " == '" + tag_id + "' AND " + EventsColumns.STATE + " == '" + 1 + "'", null, null);
			//Connect to database
	     	if ( mCursor != null && mCursor.getCount() != 0 ) {
	     		Log.i(getText(R.string.logTag).toString(), "EVENT found in db");
	            // Make sure we are at the one and only row in the cursor.
	            mCursor.moveToFirst();            
                // Set the title of the Activity to include the note title
                token = mCursor.getString(COLUMN_INDEX_TOKEN);
	     	}
	     	else
	     	{
	     		token = "null";
	     	}			
			// create request
    		CRequestRegistration cRequestRegistration = new CRequestRegistration( UserPreferences.getInstance().mUsername, 
    				UserPreferences.getInstance().mPasswordHash, 
    				UserPreferences.getInstance().mDeviceId, 
    				UserPreferences.getInstance().mSubscriberId,
    				tag_id, token, now.toMillis(true));
	
			// send request via POST HTTP method

			try {
					if (cRequestRegistration.getToken().equals("null")) {
						CResponse cResponse;

						cResponse = new ResolveHttpTask().execute(cRequestRegistration).get();
						if(!cResponse.getStatus().contains("ERROR"))
				        {
							// Event start
				        	saveEvent(cRequestRegistration, cResponse);
				        	playLocalAudio(R.raw.ok);
				        	showDialog(getText(R.string.eventStatusJustStarted).toString(), cResponse);
				        }
						else if( cResponse.getStatus().equals("WRONG_PASSWORD_ERROR") || cResponse.getStatus().equals("USER_NOT_FOUND_ERROR"))
						{
							playLocalAudio(R.raw.wrong);
							showLoginDialog();
						}
						else if( cResponse.getStatus().equals("TAG_NOT_FOUND_ERROR"))
						{
							playLocalAudio(R.raw.wrong);
							showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusTagNotRecognized));
						}
						else if( cResponse.getStatus().equals("EVENT_ALREADY_STARTED_ERROR"))
						{
							playLocalAudio(R.raw.wrong);
							showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusAlreadyInProgress));
						}
						else
						{
							playLocalAudio(R.raw.wrong);
							showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusError));
						}
			        }
			        else
			        {
			        	// Event end
			        	showEndingPrompt(this, cRequestRegistration);
			        }
				
	    	} catch (NullPointerException e) 
	    	{
	    		playLocalAudio(R.raw.wrong);
				showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusError));
				e.printStackTrace();
	    	}
			catch (InterruptedException e) {
				playLocalAudio(R.raw.wrong);
				showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusError));
				e.printStackTrace();
			} catch (ExecutionException e) {
				playLocalAudio(R.raw.wrong);
				showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusError));
				e.printStackTrace();
			}
    	}
    	else
    	{
			playLocalAudio(R.raw.wrong);
			showDialog(getText(R.string.eventTagReadingError).toString());
    	}
    }

    
	/**
     * Parses NdefMessage to readable format
     * @param message
     * @return List of text records decapsulated from NdefMessage
     */
    private List<TextRecord> parse(NdefMessage message)
    {
                    List<TextRecord> textRecords = new ArrayList<TextRecord>();
                    
                    //Get the Records inside the message
                    NdefRecord[] records = message.getRecords();
                    
                    //Iterate through and generate a list of text records
                    if(records != null && records.length>0)
                    {
                            for(NdefRecord local: records)
                            {
                            	if(local.getPayload().length != 0)
                            	{
                                    TextRecord textRecord = TextRecord.parse(local);
                                    textRecords.add(textRecord);
                            	}
                            }
                    }
	
                    return textRecords;
    }
    
    private void showDialog(String state, CResponse cResponse) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	if (cResponse != null)
    		state = state + " :\n" + cResponse.getMessage();
    	builder.setMessage(state)
    	       .setCancelable(false)
    	       .setPositiveButton(getText(R.string.ok).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	           		finish();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    private void showDialog(String state) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

    	builder.setMessage(state)
    	       .setCancelable(false)
    	       .setPositiveButton(getText(R.string.ok).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	           		finish();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    private void showLoginDialog() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

    	builder.setMessage(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.statusDenied))
    	       .setCancelable(false)
    	       .setPositiveButton(getText(R.string.loginHeader), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	
    	        	   	Intent intent = getIntent();
    	   				intent.setClass(mContext, LoginActivity.class);
    	   				startActivity(intent);
    	   				
    	           		finish();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    private void showEndingPrompt(final Context context,final CRequestRegistration cRequestRegistration) {
    	ProgressDialog.Builder builder = new ProgressDialog.Builder(this);

    	
    	builder.setMessage(R.string.eventEndPrompt)
    	       .setNegativeButton(getText(R.string.no).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   finish();
    	           }
    	       })
    	       .setPositiveButton(getText(R.string.yes).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
				   new ResolveHttpEndingTask().execute(cRequestRegistration);
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    private final void saveEvent(CRequestRegistration cRequestRegistration, CResponse cResponse) {
    	
        ContentValues values = new ContentValues();

        values.put(EventsColumns.USER, cRequestRegistration.getUser_login());
        values.put(EventsColumns.TAG_ID, cRequestRegistration.getTag_id());
        values.put(EventsColumns.TOKEN, cResponse.getToken());
        values.put(EventsColumns.COMMENT, cResponse.getMessage());
        values.put(EventsColumns.STATE, 1);
        
        try {
        	getContentResolver().insert(mUri, values);
        } catch (NullPointerException e) {
            Log.e(getText(R.string.logTag).toString(), e.getMessage());
        }
        Log.i(getText(R.string.logTag).toString(), "Event inserted to database");
    }
    
    
    private void closeEvent(CResponse cResponse) {

    	try {
    		getContentResolver().delete(mUri, EventsColumns.TOKEN + " == '" + cResponse.getToken() + "'", null);
    	} catch (NullPointerException e) {
	        Log.e(getText(R.string.logTag).toString(), e.getMessage());
	    }
	    Log.i(getText(R.string.logTag).toString(), "Event deactivated in database");
	}

    private static final String HEXES = "0123456789ABCDEF";
    public static String getHex( byte [] raw ) {
      if ( raw == null ) {
        return null;
      }
      final StringBuilder hex = new StringBuilder( 2 * raw.length );
      for ( final byte b : raw ) {
        hex.append(HEXES.charAt((b & 0xF0) >> 4))
           .append(HEXES.charAt((b & 0x0F)));
      }
      return hex.toString();
    }
    
    private void playLocalAudio(int resId) 
    {
    	if(UserPreferences.getInstance().mVibrationOn)
    		((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(300);
    	if(UserPreferences.getInstance().mSoundOn)
    	{
	    	mMediaPlayer = MediaPlayer.create(this, resId);
	    	mMediaPlayer.start();
    	}
    }
    
    private class ResolveHttpTask extends AsyncTask<CRequestRegistration, Void, CResponse> {
        
        @Override
        protected CResponse doInBackground(CRequestRegistration... args) {
        	CResponse cResponse;
        	try {
        		cResponse = HTTPAgent.getInstance().registrationHandler(args[0]);
                return cResponse;
        	} catch (ConnectException e) {
        		mHandler.post(new Runnable() {
    				public void run() {
    					showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusNoConnection));
    				}
    			});
    			e.printStackTrace();    	
    		} catch (ClientProtocolException e) {
				//playLocalAudio(this, R.raw.wrong);
				mHandler.post(new Runnable() {
					public void run() {
						showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.statusError));
					}
				});
				e.printStackTrace();
			} catch (IOException e) {
				mHandler.post(new Runnable() {
					public void run() {
						showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.statusError));
					}
				});
				e.printStackTrace();
			} 
			return new CResponse("ERROR", "ERROR", "ERROR");
        }
    }
    
private class ResolveHttpEndingTask extends AsyncTask<CRequestRegistration, Void, CResponse> {
        
	 private ProgressDialog dialog;

	    @Override
	    protected void onPreExecute()
	    {
	        dialog = ProgressDialog.show(
	            TagScanResolverActivity.this,
	            "Przetwarzanie",
	            "w trakcie...", 
	            true);
	    }
        @Override
        protected CResponse doInBackground(CRequestRegistration... args) {
        	CResponse cResponse;
        	try {
        		cResponse = HTTPAgent.getInstance().registrationHandler(args[0]);
                return cResponse;
        	} catch (ConnectException e) {
        		mHandler.post(new Runnable() {
    				public void run() {
    					showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusNoConnection));
    				}
    			});
    			e.printStackTrace();    	
    		} catch (ClientProtocolException e) {
				//playLocalAudio(this, R.raw.wrong);
				mHandler.post(new Runnable() {
					public void run() {
						showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.statusError));
					}
				});
				e.printStackTrace();
			} catch (IOException e) {
				mHandler.post(new Runnable() {
					public void run() {
						showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.statusError));
					}
				});
				e.printStackTrace();
			} 
			return new CResponse("ERROR", "ERROR", "ERROR");
        }

		@Override
		protected void onPostExecute(CResponse cResponse) {
			if(!cResponse.getStatus().contains("ERROR"))
	        {
        		new CloseEventHttpTask().execute(cResponse);
	        	playLocalAudio(R.raw.ok);
	        	showDialog(getText(R.string.eventStatusEnded).toString());
	        }
			else if( cResponse.getStatus().equals("WRONG_PASSWORD_ERROR") || cResponse.getStatus().equals("USER_NOT_FOUND_ERROR"))
			{
				playLocalAudio(R.raw.wrong);
				showLoginDialog();
			}
			else if( cResponse.getStatus().equals("TAG_NOT_FOUND_ERROR"))
			{
				playLocalAudio(R.raw.wrong);
				showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusTagNotRecognized));
			}
			else if( cResponse.getStatus().equals("EVENT_ALREADY_STARTED_ERROR"))
			{
				playLocalAudio(R.raw.wrong);
				showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusAlreadyInProgress));
			}
			else
			{
				playLocalAudio(R.raw.wrong);
				showDialog(getText(R.string.eventStatusReason).toString() + " " + getText(R.string.eventStatusError));
			}
			dialog.dismiss();
			super.onPostExecute(cResponse);
		}
        
}

 private class CloseEventHttpTask extends AsyncTask<CResponse, Void, CResponse> {
        
        // can use UI thread here
        protected void onPreExecute() {
           
        }
        // automatically done on worker thread (separate from UI thread)
        @Override
        protected CResponse doInBackground(CResponse... args) {
        	closeEvent(args[0]);
           return null;
        }
     }
        
}
