import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;


public class SenderWorker implements Callable<Object>{
	private Socket serverSocket;
	private byte[] sendableData;
	private BufferedOutputStream bufferedOutputStream;

	public SenderWorker(Socket serverSocket,byte[] data) throws IOException{
		this.serverSocket = serverSocket;
		this.sendableData = data;
		this.bufferedOutputStream = new BufferedOutputStream(this.serverSocket.getOutputStream());
	}
	@Override
	public Object call() {
		try {
			this.bufferedOutputStream.write(this.sendableData);
		} catch (IOException e) {
			e.printStackTrace();
			return Configuration.SEND_SUCCESS_ERROR;
		}
		return Configuration.SEND_SUCCESS;
	}

}
