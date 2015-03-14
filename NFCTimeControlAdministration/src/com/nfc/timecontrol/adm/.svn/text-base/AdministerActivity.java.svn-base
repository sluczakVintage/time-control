package com.nfc.timecontrol.adm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdministerActivity extends Activity {
	/**
	 * 
	 */
	private static TextView statusText;
	
   
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerview);
        statusText = (TextView)findViewById(R.id.statusText);
        statusText.setText(R.string.statusNone);
        
        EditText companyNIPET;
        companyNIPET = (EditText) findViewById(R.id.registerCompanyIDET);
        if(locationDetails.companyNIP != "null")
        	companyNIPET.setText(locationDetails.companyNIP);
    }
    
    
    /**
     * @param view
     */
    public void onCloseClickHandler(View view)
    {
    	stopService(new Intent(AdministerActivity.this, LocalService.class));
    	finish();
    }
    
    /**
     * @param view
     */
    public void onWriteClickHandler(View view)
    {
    	EditText locationNameET;
    	EditText companyNameET;
    	EditText serialNumberET;
    	EditText companyNIPET;
    	
    	locationNameET = (EditText) findViewById(R.id.registerLocationNameET);
    	companyNameET = (EditText) findViewById(R.id.registerCompanyNameET);
    	serialNumberET = (EditText) findViewById(R.id.registerSerialNumberET);
    	companyNIPET = (EditText) findViewById(R.id.registerCompanyIDET);
    	
    	switch (view.getId()) {
			case R.id.registerWriteButton:
				if (locationNameET.getText().length() == 0) {
					Toast.makeText(this, "Proszê wprowadziæ nazwê urz¹dzenia",	Toast.LENGTH_LONG).show();
					return;
				}
				if (locationNameET.getText().length() > 35) {
					Toast.makeText(this, "Nazwa urz¹dzenia musi byæ krótsza ni¿ 35 znaków!",	Toast.LENGTH_LONG).show();
					return;
				}
				if (companyNameET.getText().length() == 0) {
					Toast.makeText(this, "Proszê wpisaæ nazwê firmy",	Toast.LENGTH_LONG).show();
					
					return;
				}
				if (companyNameET.getText().length() > 35) {
					Toast.makeText(this, "Nazwa firmy musi byæ krótsza ni¿ 35 znaków!",	Toast.LENGTH_LONG).show();
					return;
				}
				if (serialNumberET.getText().length() == 0) {
					Toast.makeText(this, "Proszê wpisaæ numer seryjny urz¹dzenia",	Toast.LENGTH_LONG).show();
					return;
				}
				if (serialNumberET.getText().length() > 1000) {
					Toast.makeText(this, "Numer seryjny urz¹dzenia musi byæ krótszy ni¿ 1000 znaków",	Toast.LENGTH_LONG).show();
					return;
				}
				if (companyNIPET.getText().length() != 10) {
					Toast.makeText(this, "Proszê wpisaæ poprawny NIP (10 cyfr)",	Toast.LENGTH_LONG).show();
					return;
				}
				else
				{
					locationDetails.companyName = companyNameET.getText().toString();
					locationDetails.locationName = locationNameET.getText().toString();
					locationDetails.serialNumber = serialNumberET.getText().toString();
					locationDetails.companyNIP = companyNIPET.getText().toString();

			    	Intent intent = new Intent(this, WriteOnTagPrompt.class);
			    	startActivity(intent);
				}
			break;
		}
    }
    
    private void showUserDialog(int ResId) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

    	builder.setMessage(getText(ResId));
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    

    public void onNewClickHandler(View view) 
    {
    	Intent intent = new Intent(this, AdministerActivity.class);
		startActivity(intent);
		finish();
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    }
    
    @Override
    protected void onPause()
    {
    	super.onPause();
    }

    
    /**
     * Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu from XML resource
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);     

        Intent intent = new Intent(AdministerActivity.this, PreferencesActivity.class);
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
            startActivity(new Intent(AdministerActivity.this, PreferencesActivity.class));
            return true;
        case R.id.menuLogout:
            // Launch activity to set new user
            startActivity(new Intent(AdministerActivity.this, LoginActivity.class).putExtra("change", true));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
