package commands;

import commands.utils.CommandReceiver;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Класс команды, сохраняющей коллекцию.
 */
public class SaveCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public SaveCommand(CommandReceiver commandReceiver) {
        super("save", "сохранить коллекцию в файл");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1)
            System.out.println("Слишком много аргументов, команда приведена к базовому формату.");
        try {
            commandReceiver.save();
        } catch (ParserConfigurationException | TransformerException | IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
