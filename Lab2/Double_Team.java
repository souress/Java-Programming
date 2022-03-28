package moves;

import ru.ifmo.se.pokemon.*;

public class Double_Team extends StatusMove {

    public Double_Team(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        Effect evasion = new Effect().stat(Stat.EVASION, 1);
        pokemon.addEffect(evasion);
    }

    @Override
    protected String describe() {
        return "совершает Double Team";
    }
}
