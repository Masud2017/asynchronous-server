import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This class implements the low level ServerSocket implementation and apply the asynchronous capability on it.
 * */
public class ServerManager {
	private ServerSocket server;
	private ExecutorService executorServiceReader;
	private ExecutorService executorServiceSender;
	private List<Future<Object>> futureReaderList;
	private List<Future<Object>> futureSenderList;
	private BlockingQueue blockingQueueSender; // for sender worker
	private BlockingQueue blockingQueueReader; // for reader worker
	

	/**
	 * Constructor for ServerManager
	 * @param server Server the low level ServerSocket implementation
	 * @throws IOException 
	 * */
	public ServerManager() throws IOException {
		this.server = new ServerSocket(Configuration.port);
		this.executorServiceReader = Executors.newFixedThreadPool(Configuration.MAXIMUM_THREAD_COUNT); // creating a collection of thread pool
		this.executorServiceSender = Executors.newFixedThreadPool(Configuration.MAXIMUM_THREAD_COUNT);
	}
	
	public void listen() throws IOException, InterruptedException, ExecutionException, NumberFormatException, TimeoutException {
		
		while (true) {
			Socket newServerSocket = server.accept();
			newServerSocket.setTcpNoDelay(true);
			
			
//			SenderWorker senderWorker = new SenderWorker(newServerSocket,data);
			ReaderWorker readerWorker = new ReaderWorker(newServerSocket);
//			Future<Object> futureSender = this.executorServiceSender.submit(senderWorker);
//			Future<Object> futureReader = this.executorServiceReader.submit(readerWorker);
			executorServiceReader.submit(new ReaderWorker(newServerSocket));
//			System.out.println(new String((byte[])futureReader.get(),StandardCharsets.UTF_8));
			
		}
	}
}
		