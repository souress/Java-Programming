package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, выводящей в консоль имя и рост элементов по убыванию.
 */
public class PrintFieldDescendingHeightCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 6767425643808819573L;

    private CommandReceiver commandReceiver;

    public PrintFieldDescendingHeightCommand(CommandReceiver commandReceiver) {
        super("print_field_descending_height", "вывести значения поля height всех элементов в порядке " +
                                                                                                            "убывания");
        this.commandReceiver = commandReceiver;
    }

    public PrintFieldDescendingHeightCommand() {
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.printFieldDescendingHeight();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
