package mypokes;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Floette extends Flabebe {
    public Floette(String name, int level) {
        super(name, level);

        setType(Type.FAIRY);
        setStats(54, 45, 47, 75, 98,52);

        Confide confide = new Confide(0, 0);
        Razor_Leaf razor_leaf = new Razor_Leaf(55, 95);
        Moonblast moonblast = new Moonblast(95, 100);

        setMove(confide, moonblast, razor_leaf);
    }
}