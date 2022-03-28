package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, удаляющей элементы, превышающих заданный.
 */
public class RemoveGreaterCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = -4462244686864951066L;

    private CommandReceiver commandReceiver;

    public RemoveGreaterCommand(CommandReceiver commandReceiver) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        this.commandReceiver = commandReceiver;
    }

    public RemoveGreaterCommand() {
    }

    @Override
    public void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.removeGreater();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
