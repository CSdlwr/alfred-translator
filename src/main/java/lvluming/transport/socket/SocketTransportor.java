package lvluming.transport.socket;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lvluming
 * @date 2017/8/15 23:00
 */
public class SocketTransportor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketTransportor.class);
    private static final int PORT = 12345;

    public static void init() throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        LOGGER.info("Socket transportor init, accepting request");
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                long s = System.currentTimeMillis();
                String query = IOUtils.toString(socket.getInputStream());
                LOGGER.info("server socket accept request [{}] cost {} ms", query, System.currentTimeMillis() - s);
            } catch (IOException e) {
                LOGGER.error("Socket transportor init error.", e);
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }

}
