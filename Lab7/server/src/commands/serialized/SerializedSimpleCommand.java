package commands.serialized;

import commands.AbstractCommand;

import java.io.Serializable;

public class SerializedSimpleCommand implements Serializable {
    private static final long serialVersionUID = -1744862838020981517L;

    private final AbstractCommand command;
    private final String username;


    public SerializedSimpleCommand(AbstractCommand command, String username) {
        this.command = command;
        this.username = username;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public String getUsername() {
        return username;
    }
}
