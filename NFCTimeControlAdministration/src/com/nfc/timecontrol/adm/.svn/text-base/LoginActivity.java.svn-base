package com.nfc.timecontrol.adm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nfc.timecontrol.adm.gson.CRequestAuthentication;


/**
 * @author Sebastian
 * Starting activity, responsible for logging in user according to provided data and device identification numbers 
 */
public class LoginActivity extends Activity {
	//SharedPreferences file name
	
	
	/**
	 * String that contains user name
	 */
	private String mTempUsername;
	/**
	 * String that contains SHA-256 password hash
	 */
	private String mTempPasswordHash;

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
        
        boolean change = getIntent().getBooleanExtra("change", false);
        
        if( !( UserPreferences.getInstance().mUsername.equals("null") || UserPreferences.getInstance().mPasswordHash.equals("null") ) )
        {
        	
        	usernameET.setText(UserPreferences.getInstance().mUsername);
        	
        	mTempUsername = UserPreferences.getInstance().mUsername;
        	mTempPasswordHash = UserPreferences.getInstance().mPasswordHash;
        	
            if(!change)
            {
            	performConnection();	
            }
        }
  
        mMediaPlayer = new MediaPlayer();  
    	Log.d(getText(R.string.logTag).toString(), "Login onResume()");
    }
    
    @Override
    public void onPause()
    {
    	mMediaPlayer.release();
    	super.onPause();
    	Log.d(getText(R.string.logTag).toString(), "Login onPause()");
    }
    
    @Override
    public void onDestroy()
    {
    	mMediaPlayer.release();
    	super.onDestroy();
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
					Toast.makeText(this, "Please enter your username",	Toast.LENGTH_LONG).show();
					return;
				}
				else if (passwordET.getText().length() == 0) {
					Toast.makeText(this, "Please enter your password",	Toast.LENGTH_LONG).show();
					return;
				}
				else
				{
					mTempUsername = usernameET.getText().toString();
					/* Get SHA1 of password */
			    	mTempPasswordHash = SHA1(passwordET.getText().toString());
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
    public void connect()
	{
    	CRequestAuthentication cRequestAuthentication;

		//create Authentication Request			
		cRequestAuthentication = new CRequestAuthentication(mTempUsername, 
				mTempPasswordHash, 
				UserPreferences.getInstance().mDeviceId,
				UserPreferences.getInstance().mSubscriberId,
				"1");
		
		
		new AuthenticateTask().execute(cRequestAuthentication);
		
	}
    	
    
    /**
     * Converts byte array provided as an argument to String
     * @param data byte array that should be converted
     * @return string form of byte array provided as an argument
     */
    private static String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString();
    } 


    /**
     * Counts SHA1 checksum
     * @param text String that is to be converted to checksum
     * @return checksum
     */
    public static String SHA1(String text) 
    { 
	    MessageDigest md = null;
	    try {
			md = MessageDigest.getInstance("SHA-1");
		
			byte[] sha1hash = new byte[40];
	    	md.update(text.getBytes("iso-8859-1"), 0, text.length());
	    	sha1hash = md.digest();
		    return convertToHex(sha1hash);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	    
    } 
    
    
    private void notifyUser(int resId, int message) {
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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
        	} 
        	catch (ConnectException e) {
        		mHandler.post(new Runnable() {
					public void run() {
						notifyUser(R.raw.wrong, R.string.statusError);
					}
				});
    			e.printStackTrace();
        	}
        	catch (ClientProtocolException e) {
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
			CheckBox rememberMe = (CheckBox)findViewById(R.id.loginRememberMeCheckBox);
			if( result.equals("ACCESS_GRANTED"))
			{
				UserPreferences.getInstance().mUsername = mTempUsername;
				UserPreferences.getInstance().mPasswordHash = mTempPasswordHash; 
			
				// Save preferences to data bank
				if(rememberMe.isChecked()) 
				{
					UserPreferences.getInstance().changeUser(getApplicationContext(), mTempUsername, mTempPasswordHash);		
				}
				
				startService(new Intent(LoginActivity.this, LocalService.class));
				Intent intent = new Intent(LoginActivity.this, AdministerActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
				finish();
				
				return;
			}
			else if ( result.equals("ACCESS_DENIED_ERROR"))
			{
				notifyUser(R.raw.wrong, R.string.statusDenied);
				UserPreferences.getInstance().changeUser(getApplicationContext(), "null", "null");
			}
			else
			{
				notifyUser(R.raw.wrong, R.string.statusError);
			}
			
        	connectButton = (Button)findViewById(R.id.loginConnectButton);
        	connectButton.setEnabled(true);
        	
        	ProgressBar proggresBar = (ProgressBar)findViewById(R.id.progressBar1);
        	proggresBar.setVisibility(4);
        	
		}
        
     }
}

