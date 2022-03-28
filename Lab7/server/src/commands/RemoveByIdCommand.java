package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, удаляющей элемент из коллекции по id.
 */
public class RemoveByIdCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = 223245754741906405L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        String arg = argObject.toString();
        if (arg.split(" ").length == 1) {
            CommandReceiver commandReceiver = new CommandReceiver();
            commandReceiver.removeByID(arg, username);
        }
    }

}
