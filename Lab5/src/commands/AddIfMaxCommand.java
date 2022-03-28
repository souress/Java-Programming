package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, добавляющей в коллекцию элемент, если он является наибольшим.
 */
public class AddIfMaxCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public AddIfMaxCommand(CommandReceiver commandReceiver) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает " +
                                                                        "значение наибольшего элемента этой коллекции");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.addIfMax();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
