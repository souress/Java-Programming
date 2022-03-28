package moves;

import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove {

    public Swagger(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon){
        Effect.confuse(pokemon);
        Effect attack_up_2 = new Effect().stat(Stat.ATTACK, 2);
        pokemon.addEffect(attack_up_2);
    }

    @Override
    protected String describe() {
        return "совершает Swagger\nПротивник растерян";
    }
}
