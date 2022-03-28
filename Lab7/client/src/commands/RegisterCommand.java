package commands;

import commands.utils.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

public class RegisterCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = -9213103951696798922L;
    private CommandReceiver commandReceiver;

    public RegisterCommand(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public RegisterCommand(){
    }

    @Override
    public void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        commandReceiver.register();
    }

    @Override
    public String writeInfo() {
        return null;
    }
}
