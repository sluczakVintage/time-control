package com.nfc.timecontrol.adm;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.util.Log;

public class WriteOnTagPrompt extends Activity {

	private NfcAdapter mNfcAdapter;
	private String[][] mTechLists;
	private PendingIntent mPendingIntent;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.writepromptview);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
    	if(NfcAdapter.getDefaultAdapter(this) != null)
    		NfcAdapter.getDefaultAdapter(this).disableForegroundDispatch(this);
		super.onPause();
	}

	@Override
	protected void onResume() {
	    //obsluga nfc
    	mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // Setup a tech list for all NfcF tags
        mTechLists = new String[][] { new String[] { MifareUltralight.class.getName() } };
     
		Intent intent = new Intent(this, WriteOnTagActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    	
    	mPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    	
    	
		NfcAdapter.getDefaultAdapter(this).enableForegroundDispatch(this, mPendingIntent, null, mTechLists);
		
		super.onResume();
	}


    @Override
    public void onNewIntent(Intent intent) {
    	Log.i("Foreground dispatch", "Discovered tag with intent: " + intent + " for action " + intent.getAction());
		finish();
    }
}
