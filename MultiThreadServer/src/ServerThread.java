import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	
	ServerThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try {
			String message  = null;
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("user"+bufferReader.readLine()+" is now connected to the server");
			while( (message = bufferReader.readLine()) != null){
				System.out.println("incoming client message! "+message);
				printWriter.println("Server echoing client message==>"+message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
