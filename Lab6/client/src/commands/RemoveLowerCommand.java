package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, удаляющей элементы, меньше заданного.
 */
public class RemoveLowerCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 4222128891483971391L;

    private CommandReceiver commandReceiver;

    public RemoveLowerCommand(CommandReceiver commandReceiver) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.commandReceiver = commandReceiver;
    }

    public RemoveLowerCommand() {
    }

    @Override
    public void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.removeLower();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
