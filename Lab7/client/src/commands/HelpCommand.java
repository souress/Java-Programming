package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, выводящей в консоль информацию о вссех доступных командах.
 */
public class HelpCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public HelpCommand(CommandReceiver commandReceiver) {
        super("help", "вывести справку по доступным командам");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.help();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
