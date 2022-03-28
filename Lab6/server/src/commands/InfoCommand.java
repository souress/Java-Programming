package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, выводящей в консоль информацию о коллекции.
 */
public class InfoCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -4417738869974566532L;

    @Override
    public void execute(Object argObject, SocketChannel socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        commandReceiver.info();
    }
}
