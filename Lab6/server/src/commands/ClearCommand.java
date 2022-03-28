package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, очищающей коллекцию.
 */
public class ClearCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = 8771837855713473493L;

    @Override
    public void execute(Object argObject, SocketChannel socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        commandReceiver.clear();
    }
}
