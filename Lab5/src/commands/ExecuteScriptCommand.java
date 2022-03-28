package commands;

import commands.utils.*;

import java.io.FileNotFoundException;

/**
 * Класс команды, выполняющей скрипт.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;


    public ExecuteScriptCommand(CommandReceiver commandReceiver) {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) throws StackOverflowError, FileNotFoundException {
        commandReceiver.execute_script(args);
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
