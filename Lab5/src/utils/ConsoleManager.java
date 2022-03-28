package utils;

import commands.*;
import commands.utils.*;
import main.MainLab5;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс управления и регистрацией команд консолью.
 */
public class ConsoleManager {
    public void startInteractiveMode() {
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker);
        CollectionManager.initHashSet();

        commandInvoker.register("help", new HelpCommand(commandReceiver));
        commandInvoker.register("add", new AddCommand(commandReceiver));
        commandInvoker.register("add_if_max", new AddIfMaxCommand(commandReceiver));
        commandInvoker.register("clear", new ClearCommand(commandReceiver));
        commandInvoker.register("execute_script", new ExecuteScriptCommand(commandReceiver));
        commandInvoker.register("exit", new ExitCommand(commandReceiver));
        commandInvoker.register("filter_starts_with_name", new FilterStartsWithNameCommand(commandReceiver));
        commandInvoker.register("info", new InfoCommand(commandReceiver));
        commandInvoker.register("print_field_descending_height", new PrintFieldDescendingHeightCommand(commandReceiver));
        commandInvoker.register("print_field_descending_passport_id", new PrintFieldDescendingPassportIdCommand(commandReceiver));
        commandInvoker.register("remove_by_id", new RemoveByIdCommand(commandReceiver));
        commandInvoker.register("remove_greater", new RemoveGreaterCommand(commandReceiver));
        commandInvoker.register("remove_lower", new RemoveLowerCommand(commandReceiver));
        commandInvoker.register("save", new SaveCommand(commandReceiver));
        commandInvoker.register("show", new ShowCommand(commandReceiver));
        commandInvoker.register("update", new UpdateCommand(commandReceiver));

        System.out.println("Количество команд: " + commandInvoker.getCommandMap().size());

        try(Scanner scanner = MainLab5.scanner) {
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (commandInvoker.getCommandMap().containsKey(command.trim().split(" ")[0]))
                    commandInvoker.executeCommand(command.trim().split(" "));
                else if (!command.trim().equals(""))
                    System.out.println(command + " не является командой");
            }
        } catch (NoSuchElementException exception) {
            System.out.println("Экстренная остановка программы!");
        }
    }
}