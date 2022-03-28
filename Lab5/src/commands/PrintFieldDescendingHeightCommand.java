package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, выводящей в консоль имя и рост элементов по убыванию.
 */
public class PrintFieldDescendingHeightCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public PrintFieldDescendingHeightCommand(CommandReceiver commandReceiver) {
        super("print_field_descending_height", "вывести значения поля height всех элементов в порядке " +
                                                                                                            "убывания");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.printFieldDescendingHeight();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
