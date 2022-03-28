package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, удаляющей элементы, превышающих заданный.
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public RemoveGreaterCommand(CommandReceiver commandReceiver) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.removeGreater();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
