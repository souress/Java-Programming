package moves;

import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove {

    public Confide(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon){
        Effect special_attack_low = new Effect().stat(Stat.SPECIAL_ATTACK, -1);
        pokemon.addEffect(special_attack_low);
    }

    @Override
    protected String describe() {
        return "совершает Confide";
    }
}
