package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс команды, выводящей в консоль имя и passportId элементов по убыванию.
 */
public class PrintFieldDescendingPassportIdCommand extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = -7171969609373110138L;

    private CommandReceiver commandReceiver;

    public PrintFieldDescendingPassportIdCommand(CommandReceiver commandReceiver) {
        super("print_field_descending_passport_id", "вывести значения поля passportID всех элементов в" +
                                                                                                    " порядке убывания");
        this.commandReceiver = commandReceiver;
    }

    public PrintFieldDescendingPassportIdCommand() {
    }

    @Override
    public void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length > 1) System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        commandReceiver.printFieldDescendingPassportID();
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
