package mypokes;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Eevee extends Pokemon {
    public Eevee(String name, int level) {
        super(name, level);

        setType(Type.NORMAL);
        setStats(55, 55, 50, 45, 65,55);

        Rest rest = new Rest(0, 0);
        Confide confide = new Confide(0, 0);
        Work_Up work_up = new Work_Up(0, 0);

        setMove(rest, confide, work_up);
    }
}
