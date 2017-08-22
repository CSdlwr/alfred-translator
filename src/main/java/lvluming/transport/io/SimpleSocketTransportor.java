package lvluming.transport.io;

import lvluming.biz.Biz;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程版本的oio socket
 *
 * @author lvluming
 * @date 2017/8/15 23:00
 */
public class SimpleSocketTransportor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSocketTransportor.class);
    private static final int PORT = 12345;

    public static void init() throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        LOGGER.info("Socket transportor init, accepting request");
        while (true) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                long s = System.currentTimeMillis();
                inputStream = socket.getInputStream();
                String query = IOUtils.toString(inputStream).trim().replaceAll("\\\\", "");
                String response = Biz.getBiz(query).process();
                outputStream = socket.getOutputStream();
                outputStream.write(response.getBytes());
                LOGGER.info("server socket accept request [{}] cost {} ms", query, System.currentTimeMillis() - s);
            } catch (IOException e) {
                LOGGER.error("Socket transportor init error.", e);
            } finally {
                if (socket != null) {
                    socket.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }

}
