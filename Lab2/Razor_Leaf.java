package moves;

import ru.ifmo.se.pokemon.*;

public class Razor_Leaf extends PhysicalMove {
    public Razor_Leaf(double pow, double acc) {
        super(Type.GRASS, pow, acc);
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        return 1d/8d;
    }

    @Override
    protected String describe() {
        return "совершает Razor Leaf";
    }
}
