package mypokes;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Vaporeon extends Eevee {
    public Vaporeon(String name, int level) {
        super(name, level);

        setType(Type.WATER);
        setStats(130, 65, 60, 110, 95,65);

        Rest rest = new Rest(0, 0);
        Confide confide = new Confide(0, 0);
        Work_Up work_up = new Work_Up(0, 0);
        Scald scald = new Scald(80, 100);

        setMove(rest, confide, work_up, scald);
    }
}
