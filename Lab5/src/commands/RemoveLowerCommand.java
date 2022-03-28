package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, удаляющей элементы, меньше заданного.
 */
public class RemoveLowerCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public RemoveLowerCommand(CommandReceiver commandReceiver) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.removeLower();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
