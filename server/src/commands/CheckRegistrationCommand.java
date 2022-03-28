package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class CheckRegistrationCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -4636441139160848883L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        String name = argObject.toString();
        commandReceiver.checkRegistration(name);
    }
}