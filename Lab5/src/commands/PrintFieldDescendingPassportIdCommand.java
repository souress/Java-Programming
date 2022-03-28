package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, выводящей в консоль имя и passportId элементов по убыванию.
 */
public class PrintFieldDescendingPassportIdCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public PrintFieldDescendingPassportIdCommand(CommandReceiver commandReceiver) {
        super("print_field_descending_passport_id", "вывести значения поля passportID всех элементов в" +
                                                                                                    " порядке убывания");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.printFieldDescendingPassportID();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
