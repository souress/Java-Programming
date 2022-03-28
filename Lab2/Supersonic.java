package moves;

import ru.ifmo.se.pokemon.*;


public class Supersonic extends StatusMove {
    public Supersonic(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon){
        Effect.confuse(pokemon);
    }

    @Override
    protected String describe() {
        return "совершает Supersonic\nПротивник растерян";
    }
}
