package moves;

import ru.ifmo.se.pokemon.*;

public class Moonblast extends SpecialMove {

    public Moonblast(double pow, double acc) {
        super(Type.FAIRY, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon){
        if (Math.random() <= 0.3)
            pokemon.addEffect(new Effect().stat(Stat.SPECIAL_ATTACK, -1));
    }

    @Override
    protected String describe() {
        return "совершает Moonblast";
    }
}