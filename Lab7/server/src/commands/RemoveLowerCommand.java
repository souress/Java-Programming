package commands;

import commands.utils.CommandReceiver;
import data.Person;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, удаляющей элементы, меньше заданного.
 */
public class RemoveLowerCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = 4222128891483971391L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        String arg = argObject.toString();
        if (arg.split(" ").length == 1)
            System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        else{
            CommandReceiver commandReceiver = new CommandReceiver();
            commandReceiver.removeLower((Person) argObject, username);
        }
    }
}
