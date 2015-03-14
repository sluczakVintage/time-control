package com.nfc.timecontrol.adm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PreferencesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.optionsview);
		
    	EditText serverIPET;
    	CheckBox vibrationsCB;
    	CheckBox soundsCB;
    	
    	String serverIP = UserPreferences.getInstance().mServerIP;
    	boolean vibrationOn = UserPreferences.getInstance().mVibrationOn;
    	boolean soundOn = UserPreferences.getInstance().mSoundOn;
    	
    	serverIPET = (EditText) findViewById(R.id.optionsServerIPET);
    	vibrationsCB = (CheckBox) findViewById(R.id.optionsVibrations);
    	soundsCB = (CheckBox) findViewById(R.id.optionsSound);
    	
    	serverIPET.setText(serverIP);
    	vibrationsCB.setChecked(vibrationOn);
    	soundsCB.setChecked(soundOn);
	}

	/**
     * Handles event onClick of "Save" button
     * @param view
     */
    public void onOptionsSaveClickHandler(View view)
    {
    	EditText serverIPET;
    	CheckBox vibrationsCB;
    	CheckBox soundsCB;
    	
    	serverIPET = (EditText) findViewById(R.id.optionsServerIPET);
    	vibrationsCB = (CheckBox) findViewById(R.id.optionsVibrations);
    	soundsCB = (CheckBox) findViewById(R.id.optionsSound);
    	
    	switch (view.getId()) {
			case R.id.optionsSave:
				if (serverIPET.getText().length() == 0) {
					Toast.makeText(this, "Please enter server IP",	Toast.LENGTH_LONG).show();
					return;
				}
				else
				{
			    	UserPreferences.getInstance().saveSettings(this, serverIPET.getText().toString(), vibrationsCB.isChecked(), soundsCB.isChecked());
			    	finish();
				}
			break;
		}	
    	
    }
    
	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	protected void onPause() {

		super.onPause();
	}

	@Override
	protected void onRestart() {

		super.onRestart();
	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	
	
}
