package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by robrinne on 18/03/2017.
 */

public class Voleur
{
    private Tuile position;

    public Voleur(Tuile t)
    {
        this.position = t;
    }

    public Tuile getPosition()
    {
        return this.position;
    }

    public void deplacer(Tuile t)
    {
        this.position = t;
    }

    // Verifie si le voleur est a la tuile placee en parametre
    public boolean tuileVoleur(Tuile t)
    {
        boolean b = false;

        if(this.getPosition() == t)
        {
            b = true;
        }

        return b;
    }

    // Methode qui est utilisee lorsque le joueur fait 7 aux des et qu'il choisit ou il place le voleur (joueur et la tuile ciblee en parametres)
    public void actionVoleur(Partie p, Joueur j, Tuile t)
    {
        // On deplace le voleur sur la tuile que le joueur a choisi
        deplacer(t);

        //On parcourt les joueurs et on retire aleatoirement la moitie des cartes a ceux qui ont plus de 7 cartes ressources en leur possession
        for(int i=0; i<p.getJoueurs().size(); i++)
        {
            if(p.getJoueurs().get(i) != j)
            {
                int nbCartesRessources = p.getJoueurs().get(i).getRessources().getRessources().length;

                if(nbCartesRessources > 7)
                {
                    for(int k=0; k<nbCartesRessources/2; k++)
                    {
                        // On tire un nombre aleatoire entre le nombre de cartes ressources restantes et 0
                        int r = (int)(Math.random()*((p.getJoueurs().get(i).getRessources().getRessources().length-1)-0) + 0);

                        // On retire la ressource à l'indice r
                        p.getJoueurs().get(i).getRessources().retirerRessource(j.getRessources().getRessources()[r], 1);
                    }
                }
            }
        }
    }

    // Vol d'une ressource aupres d'un des joueurs adjacents apres avoir deplace le voleur (et apres la methode actionVoleur ci-dessus)
    public void volJoueur(Joueur j, Joueur j2)
    {
        // On choisit une carte aleatoire
        int r = (int)(Math.random()*((j2.getRessources().getRessources().length-1)-0) + 0);
        // On stocke la valeur qui se trouve a cet indice dans une variable a
        int a = j2.getRessources().getRessources()[r];

        // On ajoute la carte volee au joueur
        j.getRessources().ajouterRessource(a, 1);

        // On enleve la carte au joueur 2
        j2.getRessources().retirerRessource(a, 1);
    }

}

