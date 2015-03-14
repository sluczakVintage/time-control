package com.nfc.timecontrol.adm;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class CipherHelper {

	private final static String seed = "NYinlJYIRvxu5DCF0";
		
		public static String encrypt(String text) throws Exception {
			return text;
		}
		
		public static String decrypt(String encrypted) throws Exception {
			return encrypted;
		}

		private static byte[] getRawKey(byte[] seed) throws Exception {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(seed);
		    kgen.init(128, sr); // 192 and 256 bits may not be available
		    SecretKey skey = kgen.generateKey();
		    byte[] raw = skey.getEncoded();
		    return raw;
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
	}
