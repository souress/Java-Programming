package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Sender {
    private final SocketChannel socketChannel;

    public Sender(Session session) {
        this.socketChannel = session.getSocketChannel();
    }

    public void sendObject(Object serializedObject) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(serializedObject);
        byte [] data = byteArrayOutputStream.toByteArray();
        socketChannel.write(ByteBuffer.wrap(data));
    }
}
