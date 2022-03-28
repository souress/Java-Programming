package mypokes;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Florges extends Floette {
    public Florges(String name, int level) {
        super(name, level);

        setType(Type.FAIRY);
        setStats(78, 65, 68, 112, 154,75);

        Confide confide = new Confide(0, 0);
        Razor_Leaf razor_leaf = new Razor_Leaf(55, 95);
        Moonblast moonblast = new Moonblast(95, 100);
        Calm_Mind calm_mind = new Calm_Mind(0, 0);

        setMove(confide, moonblast, razor_leaf, calm_mind);
    }
}