package commands;

import java.io.IOException;

/**
 * Абстрактный класс команд. На его основе создается остальные команды.
 */
public abstract class AbstractCommand {
    private String name;
    private String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public AbstractCommand() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException;

    public abstract String writeInfo();

    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }
}
