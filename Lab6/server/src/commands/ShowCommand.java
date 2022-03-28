package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, выводящей в консоль содержимого коллекции.
 */
public class ShowCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = -4611396263412415993L;

    @Override
    public void execute(Object argObject, SocketChannel socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        commandReceiver.show();
    }
}
