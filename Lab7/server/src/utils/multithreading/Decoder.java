package utils.multithreading;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import commands.AbstractCommand;
import commands.serialized.*;

import static main.Main.logger;

public class Decoder {
    private final Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 3);

    public Decoder() {
    }

    public void decrypt(SocketChannel socketChannel, Object o) {
        executor.execute(new DecoderRunnable(socketChannel, o));
    }

    private static class DecoderRunnable implements Runnable {
        private final SocketChannel socketChannel;
        private final Object o;

        private DecoderRunnable(SocketChannel socketChannel, Object o) {
            this.socketChannel = socketChannel;
            this.o = o;
        }

        @Override
        public void run() {
            if (o instanceof SerializedSimpleCommand) {
                try {
                    SerializedSimpleCommand simpleCommand = (SerializedSimpleCommand) o;
                    AbstractCommand command = simpleCommand.getCommand();
                    String username = simpleCommand.getUsername();
                    String arg = "";
                    logger.debug(String.format("Получена команда \"%s\" от пользователя %s", simpleCommand.getCommand(), username));
                    command.execute(arg, socketChannel, username);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

            if (o instanceof SerializedArgumentCommand) {
                try {
                    SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o;
                    AbstractCommand command = argumentCommand.getCommand();
                    String username = argumentCommand.getUsername();
                    String arg = argumentCommand.getArg();
                    logger.debug(String.format("Получена команда \"%s %s\" от пользователя %s", argumentCommand.getCommand(), arg, username));
                    command.execute(arg, socketChannel, username);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

            if (o instanceof SerializedObjectCommand) {
                try {
                    SerializedObjectCommand objectCommand = (SerializedObjectCommand) o;
                    AbstractCommand command = objectCommand.getCommand();
                    String username = objectCommand.getUsername();
                    Object arg = objectCommand.getObject();
                    logger.debug(String.format("Получена команда \"%s %s\" от пользователя %s", objectCommand.getCommand(), arg.toString(), username));
                    command.execute(arg, socketChannel, username);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

            if (o instanceof SerializedCombinedCommand) {
                try {
                    SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
                    AbstractCommand command = combinedCommand.getCommand();
                    String username = combinedCommand.getUsername();
                    logger.debug(String.format("Получена команда \"%s %s %s\" от пользователя %s",
                            combinedCommand.getCommand(), combinedCommand.getArgument(), combinedCommand.getArgument(), username));
                    command.execute(combinedCommand, socketChannel, username);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
