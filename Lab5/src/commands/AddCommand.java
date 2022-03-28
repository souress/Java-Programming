package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, добавляющей элемент в коллекцию.
 */
public class AddCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public AddCommand(CommandReceiver commandReceiver) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.add();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
