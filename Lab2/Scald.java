package moves;

import ru.ifmo.se.pokemon.*;

public class Scald extends SpecialMove {
    public Scald(double pow, double acc) {
        super(Type.WATER, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() <= 0.3)
            Effect.burn(pokemon);
            System.out.println("Противник подожжён");
    }

    @Override
    protected String describe() {
        return "совершает Scald";
    }
}
