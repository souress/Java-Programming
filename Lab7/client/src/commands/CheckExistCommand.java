package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

public class CheckExistCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -3773218210331586469L;
    private CommandReceiver commandReceiver;

    public CheckExistCommand(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public CheckExistCommand() {

    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        commandReceiver.checkExist(args[0]);
    }

    @Override
    public String writeInfo() {
        return null;
    }
}
