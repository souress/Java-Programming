package commands.serialized;

import commands.AbstractCommand;

import java.io.Serializable;

public class SerializedObjectCommand implements Serializable {
    private static final long serialVersionUID = -8590656103008400936L;

    private final AbstractCommand command;
    private final Object object;

    public SerializedObjectCommand(AbstractCommand command, Object object) {
        this.command = command;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
