package commands.serialized;

import commands.AbstractCommand;

import java.io.Serializable;

public class SerializedSimpleCommand implements Serializable {
    private static final long serialVersionUID = -1744862838020981517L;

    private final AbstractCommand command;

    public SerializedSimpleCommand(AbstractCommand command) {
        this.command = command;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
