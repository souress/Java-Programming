package utils.multithreading;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

import static main.Main.logger;

public class RequestReader {
    private final Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 3);
    private static Decoder decoder;

    public RequestReader(Decoder decoder) {
        this.decoder = decoder;
    }

    public void read(SocketChannel socketChannel) {
        executor.execute(new RequestReaderRunnable(socketChannel));
    }

    private static class RequestReaderRunnable implements Runnable {
        private final SocketChannel socketChannel;

        private RequestReaderRunnable(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
                socketChannel.read(byteBuffer);
                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array(), 0, byteBuffer.limit()));
                Object o = in.readObject();
                decoder.decrypt(socketChannel, o);
                byteBuffer.clear();
                logger.debug("Данные приняты от " + socketChannel.getRemoteAddress());
            } catch (IOException | ClassNotFoundException exception) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
