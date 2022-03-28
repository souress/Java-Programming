package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, добавляющей элемент в коллекцию.
 */
public class AddCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = -3437100694030550244L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        commandReceiver.add(argObject, username);
    }
}
