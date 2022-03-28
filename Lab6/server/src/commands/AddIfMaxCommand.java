package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, добавляющей в коллекцию элемент, если он является наибольшим.
 */
public class AddIfMaxCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 3185682357961358964L;

    @Override
    public void execute(Object argObject, SocketChannel socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        commandReceiver.addIfMax(argObject);
    }
}
