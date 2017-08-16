package lvluming;

import com.google.common.base.Joiner;
import lvluming.biz.Biz;
import lvluming.transport.socket.SocketTransportor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author lvluming
 * @date 2017/8/6 17:07
 */
public class Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private static final Joiner PARAMETER_JOINER = Joiner.on(StringUtils.SPACE).skipNulls();

    public static void main(String[] args) throws IOException {

        Biz.initEnv();
        SocketTransportor.init();

    }
}
