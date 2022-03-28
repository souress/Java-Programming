package client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Reciever {
    public static String receive(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
            byteBuffer.clear();
            socketChannel.read(byteBuffer);
            if (byteBuffer.position() != 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                return Decryptor.decrypt(objectInputStream.readObject());
            }
        }
    }
}
