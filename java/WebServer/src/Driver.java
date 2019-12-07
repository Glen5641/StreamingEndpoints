import java.net.ServerSocket;
import java.net.Socket;

public class Driver { 
	public static void main(String[] args) throws Exception { 
		final ServerSocket server = new ServerSocket(8000); 
		System.out.println("Listening for connection on port 8080 ...."); 
		while (true){ 
			Socket socket = null;
			try { 
				socket = server.accept();
				Thread t = new Handler(socket);
				t.start();
			} catch (Exception e) {
				System.out.println("Connection Closed");
			}
		}
	}
}