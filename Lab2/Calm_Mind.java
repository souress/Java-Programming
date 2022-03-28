package moves;

import ru.ifmo.se.pokemon.*;

public class Calm_Mind extends StatusMove {
    public Calm_Mind(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);
    }

    @Override
    protected  void applySelfEffects(Pokemon pokemon) {
        Effect special_attack_up = new Effect().stat(Stat.SPECIAL_ATTACK, 1);
        Effect special_defense_up = new Effect().stat(Stat.SPECIAL_DEFENSE, 1);
        pokemon.addEffect(special_attack_up);
        pokemon.addEffect(special_defense_up);
    }

    @Override
    protected String describe() {
        return "совершает Calm Mind";
    }
}
