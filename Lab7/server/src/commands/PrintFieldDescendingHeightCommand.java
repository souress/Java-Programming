package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, выводящей в консоль имя и рост элементов по убыванию.
 */
public class PrintFieldDescendingHeightCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 6767425643808819573L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        commandReceiver.printFieldDescendingHeight();
    }
}
