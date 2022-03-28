package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, выводящей в консоль содержимого коллекции.
 */
public class ShowCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = -4611396263412415993L;

    private CommandReceiver commandReceiver;

    public ShowCommand(CommandReceiver commandReceiver) {
        super("show", "вывести все элементы коллекции");
        this.commandReceiver = commandReceiver;
    }

    public ShowCommand() {
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.show();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
