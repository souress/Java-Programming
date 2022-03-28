package com.pokemons;

import mypokes.*;
import ru.ifmo.se.pokemon.*;

public class Pokegram {

    public static void main(String[] args) {
        Battle b = new Battle();
        Phione phione = new Phione("Фион", 1);
        Eevee eevee = new Eevee("Иви", 1);
        Vaporeon vaporeon = new Vaporeon("Вапореон", 2);
        Flabebe flabebe = new Flabebe("Флабэбэ", 1);
        Floette floette = new Floette("Флоэтт", 2);
        Florges florges = new Florges("Флоргес", 3);

        b.addAlly(phione);
        b.addAlly(eevee);
        b.addAlly(vaporeon);

        b.addFoe(flabebe);
        b.addFoe(floette);
        b.addFoe(florges);

        b.go();
    }
}
