package utils;

import main.Main;
import utils.multithreading.Decoder;
import utils.database.Connector;
import utils.database.Initializer;
import utils.database.Manager;
import utils.multithreading.RequestReader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import static main.Main.logger;

public class Controller {
    private static SocketChannel clientSocketChannel;
    private static ServerSocketChannel serverSocketChannel;
    private static Manager manager;
    private static final Decoder decoder = new Decoder();
    private static final RequestReader requestReader = new RequestReader(decoder);

    public void run(String strPort) throws IOException{
        try {
            try {
                int port = 0;
                try {
                    port = Integer.parseInt(strPort);
                } catch (NumberFormatException ex) {
                    System.exit(0);
                }

                Connector connector = new Connector();
                Connection connection = connector.makeConnection();
                Initializer initializer = new Initializer(connection);
                try {
                    initializer.initialize();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                CollectionManager collectionManager = new CollectionManager();
                try {
                    manager = new Manager(connection, collectionManager);
                    manager.updateCollection();
                    System.out.println(CollectionManager.getInfo());
                } catch (NoSuchAlgorithmException e) {
                    logger.error("Hashing algorithm not found.");
                    e.printStackTrace();
                    return;
                }

                SocketAddress socketAddress = new InetSocketAddress(port);
                Selector selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(socketAddress);
                serverSocketChannel.configureBlocking(false);
                int ops = serverSocketChannel.validOps();
                serverSocketChannel.register(selector, ops);
                logger.debug("Сервер по адресу " + serverSocketChannel.getLocalAddress().toString() + " запущен!"
                        + "\n(Для отключения введите комбинацию клавиш Ctrl+C)");

                while (true) {
                    selector.select();

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

                    while (selectionKeyIterator.hasNext()) {
                        SelectionKey currentKey = selectionKeyIterator.next();

                        if (currentKey.isAcceptable()) {
                            clientSocketChannel = serverSocketChannel.accept();
                            if (clientSocketChannel != null) {
                                clientSocketChannel.configureBlocking(false);
                                clientSocketChannel.register(selector, SelectionKey.OP_READ);
                                logger.debug("Клиент " + clientSocketChannel.getLocalAddress() + " подключился к серверу");
                            }
                        } else if (currentKey.isReadable()) {
                            clientSocketChannel = (SocketChannel) currentKey.channel();
                            requestReader.read(clientSocketChannel);
                            Thread.sleep(200);
                        }
                    }
                }
            } finally {
                if (clientSocketChannel != null) clientSocketChannel.close();
                if (serverSocketChannel != null) serverSocketChannel.close();
            }
        } catch (IOException | NullPointerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static SocketChannel getClientSocketChannel() {
        return clientSocketChannel;
    }

    public static Manager getManager() {
        return manager;
    }

}
