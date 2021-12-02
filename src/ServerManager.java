import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * This class implements the low level ServerSocket implementation and apply the asynchronous capability on it.
 * */
public class ServerManager {
	private ServerSocket server;
	private ExecutorService threadPool;

//	private List<Future<Object>> futureReaderList;
//	private List<Future<Object>> futureSenderList;
	private BlockingQueue blockingQueueSender; // for sender worker
	private List<ReaderWorker> workerList = new ArrayList<>();
	

	/**
	 * Constructor for ServerManager
	 * @throws IOException 
	 * */
	public ServerManager() throws IOException {
		this.server = new ServerSocket(Configuration.port);
		this.threadPool = Executors.newFixedThreadPool(Configuration.MAXIMUM_THREAD_COUNT);


	}
	
	public void listen() throws IOException, InterruptedException, ExecutionException, NumberFormatException, TimeoutException {
		
		try {
			while (true) {
				Socket newServerSocket = server.accept();
				newServerSocket.setTcpNoDelay(true);

				ReaderWorker readerWorker = new ReaderWorker(newServerSocket);
				workerList.add(readerWorker);

				this.threadPool.submit(readerWorker);

			}
		} catch(IOException e) {

			} finally {
				server.close();
			}
		}
}

		