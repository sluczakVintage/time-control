package com.nfc.timecontrol.cl;

import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nfc.timecontrol.cl.gson.CRequestAuthentication;


/**
 * @author Sebastian
 * Starting activity, responsible for logging in user according to provided data and device identification numbers 
 */
public class LoginActivity extends Activity {
	
	/**
	 * 
	 */
	private String mTempUsername;
	
	/**
	 * 
	 */
	private String mTempPasswordHash;

	/**
	 * 
	 */
	private MediaPlayer mMediaPlayer;
    
    private Handler mHandler;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);
        
        mHandler = new Handler();
        
  
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	
        EditText usernameET;
    	usernameET = (EditText) findViewById(R.id.loginUsernameET);
        
        UserPreferences.getInstance().getPreferences(this);
        
        //If username or password from data bank is NOT empty
        if( !( UserPreferences.getInstance().mUsername.equals("null") || UserPreferences.getInstance().mPasswordHash.equals("null") ) )
        {      	
        	// fill usernameET with it
        	usernameET.setText(UserPreferences.getInstance().mUsername);      	
        }
      
        mMediaPlayer = new MediaPlayer();
    	Log.d(getText(R.string.logTag).toString(), "Login onResume()");
    }
    
    @Override
    public void onPause()
    {
    	super.onPause();
    	mMediaPlayer.release();
    	Log.d(getText(R.string.logTag).toString(), "Login onPause()");
    }
    
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    	mMediaPlayer.release();
    	Log.d(getText(R.string.logTag).toString(), "Login onDestroy()");
    }
    
    /**
     * Handles event onClick of "Connect" button
     * @param view
     */
    public void onConnectClickHandler(View view)
    {
    	EditText usernameET;
    	EditText passwordET;
    	usernameET = (EditText) findViewById(R.id.loginUsernameET);
    	passwordET = (EditText) findViewById(R.id.loginPasswordET);
    	
    	switch (view.getId()) {
			case R.id.loginConnectButton:
				if (usernameET.getText().length() == 0) {
					Toast.makeText(this, getText(R.string.loginEnterUsername).toString(),	Toast.LENGTH_LONG).show();
					return;
				}
				else if (passwordET.getText().length() == 0) {
					Toast.makeText(this, getText(R.string.loginEnterPassword).toString(),	Toast.LENGTH_LONG).show();
					return;
				}
				else
				{
					mTempUsername = usernameET.getText().toString();
					/* Get SHA1 of password */
			    	mTempPasswordHash = CipherHelper.SHA1(passwordET.getText().toString());
			    	
			    	performConnection();
				}
			break;
		}	
    	
    }
    
    /**
     * Performs connection operations (REST data transfer, validation and creation of intent)
     */
    public void performConnection()
    {
		connect();
    }
    
    /**
     * Connects to webservice
     * @return true if connection + validation was completed successfully
     */
    public Boolean connect()
	{
    	CRequestAuthentication cRequestAuthentication;
    			
				
				//create Authentication Request			
				cRequestAuthentication = new CRequestAuthentication(mTempUsername, 
						mTempPasswordHash, 
						UserPreferences.getInstance().mDeviceId,
						UserPreferences.getInstance().mSubscriberId,
						"0");
				
				new AuthenticateTask().execute(cRequestAuthentication);

				return true;
	}

    
    private void notifyUser(int resId, int message) {
    	showDialogBox(message);
    	
    	playLocalAudio(this,resId);
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
    
    private void showDialogBox(int state) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

    	builder.setMessage(getText(state))
    	       .setCancelable(false)
    	       .setPositiveButton(getText(R.string.ok).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	           		
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
        
        Intent intent = new Intent(LoginActivity.this, PreferencesActivity.class);
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
            startActivity(new Intent(LoginActivity.this, PreferencesActivity.class));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
    private class AuthenticateTask extends AsyncTask<CRequestAuthentication, Void, String> {
        private Button connectButton;
        // can use UI thread here
        @Override
        protected void onPreExecute() {
        	connectButton = (Button)findViewById(R.id.loginConnectButton);
        	connectButton.setEnabled(false);

        	ProgressBar proggresBar = (ProgressBar)findViewById(R.id.progressBar1);
			proggresBar.setVisibility(1);
			
        }
        // automatically done on worker thread (separate from UI thread)
        @Override
        protected String doInBackground(CRequestAuthentication... args) {
        	String result = "ERROR";
        	try {
				result = HTTPAgent.getInstance().authenticationHandler(args[0]);
        	} catch (ConnectException e) {
        		mHandler.post(new Runnable() {
    				public void run() {
    					notifyUser(R.raw.wrong, R.string.eventStatusNoConnection);
    				}
    			});
    			e.printStackTrace();    	
    		} catch (ClientProtocolException e) {
        		mHandler.post(new Runnable() {
					public void run() {
						notifyUser(R.raw.wrong, R.string.statusError);
					}
				});
    			e.printStackTrace();
    			
    		} 
        	catch (IOException e) {
    			mHandler.post(new Runnable() {
					public void run() {
						notifyUser(R.raw.wrong, R.string.statusError);
					}
				});
    				
    			e.printStackTrace();
    		}
        	return result;
        }
		@Override
		protected void onPostExecute(String result) {
			
			if( result.equals("ACCESS_GRANTED"))
			{
				UserPreferences.getInstance().mUsername = mTempUsername;
				UserPreferences.getInstance().mPasswordHash = mTempPasswordHash; 
			
				// Save preferences to data bank
				
				UserPreferences.getInstance().changeUser(getApplicationContext(), mTempUsername, mTempPasswordHash);
				
	        	Intent intent = getIntent();
				
	        	try {
					//Launch proper intent
					if(getIntent().getAction().equals(Intent.ACTION_MAIN))
					{				
						startActivity(new Intent(LoginActivity.this, TagScanActivity.class));
					}
					else
					{
						intent.setClass(LoginActivity.this, TagScanResolverActivity.class);
						startActivity(intent);
					}
	        	} catch(NullPointerException e) {
					e.printStackTrace();
					startActivity(new Intent(LoginActivity.this, TagScanActivity.class));
				}
				
				finish();
				
				return;
			}
			else if ( result.equals("ACCESS_DENIED_ERROR"))
			{
				notifyUser(R.raw.wrong, R.string.statusDenied);
				UserPreferences.getInstance().changeUser(getApplicationContext(), "null", "null");
			}
			
        	connectButton = (Button)findViewById(R.id.loginConnectButton);
        	connectButton.setEnabled(true);
        	
        	ProgressBar proggresBar = (ProgressBar)findViewById(R.id.progressBar1);
        	proggresBar.setVisibility(4);
		}
        
     }
}

