import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	Passwordgenerator passwordgenerator = new Passwordgenerator();
	ServerThread(Socket socket){
		this.socket = socket;
		
	}
	
	public void run(){
		try {
			String message  = null;
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("user "+bufferReader.readLine()+" is now connected to the server");
			while( (message = bufferReader.readLine()) != null){
				System.out.println("incoming client message! "+message);
				String password = passwordgenerator.generateRandomPassword();
				printWriter.println("Server echoing client message==>"+password);
				
				
						
			/*	MessageDigest m = null;
				try {
					m = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				m.reset();
				m.update(message.getBytes());
				byte[] digest = m.digest();
				BigInteger bigInt = new BigInteger(1,digest);
				String hashtext = bigInt.toString(16);
				System.out.println(hashtext+"\n");
				// Now we need to zero pad it if you actually want the full 32 chars.
				while(hashtext.length() < 32 ){
				  hashtext = "0"+hashtext;
				}
				System.out.println(hashtext);*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
