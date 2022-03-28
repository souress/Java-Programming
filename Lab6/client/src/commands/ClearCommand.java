package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, очищающей коллекцию.
 */
public class ClearCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 8771837855713473493L;
    private CommandReceiver commandReceiver;

    public ClearCommand(CommandReceiver commandReceiver) {
        super("clear", "очистить коллекцию");
        this.commandReceiver = commandReceiver;
    }

    public ClearCommand() {
    }

    @Override
    public void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.clear();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
