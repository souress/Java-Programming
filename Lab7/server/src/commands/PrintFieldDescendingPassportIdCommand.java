package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, выводящей в консоль имя и passportId элементов по убыванию.
 */
public class PrintFieldDescendingPassportIdCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -7171969609373110138L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        commandReceiver.printFieldDescendingPassportID();
    }
}
