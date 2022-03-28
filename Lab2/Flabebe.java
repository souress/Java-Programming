package mypokes;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Flabebe extends Pokemon {
    public Flabebe(String name, int level) {
        super(name, level);

        setType(Type.FAIRY);
        setStats(44, 38, 39, 61, 79,42);

        Confide confide = new Confide(0, 0);
        Moonblast moonblast = new Moonblast(95, 100);

        setMove(confide, moonblast);
    }
}
