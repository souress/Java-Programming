package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, оканчивающей работу приложения.
 */
public class ExitCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public ExitCommand(CommandReceiver commandReceiver) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.exit();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
