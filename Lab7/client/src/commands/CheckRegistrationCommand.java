package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

public class CheckRegistrationCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -4636441139160848883L;
    private CommandReceiver commandReceiver;

    public CheckRegistrationCommand(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public CheckRegistrationCommand() {
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        commandReceiver.checkRegistration(args[0]);
    }

    @Override
    public String writeInfo() {
        return null;
    }
}