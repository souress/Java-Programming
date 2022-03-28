package commands.serialized;

import commands.AbstractCommand;

import java.io.Serializable;

public class SerializedArgumentCommand implements Serializable {
    private static final long serialVersionUID = 7573435200919440967L;

    private final AbstractCommand command;
    private final String arg;
    private String username;

    public SerializedArgumentCommand(AbstractCommand command, String arg, String username) {
        this.command = command;
        this.arg = arg;
        this.username = username;
    }

    public SerializedArgumentCommand(AbstractCommand command, String arg) {
        this.command = command;
        this.arg = arg;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public String getUsername() {
        return username;
    }
}
