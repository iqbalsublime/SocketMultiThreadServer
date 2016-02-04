import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {
	
	Passwordgenerator passwordgenerator = new Passwordgenerator();
	HashGenerator hashGenerator = new HashGenerator();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		if(args.length == 1){
			String name = args[0];
			Socket socket = new Socket("localhost", 4444);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream.writeObject(new Message(name));
			BufferedReader bufferReaderFromCommandPromt = new BufferedReader(new InputStreamReader(System.in));
			
			while(true){
				String readerInput = bufferReaderFromCommandPromt.readLine();
				Message message = new Message("22","11","00",readerInput);
				objectOutputStream.writeObject(message);
				Message returnMessage = (Message) objectInputStream.readObject();
				processMessage(returnMessage);
				
			}
		}else{
			System.out.println("Enter Client Name <name>");
		}
	}
	
	public static void processMessage(Message message){
		String password = new Passwordgenerator().generateRandomPassword();
		String hash = new HashGenerator().generateHash(password);
		
		Random r = new Random();
		int Low = 10;
		int High = 100;
		int Result = r.nextInt(High-Low) + Low;
		
		System.out.println("Server Password is: "+message.getText());
		System.out.println("My Password is: "+password);
		
		if(message.getText().equalsIgnoreCase("try again letter.....")){
			System.out.println("Your Limit execeeded");
		}else{
			System.out.println("Password range is: "+message.getRange());
			System.out.println(message.getHash());
			
			if(hash.equals(message.getHash())){
				System.out.println("Password matched");
				System.out.println("Server Hash: "+message.getHash());
				System.out.println("Client Hash: "+hash);
			}
			else{
				System.out.println("Password doesn't matched");
				System.out.println("Server Hash: "+message.getHash());
				System.out.println("Client Hash: "+hash);
			}
		}
		
		
	}

}
