package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class SignInCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -7237696736602519701L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        String[] nameAndPassword = argObject.toString().split(" ");
        commandReceiver.signIn(nameAndPassword[0], nameAndPassword[1]);
    }
}

