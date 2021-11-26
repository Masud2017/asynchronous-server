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
		byte[] data = new byte[12];
		this.bufferedInputStream.read(data);
		
		System.out.println(new String(data,StandardCharsets.UTF_8));
		this.bufferedOutputStream.write(12);
		this.bufferedInputStream.close();
		this.serverSocket.close();
		this.inp.close();

		return data;
	}

}
