package mypokes;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Phione extends Pokemon {
    public Phione(String name, int level) {
        super(name, level);

        setType(Type.WATER);
        setStats(80, 80, 80, 80, 80,80);

        Double_Team double_team = new Double_Team(0, 0);
        Rest rest = new Rest(0, 0);
        Supersonic supersonic = new Supersonic(0, 55);
        Swagger swagger = new Swagger(0, 85);
        setMove(double_team, rest, supersonic, swagger);
    }
}
