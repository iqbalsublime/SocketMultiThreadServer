import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
	
	Passwordgenerator passwordgenerator = new Passwordgenerator();
	HashGenerator hashGenerator = new HashGenerator();
	
	public Client(){
		
	}
	
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
				processMessage(returnMessage, objectOutputStream);
				
			}
		}else{
			System.out.println("Enter Client Name <name>");
		}
	}
	
	public static void processMessage(Message message, ObjectOutputStream objectOutputStream) throws IOException{
		
		if(message.getText().equalsIgnoreCase("try again letter.....")){
			System.out.println("Your Limit exceeded");
		}else{
			String totalRange = message.getRange();
			String ranges [] = totalRange.split("-");
			String pass1 = ranges[0];
			String pass2 = ranges[1];
			String password = new Passwordgenerator().generatePasswordWithinRange(Integer.parseInt(pass2), Integer.parseInt(pass1));
			if(password.length()==4){
				password = "0"+password;
			}
			else if(password.length()==3){
				password = "00"+password;
			}
			else if(password.length()==2){
				password = "000"+password;
			}
			else if(password.length()==1){
				password = "0000"+password;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			String date = sdf.format(new Date()); 
			//System.out.println(date); //04/02/2016
			String hash = new HashGenerator().generateHash(password+date);
			
			System.out.println("Server Password is: "+message.getText());
			System.out.println("My Password is: "+password);
			
			System.out.println("Password range is: "+message.getRange());
			System.out.println(message.getHash());
			
			if(hash.equals(message.getHash())){
				System.out.println("Password matched");
				System.out.println("Server Hash: "+message.getHash());
				System.out.println("Client Hash: "+hash);
				
				//
				Message message1 = new Message("22","11","1111",password);
				System.out.println("Sending password to Server....."+password);
				objectOutputStream.writeObject(message1);
		
			}
			else{
				System.out.println("Password doesn't matched");
				System.out.println("Server Hash: "+message.getHash());
				System.out.println("Client Hash: "+hash);
				
				
				
			}
		}
		
		
	}
	
	

}
