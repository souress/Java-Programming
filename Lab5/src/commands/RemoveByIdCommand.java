package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, удаляющей элемент из коллекции по id.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public RemoveByIdCommand(CommandReceiver commandReceiver) {
        super("remove_by_id <id>", "удалить элемент из коллекции по его id");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) commandReceiver.removeByID(args[1]);
        else System.out.println("Некорректное количество аргументов. Для справки напишите help.");
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
