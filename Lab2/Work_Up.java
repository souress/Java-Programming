package moves;

import ru.ifmo.se.pokemon.*;

public class Work_Up extends StatusMove {

    public Work_Up(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon){
        Effect special_attack_up = new Effect().stat(Stat.SPECIAL_ATTACK, 1);
        Effect attack_up = new Effect().stat(Stat.ATTACK, 1);
        pokemon.addEffect(special_attack_up);
        pokemon.addEffect(attack_up);
    }

    @Override
    protected String describe() {
        return "совершает Work Up";
    }
}
