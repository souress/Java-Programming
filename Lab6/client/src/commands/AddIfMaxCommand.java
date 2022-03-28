package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, добавляющей в коллекцию элемент, если он является наибольшим.
 */
public class AddIfMaxCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 3185682357961358964L;
    private CommandReceiver commandReceiver;

    public AddIfMaxCommand(CommandReceiver commandReceiver) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает " +
                                                                        "значение наибольшего элемента этой коллекции");
        this.commandReceiver = commandReceiver;
    }

    public AddIfMaxCommand() {
    }

    @Override
    public void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.addIfMax();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
