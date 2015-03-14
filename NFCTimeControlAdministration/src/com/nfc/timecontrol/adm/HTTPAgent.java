package com.nfc.timecontrol.adm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

import android.util.Log;

import com.google.gson.Gson;
import com.nfc.timecontrol.adm.gson.CRequestAdministration;
import com.nfc.timecontrol.adm.gson.CRequestAuthentication;
import com.nfc.timecontrol.adm.gson.CResponse;

public class HTTPAgent {

	private Gson gson = new Gson();
	/**
	 * 
	 */
	private static HTTPAgent instance;
	
	//pola
	
	/**
	 * 
	 */
	private BufferedReader in = null;

	//konstruktor
    /**
     * 
     */
    private HTTPAgent()
    {
        
    }

    /**
     *
     * @return
     */
    public static synchronized HTTPAgent getInstance() {
	if (instance == null) {
		instance = new HTTPAgent();
	}
	return instance;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
    }
	
    /**
     * @param obj
     * @return
     * @throws UnsupportedEncodingException
     */
    public StringEntity createEntity(Object obj) throws UnsupportedEncodingException {
    
		String json = gson.toJson(obj);
		
		Log.d("NFC_HTTPAgent", "JSON: " + json);
		
		StringEntity stringEntity = new StringEntity(json);
    	
    	return stringEntity;
    }
    
    /**
     * @param response
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public Reader handleResponse(HttpResponse response) throws IllegalStateException, IOException
    {
    	Reader reader;
    	
    	final int statusCode = response.getStatusLine().getStatusCode();
		
		if (statusCode != HttpStatus.SC_OK) {
			StatusLine statusLine = response.getStatusLine();
			
			Log.w(getClass().getSimpleName(), "Error  " + statusCode);
			Log.w(getClass().getSimpleName(), "StatusLine " + statusLine.getReasonPhrase());
			return null;
		
		}

		HttpEntity entity = response.getEntity();
		
		if (entity != null) {
			InputStream instream = entity.getContent();

			reader = new InputStreamReader(instream);
			return reader;
		}
		
    	return null;
    }
	/**
	 * @param username
	 * @param passwordHash
	 * @param deviceId
	 * @param subscriberId
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String authenticationHandler(CRequestAuthentication cRequestAuthentication)
	throws ClientProtocolException, IOException
	{		
		try {
			// HTTP POST authorization request 
			HttpPost httppost = new HttpPost();
			
			httppost.setURI(new URI(UserPreferences.getInstance().mServerURI + "/auth"));
			
			httppost.setEntity( createEntity(cRequestAuthentication) );
			httppost.setHeader("Accept", "application/json");
	        httppost.setHeader("Content-type", "application/json");
					
			// HTTP POST authorization response
	        HttpResponse response = getThreadSafeClient().execute(httppost);
	        
	        Reader reader = handleResponse(response);
	        String result = "null";
	        if( reader != null )
	        {
	        	CResponse cResponse = gson.fromJson(reader, CResponse.class);
	        	cResponse = cResponse.decrypt();
	        	result = cResponse.getStatus();
	        }
	        else
	        {
	        	result = "ACCESS_ERROR";	        	
	        }
	        Log.d("NFC_HTTPAgent", "Response: " + result);
	        return result;
			
		} catch (URISyntaxException e1) {

			e1.printStackTrace();
			return null;
		} finally {
			if(in != null) {
				try {
					in.close();
					return null;
				}
				catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}
	
	public CResponse administrationHandler(CRequestAdministration cRequestAdministration)
	throws ClientProtocolException, IOException
	{		
		CResponse cResponse;
		try {
			// HTTP POST authorization request 
			HttpPost httppost = new HttpPost();
			
			httppost.setURI(new URI(UserPreferences.getInstance().mServerURI + "/adm"));
			
			httppost.setEntity( createEntity(cRequestAdministration) );
			httppost.setHeader("Accept", "application/json");
	        httppost.setHeader("Content-type", "application/json");
					
			// HTTP POST authorization response
	        HttpResponse response = getThreadSafeClient().execute(httppost);
	        
	        Reader reader = handleResponse(response);
	        
	        if( reader != null )
	        {
	        	cResponse = gson.fromJson(reader, CResponse.class);
	        	cResponse = cResponse.decrypt();
	        }
	        else
	        {
	        	cResponse = new CResponse("SERVER_ERROR", "SERVER_ERROR", "SERVER_ERROR"); 
	        }
	        Log.d("NFC_HTTPAgent", "Response: " + cResponse.getStatus());
	        return cResponse;
			
		} catch (URISyntaxException e1) {

			e1.printStackTrace();
			return null;
		} finally {
			if(in != null) {
				try {
					in.close();
					return null;
				}
				catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

	}
	
	/** Thread safe httpCLient
	 * @return
	 */
	public static DefaultHttpClient getThreadSafeClient() {
	    DefaultHttpClient client = new DefaultHttpClient();
	    ClientConnectionManager mgr = client.getConnectionManager();
	    HttpParams params = client.getParams();
	 
	    client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, 
	            mgr.getSchemeRegistry()), params);
	 
	    return client;
	}
}
