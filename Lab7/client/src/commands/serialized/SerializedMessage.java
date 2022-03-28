package commands.serialized;

import java.io.Serializable;

public class SerializedMessage implements Serializable {
    private static final long serialVersionUID = 633733203058918781L;

    private final String message;

    public SerializedMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
