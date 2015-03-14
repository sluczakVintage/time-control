package com.nfc.timecontrol.adm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nfc.timecontrol.adm.gson.CRequestAdministration;
import com.nfc.timecontrol.adm.gson.CResponse;

public class WriteOnTagActivity extends Activity {
	
	/**
	 * 
	 */
	Button closeButton;
	
    /**
	 * Decides if the application should return to the last form or create new, clear 
	 */
	private boolean mStartNew = false;
	private MediaPlayer mMediaPlayer;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);
        
        UserPreferences.getInstance().getPreferences(this);
        
        closeButton = (Button)findViewById(R.id.tagWriteCloseButton);
        closeButton.setEnabled(false);
        
        NfcAdapter.getDefaultAdapter(this);
    	
    	resolveIntent(getIntent());
    }
    
	 @Override
    protected void onResume()
    {
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

    public void onWriteCloseClickHandler(View view) {
    	
	    Intent intent = new Intent(this, AdministerActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
    	
    	finish();
    }
    
	/**
     * @param intent
     */
    private void resolveIntent(Intent intent) {
    	TextView statusText;
    	statusText = (TextView)findViewById(R.id.statusText);
    	
        // Parse the intent 	
    	String action = intent.getAction();
    	Log.i(getText(R.string.logTag).toString(), "Resolving intent: " + intent + " for action " + action);
    	if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
    		Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
    		
    		Log.i(getText(R.string.logTag).toString(), "Reading EXTRA_TAG");

    		byte[] byte_id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
    		// Convert byte data to Hex string
    		String tag_id = getHex(byte_id);
    		Log.i("Foreground dispatch", "TagID: " + tag_id );
    		
    		final Ndef ndefref = Ndef.get(tagFromIntent);

    	    if(ndefref == null || !ndefref.isWritable()) {
    	            return;
    	        }
    	        
	        Time now = new Time();
			now.setToNow();
	    	CRequestAdministration cRequestAdministration = new CRequestAdministration(UserPreferences.getInstance().mUsername, 
	    			UserPreferences.getInstance().mPasswordHash, 
	    			UserPreferences.getInstance().mDeviceId, 
	    			UserPreferences.getInstance().mSubscriberId,
	    			tag_id, locationDetails.companyNIP, locationDetails.companyName, locationDetails.locationName, now.toMillis(true), locationDetails.serialNumber);

	    		CResponse cResponse;
				try {
					(new Thread() {
	    	            public void run() {
	    	                try {
	    	                    int count = 0;
	    	                        // connect I/O
	    	                    ndefref.connect();
	    	                    Log.i(getText(R.string.logTag).toString(), getText(R.string.writeConnecting).toString());
	    	                        // check for connection
	    	                    while(!ndefref.isConnected()) {
	    	                        if(count > 6000) {
	    	                            throw new Exception(getText(R.string.writeFatalError).toString());
	    	                        }
	    	                        count++;
	    	                        sleep(10);                        
	    	                    }
	    	                    Log.i( getText(R.string.logTag).toString(), "Writing location Name: " + locationDetails.locationName );
	    	                        // construct the NdefMessage
	    	                    NdefRecord[] rec = new NdefRecord[1];
	    	                    rec[0] = newTextRecord( "NFCTC" );
	    	                    
	    	                    
	    	                    NdefMessage msg = new NdefMessage(rec);   
	
	    	                        // write the NdefMessage
	    	                    ndefref.writeNdefMessage(msg);
	
	    	                        // close I/O
	    	                    ndefref.close();
	    	                    Log.i(getText(R.string.logTag).toString(), getText(R.string.writeClosing).toString());
	
	    	                } catch (Throwable t) {
	    	                	
	    	                    t.printStackTrace();
	    	                }
	    	            }
	    	        }).start();
					
					cResponse = new ResolveIntentTask().execute(cRequestAdministration).get();

	    		if (!cResponse.getStatus().contains("ERROR")) {
	    			notifyUser(R.raw.ok, R.string.writeOk);
	    			statusText.setText(R.string.statusTextOk);
	    			mStartNew = true;
	    			closeButton.setEnabled(true);
	    			
	    			return;
	    		}
    		else if(cResponse.getStatus().equals("TAG_ALREADY_EXISTS_ERROR"))
    		{
    			statusText.setText(R.string.writeError);
    			showUserDialog(R.string.writeErrorTagExists, R.raw.wrong);
    		}
    		else
    		{
    			statusText.setText(R.string.statusTextRegistrationError);
    			notifyUser(R.raw.wrong, cResponse.getStatus());
    		}
			} catch (InterruptedException e) {
				statusText.setText(R.string.writeError);
    			showUserDialog(R.string.writeError, R.raw.wrong);
				e.printStackTrace();
			} catch (ExecutionException e) {
				statusText.setText(R.string.writeError);
    			showUserDialog(R.string.writeError, R.raw.wrong);
				e.printStackTrace();
			}

    	}
		mStartNew = false;
		closeButton.setEnabled(true);
    }
    
    // create a new NdefRecord 
    private NdefRecord newTextRecord(String text) {
        byte[] langBytes = Locale.ENGLISH.
                                getLanguage().
                                getBytes(Charset.forName("US-ASCII"));

        byte[] textBytes = text.getBytes(Charset.forName("UTF-8"));
        
        char status = (char) (langBytes.length);

        byte[] data = new byte[1 + langBytes.length + textBytes.length]; 
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        
        String type = "text/vnd.timecontrol";
        final byte[] typeBytes = type.getBytes(Charset.forName("US-ASCII"));
        
        return new NdefRecord(NdefRecord.TNF_MIME_MEDIA, 
                                typeBytes, 
                                new byte[0], 
                                data);
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
    
    private void showUserDialog(int ResId, int audioResId) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

    	builder.setMessage(getText(ResId))
    	       .setCancelable(false)
    	       .setPositiveButton(getText(R.string.ok).toString(), new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	           		finish();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	playLocalAudio(this,audioResId);
    	alert.show();
    }
    
    private void notifyUser(int resId, int stringId) {
    	Toast.makeText(this, getText(stringId), Toast.LENGTH_LONG).show();
    	playLocalAudio(this,resId);
    }
    
    private void notifyUser(int resId, String string) {
    	Toast.makeText(this, string, Toast.LENGTH_LONG).show();
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
    
    
    private class ResolveIntentTask extends AsyncTask<CRequestAdministration, Void, CResponse> {
        
        @Override
        protected CResponse doInBackground(CRequestAdministration... args) {
        	try{
        		
        		return HTTPAgent.getInstance().administrationHandler(args[0]);
	        	
			} catch (ClientProtocolException e) {
				notifyUser(R.raw.wrong, R.string.writeErrorServer);
				e.printStackTrace();
			} catch (IOException e) {
				notifyUser(R.raw.wrong, R.string.writeErrorServer);
				e.printStackTrace();
			}
        	return null;
        }
     }
    
    @Override
    public void onBackPressed() {
     
	    Intent intent = new Intent(this, AdministerActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
    	
    	finish();
       return;
    }
}
