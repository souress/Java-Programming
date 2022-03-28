package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, выводящей в консоль содержимого коллекции.
 */
public class ShowCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public ShowCommand(CommandReceiver commandReceiver) {
        super("show", "вывести все элементы коллекции");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.show();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
