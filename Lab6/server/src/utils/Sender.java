package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Sender {
    public static void sendObject(Object serializedObject) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serializedObject);
        byte [] data = byteArrayOutputStream.toByteArray();
        Controller.getClientSocketChannel().write(ByteBuffer.wrap(data));
    }
}
