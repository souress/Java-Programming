package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, выводящей в консоль информацию о коллекции.
 */
public class InfoCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -4417738869974566532L;

    private CommandReceiver commandReceiver;

    public InfoCommand(CommandReceiver commandReceiver) {
        super("info", "вывести информацию о коллекции");
        this.commandReceiver = commandReceiver;
    }

    public InfoCommand() {
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.info();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
