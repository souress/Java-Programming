package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, выводящей элементы коллекции, поля name которых начинаются с заданной подстроки.
 */
public class FilterStartsWithNameCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public FilterStartsWithNameCommand(CommandReceiver commandReceiver) {
        super("filter_starts_with_name <name>", "вывести элементы, значение поля name которых начинается" +
                                                                                               " с заданной подстроки");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) commandReceiver.filterStartsWithName(args[1]);
        else System.out.println("Некорректное количество аргументов. Для справки напишите help.");
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
