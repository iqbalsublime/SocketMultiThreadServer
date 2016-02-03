import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException {
		if(args.length == 1){
			String name = args[0];
			Socket socket = new Socket("localhost", 4444);
			BufferedReader bufferReaderFromClient =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println(name);
			BufferedReader bufferReaderFromCommandPromt = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				String readerInput = bufferReaderFromCommandPromt.readLine();
				printWriter.println(name+" : "+readerInput);
				System.out.println(bufferReaderFromClient.readLine());
			}
		}else{
			System.out.println("Use Client Name <name>");
		}
	}

}
