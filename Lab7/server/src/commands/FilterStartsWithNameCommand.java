package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, выводящей элементы коллекции, поля name которых начинаются с заданной подстроки.
 */
public class FilterStartsWithNameCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 3690940632799530338L;
    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        String name = argObject.toString();
        commandReceiver.filterStartsWithName(name);
    }
}
