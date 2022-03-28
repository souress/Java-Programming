package commands;

import commands.utils.CommandReceiver;
import data.Person;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, удаляющей элементы, превышающих заданный.
 */
public class RemoveGreaterCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -4462244686864951066L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        String arg = argObject.toString();
        if (arg.split(" ").length == 1)
            System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        else {
            CommandReceiver commandReceiver = new CommandReceiver();
            commandReceiver.removeGreater((Person) argObject, username);
        }
    }
}
