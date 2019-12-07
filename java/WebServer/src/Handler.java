import java.net.Socket;

public class Handler extends Thread {

	private final Socket socket;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			socket.setKeepAlive(true);
			String httpResponse = "HTTP/1.1 200 OK\r\n";
			httpResponse += "Content-Type: application/json\r\n";
			httpResponse += "Connection: keep-alive\r\n";
			httpResponse += "Content-Type: text/html\r\n";
			httpResponse += "X-Content-Type-Options: nosniff \r\n\r\n";
			socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
			while (true) {
				double rng = Math.random();
				httpResponse = "{ random : " + rng + " }\r\n";
				socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Connection Closed");
		}
	}
}
