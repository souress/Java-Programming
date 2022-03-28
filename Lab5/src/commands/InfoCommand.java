package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, выводящей в консоль информацию о коллекции.
 */
public class InfoCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public InfoCommand(CommandReceiver commandReceiver) {
        super("info", "вывести информацию о коллекции");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.info();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
