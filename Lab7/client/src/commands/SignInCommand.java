package commands;


import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

public class SignInCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -7237696736602519701L;
    private CommandReceiver commandReceiver;

    public SignInCommand(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public SignInCommand() {
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        commandReceiver.signIn();
    }

    @Override
    public String writeInfo() {
        return null;
    }
}

