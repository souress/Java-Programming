package commands.serialized;

import commands.AbstractCommand;

import java.io.Serializable;

public class SerializedObjectCommand implements Serializable {
    private static final long serialVersionUID = -8590656103008400936L;

    private final AbstractCommand command;
    private final Object object;
    private final String username;

    public SerializedObjectCommand(AbstractCommand command, Object object, String username) {
        this.command = command;
        this.object = object;
        this.username = username;
    }

    public Object getObject() {
        return object;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
