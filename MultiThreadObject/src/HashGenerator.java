
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator{

    public synchronized String generateHash(String password) {
    	MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.reset();
		m.update(password.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		System.out.println(hashtext+"\n");
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		return hashtext;
    }
}