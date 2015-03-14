package webService;

import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class CipherHelper {

	private final static String seed = "NYinlJYIRvxu5DCF0";
		
		private static byte[] getRawKey(byte[] seed) throws Exception {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(seed);
		    kgen.init(128, sr); // 192 and 256 bits may not be available
		    SecretKey skey = kgen.generateKey();
		    byte[] raw = skey.getEncoded();
		    return raw;
		}

		public static String encrypt(String value) {
		
			return value;
		
	}
	
	public static String decrypt(String value) {

			return value;
		
	}
	    
	}
