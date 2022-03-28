package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class CheckExistCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -3773218210331586469L;

    @Override
    public void execute(Object argObject, SocketChannel socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        String id = argObject.toString();
        commandReceiver.checkExist(id);
    }
}
