package commands;

import commands.serialized.SerializedCombinedCommand;
import commands.utils.CommandReceiver;
import data.Person;

import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс команды, обновляющей элемент по id.
 */
public class UpdateCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -3665081062463514576L;

    @Override
    public void execute(Object argObject, SocketChannel socket, String username) throws IOException {
        SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) argObject;
        String arg =  combinedCommand.getArgument();
        Person person = (Person) combinedCommand.getObject();
        if (arg.split(" ").length == 1) {
            CommandReceiver commandReceiver = new CommandReceiver();
            commandReceiver.update(arg, person, username);
        }
    }
}
