package com.nfc.timecontrol.adm;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;

public class UserPreferences {

	private static UserPreferences instance;

	/**
	 * String that contains user name
	 */
	public String mUsername = "null";
	/**
	 * String that contains SHA-256 password hash
	 */
	public String mPasswordHash = "null";

	/**
	 * 
	 */
	public String mDeviceId;

	/**
	 * 
	 */
	public String mSubscriberId;

	/**
	 * 
	 */
	public boolean mVibrationOn;
	
	/**
	 * 
	 */
	public boolean mSoundOn;
//62.121.111.103
	public String mServerIP = "62.121.111.103";
	
	public final String mProtocol = "http://";
	
	public final String mServerSuffix = ":8080/nfcTimeControl/service";
	
	public String mServerURI = mProtocol + mServerIP + mServerSuffix;
	
    /**
     * konstruktor 
     */
	private UserPreferences()
    {
    	mUsername = "null";
    	mPasswordHash = "null";
    	
    	mVibrationOn = true;
    	mSoundOn = true;
    }

    /**
     *
     * @return
     */
    public static synchronized UserPreferences getInstance() {
	if (instance == null) {
		instance = new UserPreferences();
	}
	return instance;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
    }
    
    public void getPreferences(Context context)
    {
    	 /* Get phone IMEI */
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        mDeviceId = telephonyManager.getDeviceId();
        mSubscriberId = telephonyManager.getSubscriberId();
        
        /* Get Shared Preferences */
        SharedPreferences loginData = context.getSharedPreferences(context.getText(R.string.LOGIN_DATA).toString(), 0);
        mUsername = loginData.getString("username", mUsername);
        mPasswordHash = loginData.getString("passwordHash", mPasswordHash);
        mServerIP = loginData.getString("serverIP", mServerIP);
        mVibrationOn = loginData.getBoolean("vibrationOn", true);
        mSoundOn = loginData.getBoolean("soundOn", true);
    
        mServerURI = mProtocol + mServerIP + mServerSuffix;
        
        Log.d(context.getText(R.string.logTag).toString(), "Retriving username " + mUsername + " passwordHash " + mPasswordHash + " and other user prefs");
    }
    
    public void changeUser(Context context, String username, String passwordHash) 
    {
    	SharedPreferences loginData = context.getSharedPreferences(context.getText(R.string.LOGIN_DATA).toString(), 0);
		SharedPreferences.Editor editor = loginData.edit();
		mUsername = username;
		mPasswordHash = passwordHash;
		editor.putString("username", username);
		editor.putString("passwordHash", passwordHash);
		Log.d(context.getText(R.string.logTag).toString(), "Saving username " + username + " passwordHash " + passwordHash);
		editor.commit();
    }
    
    public void saveSettings(Context context, String serverIP, boolean vibrationOn, boolean soundOn)
    {
    	SharedPreferences loginData = context.getSharedPreferences(context.getText(R.string.LOGIN_DATA).toString(), 0);
		SharedPreferences.Editor editor = loginData.edit();
		mServerIP = serverIP;
		mVibrationOn = vibrationOn;
		mSoundOn = soundOn;
		editor.putString("serverIP", serverIP);
		editor.putBoolean("vibrationOn", vibrationOn);
		editor.putBoolean("soundOn", soundOn);
		Log.d(context.getText(R.string.logTag).toString(), "Saving serverIP " + serverIP + " vibration " + vibrationOn + " sound " + soundOn);
		editor.commit();
    }
}
