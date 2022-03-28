package moves;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {

    public Rest(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        Effect sleep_2_turns = new Effect().turns(2).condition(Status.SLEEP);
        pokemon.addEffect(sleep_2_turns);
        pokemon.restore();
    }

    @Override
    protected String describe() {
        return "совершает Rest\nВосстанавливает здоровье и засыпает на два хода";
    }
}