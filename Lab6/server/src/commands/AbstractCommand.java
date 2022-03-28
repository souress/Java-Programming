package commands;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Абстрактный класс команд. На его основе создается остальные команды.
 */
public abstract class AbstractCommand {
    public abstract void execute(Object argObject, SocketChannel socket) throws IOException;
}
