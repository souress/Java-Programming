package utils.CommandHandler;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import commands.AbstractCommand;
import commands.serialized.SerializedArgumentCommand;
import commands.serialized.SerializedCombinedCommand;
import commands.serialized.SerializedObjectCommand;
import commands.serialized.SerializedSimpleCommand;

public class Decrypting {
    private final SocketChannel socketChannel;

    public Decrypting(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public void decrypt(Object o) throws IOException {
        if (o instanceof SerializedSimpleCommand) {
            SerializedSimpleCommand simpleCommand = (SerializedSimpleCommand) o;
            AbstractCommand command = simpleCommand.getCommand();
            String arg = "";
            command.execute(arg, socketChannel);
        }

        if (o instanceof SerializedArgumentCommand) {
            SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o;
            AbstractCommand command = argumentCommand.getCommand();
            String arg = argumentCommand.getArg();
            command.execute(arg, socketChannel);
        }

        if (o instanceof SerializedObjectCommand) {
            SerializedObjectCommand objectCommand = (SerializedObjectCommand) o;
            AbstractCommand command = objectCommand.getCommand();
            Object arg = objectCommand.getObject();
            command.execute(arg, socketChannel);
        }

        if (o instanceof SerializedCombinedCommand) {
            SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
            AbstractCommand command = combinedCommand.getCommand();
            command.execute(combinedCommand, socketChannel);
        }
    }
}
