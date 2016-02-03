import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	Passwordgenerator passwordgenerator = new Passwordgenerator();
	HashGenerator hashGenerator = new HashGenerator();
	ServerThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try {
			int i = 0;
			Message message  = null;
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			while((message = (Message) objectInputStream.readObject())!=null){
				System.out.println(i);
				if(i > 2){
					objectOutputStream.writeObject(new Message("try again letter....."));
					continue;
				}
				//message.setRange("00000-10000");
				//message.setHash(hashGenerator.generateHash(passwordgenerator.generateRandomPassword()));
			System.out.println(message.getHash());
			objectOutputStream.writeObject(prepareMessage(message));
			
			i++;
			}
			System.out.println("Closing....");
			socket.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Message  prepareMessage(Message message){
		String password = passwordgenerator.generateRandomPassword();
		System.out.println(password);
		String range = null;
		int pass =  Integer.parseInt(password);
		if(pass <= 10000){
			range = "00000-10000";
			message.setRange(range);
		}
		else if(pass > 10000 && pass <= 40000){
			range = "10001-40000";
			message.setRange(range);
		}
		else if(pass > 40000 && pass <= 60000){
			range = "40001-60000";
			message.setRange(range);
		}
		else if(pass > 60000 && pass <= 80000){
			range = "60001-80000";
			message.setRange(range);
		}
		else if(pass > 80000 && pass <= 99999){
			range = "80001-99999";
			message.setRange(range);
		}
		message.setHash(hashGenerator.generateHash(password));
		message.setText(password);
		return message;
	}
}
