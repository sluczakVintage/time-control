package test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Formatter;

public class test {

    public static String calculateHash(MessageDigest algorithm,
            String fileName) throws Exception{

        String t = fileName;
       
       InputStream is = new ByteArrayInputStream(t.getBytes("UTF-8"));
       
       DigestInputStream   dis = new DigestInputStream(is, algorithm);

        // read the file and update the hash calculation
        while (dis.read() != -1);

        // get the hash value as byte array
        byte[] hash = algorithm.digest();

        return byteArray2Hex(hash);
    }

    private static String byteArray2Hex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static void main(String[] args) throws Exception {
        String fileName = "/home/maciek/inz_readme.txt";

        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        MessageDigest md5  = MessageDigest.getInstance("MD5");        

        
        
        System.out.println(getStringTimestamp( new Timestamp(new java.util.Date().getTime())));
        System.out.println( Timestamp.valueOf("2011-07-19 01:11:43"));
        long time = new Long("2011-07-19 01:11:43.036");
        System.out.println(time);
        
        System.out.println(calculateHash(md5, fileName));
    }

    private static String getStringTimestamp(Timestamp ts ) {
    	
    	String S = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss ").format(ts);
    	return S;
    }

}
