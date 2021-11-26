import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * This class is responsible for read data from server
 */
public class Reader {
	Socket sock;
    InputStream in;
    static final int MSG_MAXSIZE = 258;
    byte[] stream;
    int nb_bytes;
    
    Reader(final Socket s) {
        this.stream = new byte[4096];
        this.nb_bytes = 0;
        this.sock = s;
    }
    
    
    /** 
     * @param b
     * @throws IOException
     */
    public void setup(final boolean b) throws IOException {
        if (b) {
            this.sock.setSoTimeout(Configuration.MAXIMUM_SOCKET_TIMEOUT);
        }
        this.in = this.sock.getInputStream();
    }
    
    
    /** 
     * @return byte[]
     * @throws IOException
     */
    public byte[] read_msg() throws IOException {
        final byte[] b = new byte[258];
        int n = 0;
        while (true) {
            if (this.nb_bytes >= 3 && n == 0) {
                n = this.stream[2];
            }
            if (this.nb_bytes >= n + 3) {
                final byte[] array = new byte[3 + n];
                System.arraycopy(this.stream, 0, array, 0, 3 + n);
                System.arraycopy(this.stream, 3 + n, this.stream, 0, this.nb_bytes - (3 + n));
                this.nb_bytes -= 3 + n;
                return array;
            }
            int read;
            try {
                read = this.in.read(b);
            }
            catch (SocketTimeoutException ex) {
                return null;
            }
            if (read < 0) {
                throw new IOException("Connection may have been closed by peer");
            }
            System.arraycopy(b, 0, this.stream, this.nb_bytes, read);
            this.nb_bytes += read;
        }
    }
}
