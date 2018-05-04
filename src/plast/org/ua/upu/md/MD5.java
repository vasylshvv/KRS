package plast.org.ua.upu.md;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
	public static String getHash(String s) throws Exception{
	       MessageDigest m = MessageDigest.getInstance("MD5");
	       m.update(s.getBytes(),0,s.length());
	       String result = new BigInteger(1,m.digest()).toString(16);
	       return result;
	 }
}
