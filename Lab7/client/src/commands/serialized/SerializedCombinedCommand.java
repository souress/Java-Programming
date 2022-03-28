package commands.serialized;

import commands.AbstractCommand;
import java.io.Serializable;

public class SerializedCombinedCommand implements Serializable {
    private static final long serialVersionUID = -8821033542358210150L;

    private final AbstractCommand command;
    private final Object object;
    private final String argument;
    private final String username;

    public SerializedCombinedCommand(AbstractCommand command, Object object, String arg, String username) {
        this.command = command;
        this.object = object;
        this.argument = arg;
        this.username = username;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public Object getObject() {
        return object;
    }

    public String getArgument() {
        return argument;
    }
}
