package com.mygdx.game.model;

import java.util.Random;

/**
 * Created by XXIII on 15/03/2017.
 */

public class De {

    public static final int MIN = 1;
    public static final int MAX = 6;

    private Random r;
    private int valeur;

    public De() {
        r = new Random();
        valeur = 0;
    }

    public int lancer()
    {
        valeur = MIN + r.nextInt(MAX - MIN + 1);
        return valeur;
    }

    public String toString() {
        return "Valeur du dé : "+valeur;
    }

    public int getValeur() {
        return valeur;
    }
}
