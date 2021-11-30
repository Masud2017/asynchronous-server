import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;


public class SenderWorker implements Callable<Object>{
	private Socket serverSocket;
	byte[] data2 = new byte[4];

//	private byte[] sendableData;
	private BufferedOutputStream bufferedOutputStream;

	public SenderWorker(Socket serverSocket,byte[] data) throws IOException{
		data2[0] = 32;
		data2[1] = 0x2;
		data2[2] = 0x00;
		data2[3] = 0x00;
		this.serverSocket = serverSocket;

		this.bufferedOutputStream = new BufferedOutputStream(this.serverSocket.getOutputStream());
	}
	@Override
	public Object call() {
		try {
			this.bufferedOutputStream.write(this.data2);
		} catch (IOException e) {
			e.printStackTrace();
			return Configuration.SEND_SUCCESS_ERROR;
		}
		return Configuration.SEND_SUCCESS;
	}

}
