
import java.util.Random;

public class Passwordgenerator{
	
	protected static final int TOTAL_SIZE = 5;
    protected static String members = "0123456789";
    protected static Random random = new Random();
    

    public synchronized String generateRandomPassword() {
        char[] password = new char[TOTAL_SIZE];
        for (int i = 0; i < password.length; i++) {
            password[i] = members.charAt(random.nextInt(members.length()));
        }
        return new String(password);
    }
    
    public synchronized String generatePasswordWithinRange(int high, int low) {
    	Random r = new Random();
		//int Low = 10;
		//int High = 100;
		int result = r.nextInt(high-low) + low;
		return Integer.toString(result);
    }
    
   
}