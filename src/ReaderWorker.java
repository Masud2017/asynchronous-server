import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class ReaderWorker implements Callable<Object>{
	private Socket serverSocket;
	private BufferedInputStream bufferedInputStream;
	private InputStream inp;
	private BufferedOutputStream bufferedOutputStream;
	
	public ReaderWorker(Socket serverSocket) throws IOException {
		this.serverSocket = serverSocket;
		this.bufferedInputStream = new BufferedInputStream(this.serverSocket.getInputStream());
		this.bufferedOutputStream = new BufferedOutputStream(this.serverSocket.getOutputStream());
		this.inp = serverSocket.getInputStream();
	}

	@Override
	public Object call() throws Exception {
		serverSocket.setSoTimeout(1000);
		byte[] data = new byte[44];
		this.bufferedInputStream.read(data);
		
		System.out.println(new String(data,StandardCharsets.UTF_8));
		for (byte dt : data) {
			System.out.println("Different segment of data: "+dt);
		}

		byte[] data2 = new byte[4];
		data2[0] = 32;
		data2[1] = 0x2;
		data2[2] = 0x00;
		data2[3] = 0x00;
		this.bufferedOutputStream.write(data2);
		this.bufferedOutputStream.flush();
		this.bufferedInputStream.close();
		this.bufferedOutputStream.close();
		this.serverSocket.close();
		this.inp.close();

		return data;
	}

}
