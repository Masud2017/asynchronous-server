import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

class Rdd implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello world");
		
	}
	
}

/**
 * This is main class for the server
 */
public class Broker {
	/**
	 * The main method of the application
	 * @param arg String[] - arg hold command line argument
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] arg) throws IOException, InterruptedException, ExecutionException, NumberFormatException, TimeoutException{
		ServerManager manager = new ServerManager();
//		ServerManager manager2 = new ServerManager();
		System.out.println("Thread count : "+Configuration.MAXIMUM_THREAD_COUNT);
		
//		ExecutorService service = Executors.newFixedThreadPool(10);
		System.out.println("Server that is asynchronous at port : "+Configuration.port);
//		ServerSocket server = new ServerSocket(4444);
		
		
			manager.listen();
//			sock.setTcpNoDelay(true);
//			service.submit(new ReaderWorker(sock));
			
			System.out.println("The count of thread : "+Thread.activeCount());
	}
}
