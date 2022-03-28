package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, очищающей коллекцию.
 */
public class ClearCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public ClearCommand(CommandReceiver commandReceiver) {
        super("clear", "очистить коллекцию");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.clear();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
