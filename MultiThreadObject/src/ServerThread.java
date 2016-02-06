import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

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
				
				if(message.getName().equalsIgnoreCase("00")){
					System.out.println(i);
					if(i > 3){
						objectOutputStream.writeObject(new Message("","","","try again letter....."));
						continue;
					}
					//message.setRange("00000-10000");
					//message.setHash(hashGenerator.generateHash(passwordgenerator.generateRandomPassword()));
				System.out.println(message.getHash());
				objectOutputStream.writeObject(prepareMessage(message));
				}else if(message.getName().equalsIgnoreCase("1111")){
					System.out.println("Password got by Client is "+message.getText());
					i = 10;
				}
			
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
		if(pass <= 00500){
			range = "00000-00500";
			message.setRange(range);
		}
		else if(pass > 00500 && pass <= 10000){
			range = "00501-10000";
			message.setRange(range);
		}
		else if(pass > 10000 && pass <= 15000){
			range = "10001-15000";
			message.setRange(range);
		}
		else if(pass > 15000 && pass <= 20000){
			range = "15001-20000";
			message.setRange(range);
		}
		else if(pass > 20000 && pass <= 25000){
			range = "20001-25000";
			message.setRange(range);
		}
		else if(pass > 25000 && pass <= 30000){
			range = "25001-30000";
			message.setRange(range);
		}
		else if(pass > 30000 && pass <= 35000){
			range = "30001-35000";
			message.setRange(range);
		}
		else if(pass > 35000 && pass <= 40000){
			range = "35001-40000";
			message.setRange(range);
		}
		else if(pass > 40000 && pass <= 45000){
			range = "40001-45000";
			message.setRange(range);
		}
		else if(pass > 45000 && pass <= 50000){
			range = "45001-50000";
			message.setRange(range);
		}
		else if(pass > 50000 && pass <= 55000){
			range = "50001-55000";
			message.setRange(range);
		}
		else if(pass > 55000 && pass <= 60000){
			range = "55001-60000";
			message.setRange(range);
		}
		else if(pass > 60000 && pass <= 65000){
			range = "60001-65000";
			message.setRange(range);
		}
		else if(pass > 65000 && pass <= 70000){
			range = "65001-70000";
			message.setRange(range);
		}
		else if(pass > 70000 && pass <= 75000){
			range = "70001-75000";
			message.setRange(range);
		}
		else if(pass > 75000 && pass <= 80000){
			range = "75001-80000";
			message.setRange(range);
		}
		else if(pass > 80000 && pass <= 85000){
			range = "80001-85000";
			message.setRange(range);
		}
		else if(pass > 85000 && pass <= 90000){
			range = "85001-90000";
			message.setRange(range);
		}
		else if(pass > 90000 && pass <= 95000){
			range = "90001-95000";
			message.setRange(range);
		}
		else if(pass > 95000 && pass <= 99999){
			range = "95001-99999";
			message.setRange(range);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(new Date()); 
	//	System.out.println(date); //04/02/2016
		message.setHash(hashGenerator.generateHash(password+date));
		message.setText(password);
		return message;
	}
}
