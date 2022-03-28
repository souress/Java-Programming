package utils.multithreading;

import utils.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static main.Main.logger;

public class Sender {
    private static final Executor executor = Executors.newCachedThreadPool();

    public static void sendObject(Object serializedObject) {
        executor.execute(new SenderRunnable(serializedObject));
    }

    private static class SenderRunnable implements Runnable {
        private final Object serializedObject;

        private SenderRunnable(Object serializedObject) {
            this.serializedObject = serializedObject;
        }

        @Override
        public void run() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(serializedObject);
                byte [] data = byteArrayOutputStream.toByteArray();
                Controller.getClientSocketChannel().write(ByteBuffer.wrap(data));
                logger.debug("Результат отправлен клиенту");
            } catch (IOException exception) {
                logger.debug("Ошибка отправки результата клиенту");
                exception.printStackTrace();
            }
        }
    }
}
