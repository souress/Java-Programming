package client;

import commands.serialized.SerializedMessage;

public class Decryptor {
    static String decrypt(Object o) {
        if (o instanceof SerializedMessage) {
            SerializedMessage serializedMessage = (SerializedMessage) o;
            return (serializedMessage).getMessage();
        } else return o.toString();
    }
}
