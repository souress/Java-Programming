package commands;

import java.nio.channels.SocketChannel;

public interface CommandInterface {
    void execute(String arg, String name, String password, SocketChannel socket);
}
