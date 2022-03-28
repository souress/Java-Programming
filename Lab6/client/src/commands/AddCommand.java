package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, добавляющей элемент в коллекцию.
 */
public class AddCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = -3437100694030550244L;
    private CommandReceiver commandReceiver;

    public AddCommand(CommandReceiver commandReceiver) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.commandReceiver = commandReceiver;
    }

    public AddCommand() {}

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.add();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
