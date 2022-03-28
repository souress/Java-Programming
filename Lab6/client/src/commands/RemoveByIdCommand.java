package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, удаляющей элемент из коллекции по id.
 */
public class RemoveByIdCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 223245754741906405L;

    private CommandReceiver commandReceiver;

    public RemoveByIdCommand(CommandReceiver commandReceiver) {
        super("remove_by_id <id>", "удалить элемент из коллекции по его id");
        this.commandReceiver = commandReceiver;
    }

    public RemoveByIdCommand() {
    }

    @Override
    public void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length == 2) commandReceiver.removeByID(args[1]);
        else System.out.println("Некорректное количество аргументов. Для справки напишите help.");
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
