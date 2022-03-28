package utils;

import commands.utils.CommandReceiver;
import utils.CommandHandler.Decrypting;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Controller {
    private static SocketChannel clientSocketChannel;
    private static ServerSocketChannel serverSocketChannel;
    private static ObjectInputStream in;

    public void run(String strPort) throws IOException{
        try {
            try {
                int port = 0;
                CollectionManager.initHashSet();
                try {
                    port = Integer.parseInt(strPort);
                } catch (NumberFormatException ex) {
                    System.exit(0);
                }

                SocketAddress socketAddress = new InetSocketAddress(port);
                Selector selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(socketAddress);
                serverSocketChannel.configureBlocking(false);
                int ops = serverSocketChannel.validOps();
                serverSocketChannel.register(selector, ops);
                System.out.println("Сервер по адресу " + serverSocketChannel.getLocalAddress().toString() + " запущен!"
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
                                System.out.println("Клиент " + clientSocketChannel.getLocalAddress() + " подключился к серверу");
                            }
                        } else if (currentKey.isReadable()) {
                            try {
                                clientSocketChannel = (SocketChannel) currentKey.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
                                clientSocketChannel.read(byteBuffer);
                                in = new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array(), 0, byteBuffer.limit()));
                                Object o = in.readObject();
                                Decrypting decrypting = new Decrypting(clientSocketChannel);
                                decrypting.decrypt(o);
                                byteBuffer.clear();
                                Thread.sleep(200);
                            } catch (IOException e) {
                                Thread.sleep(200);
                            }
                        }

                    }
                }
            } finally {
                if (clientSocketChannel != null) clientSocketChannel.close();
                serverSocketChannel.close();
            }
        } catch (IOException | ClassNotFoundException | NullPointerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static SocketChannel getClientSocketChannel() {
        return clientSocketChannel;
    }
}
