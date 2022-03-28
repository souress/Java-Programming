package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

public class Session {
    private SocketChannel socketChannel;
    private final String hostName;
    private final int port;

    public Session(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void startSession() throws IOException, InterruptedException {
        for (int i = 0; true; i++){
            try {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(hostName, port);
                socketChannel = SocketChannel.open(inetSocketAddress);
                socketChannel.configureBlocking(false);
                System.out.printf("Подключение к удаленному адресу %s по порту %d%n", hostName, port);
                break;
            } catch (SocketException ex) {
                System.out.println("Не удалось подключиться к удаленному адресу...");
                if (i == 2) System.exit(0);
                System.out.println("Попробую снова");
                Thread.sleep(2000);
            }
        }
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
