package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, обновляющей элемент по id.
 */
public class UpdateCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = -3665081062463514576L;

    private CommandReceiver commandReceiver;

    public UpdateCommand(CommandReceiver commandReceiver) {
        super("update <id> {element}", "обновить значение элемента коллекции по id");
        this.commandReceiver = commandReceiver;
    }

    public UpdateCommand() {
    }

    @Override
    public void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length == 2) commandReceiver.update(args[1]);
        else System.out.println("Некорректное количество аргументов. Для справки напишите help.");
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
