package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class RegisterCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -9213103951696798922L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        String[] nameAndPassword = argObject.toString().split(" ");
        commandReceiver.register(nameAndPassword[0], nameAndPassword[1]);
    }
}
