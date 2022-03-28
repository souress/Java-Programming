package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, выводящей элементы коллекции, поля name которых начинаются с заданной подстроки.
 */
public class FilterStartsWithNameCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 3690940632799530338L;

    private CommandReceiver commandReceiver;

    public FilterStartsWithNameCommand(CommandReceiver commandReceiver) {
        super("filter_starts_with_name <name>", "вывести элементы, значение поля name которых начинается" +
                                                                                               " с заданной подстроки");
        this.commandReceiver = commandReceiver;
    }

    public FilterStartsWithNameCommand() {
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length == 2) commandReceiver.filterStartsWithName(args[1]);
        else System.out.println("Некорректное количество аргументов. Для справки напишите help.");
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
