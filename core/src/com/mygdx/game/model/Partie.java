package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Vector;

import static com.mygdx.game.model.Constantes.ARGILE;
import static com.mygdx.game.model.Constantes.BLE;

/**
 * Created by robrinne on 18/03/2017.
 */

public class Partie
{
    private ArrayList<Joueur> joueurs;
    private De de1, de2;
    private Voleur voleur;
    private Plateau plateau;

    public Partie(int nbJoueurs)
    {
        joueurs = new ArrayList<Joueur>();

        for(int i=0; i<nbJoueurs; i++)
        {
            joueurs.add(new Joueur("couleur"));
        }

        de1 = new De();
        de2 = new De();
        plateau = new Plateau();

        voleur = new Voleur(plateau.getPositionDesert());
    }

    public void tourDeJeu(Joueur jou)
    {
        boolean continuer = true;
        int a = de1.lancer();
        int b = de2.lancer();
        int sommeDes = a + b;

        if(sommeDes != 7)
        {
            for(int i=0; i<joueurs.size(); i++)
            {
                donnerRessourcesAuxJoueurs(joueurs.get(i), sommeDes);
            }
        }
        else
        {
            // Le joueur choisit une tuile ou deplacer le voleur
            // A modeliser
            Tuile t = plateau.getListeTuiles().get(0);
            // On lance la methode qui modelise le deplacement du voleut et la suppression des cartes en trop des joueurs
            voleur.actionVoleur(this, jou, t);

            // Le joueur choisit qui voler parmis les joueurs qui sont adjacents a la tuile
            voleur.volJoueur(jou, joueurs.get(1));
        }

        // jou.afficherRessources();

        while(continuer)
        {
            // On attend le declenchement d'un event listener

           continuer = choisirAction(jou);
        }
    }

    public boolean choisirAction(Joueur j)
    {
        boolean continuerTour = true;

        // System.out.println("Action ? 1: construire 2: echangerJoueur 3: echangerPort 4: Fin de tour");
        // Scanner sc = new Scanner(System.in);
        // int choix = sc.nextInt();

        // Selon le listener que declenche le joueur on fait des actions differentes
        // exemple choix = 1
        int choix = 1;

        switch(choix)
        {
            case 1 : construire(j);
                break;
            case 2 : echangerAvecJoueur(j);
                break;
            case 3 : echangerAvecPort(j);
                break;
            case 4 : continuerTour = false;
                break;
            default : continuerTour = false;System.out.println("Erreur");
                break;
        }
        return continuerTour;
    }

    public void construire(Joueur j)
    {
        // Un attente de clic


        // Selectionner le batiment que l'on veut construire : methode qui renvoie le type de batiment que l'on veut construire
        // String bat = batimentAConstruire();
        String bat = "";
        boolean constructible = false;
        //Afficher ce qu'il peut construire
        if(Structure.estConstructible(j.getRessources(),"ville"))
        {
            System.out.println("Ville est constructible");
            constructible = true;
        }
        if(Structure.estConstructible(j.getRessources(),"colonie"))
        {
            System.out.println("Colonie est constructible");
            constructible = true;
        }
        if(Structure.estConstructible(j.getRessources(),"route"))
        {
            System.out.println("Route est constructible");
            constructible = true;
        }

        // Le joueur choisit ce qu'il veut construire
        // A modeliser
        // exemple :
        bat = "ville";

        // Retirer les ressources
        if(bat.equals("ville") && Structure.estConstructible(j.getRessources(),"ville"))
        {
            // Le joueur choisit le site de construction
            // A modeliser
            // Exemple
            Vector2 v = new Vector2();
            SiteConstruction sc = new SiteConstruction(v);

            // La ville est construite a l'emplacement selectionne
            j.construireVille(sc);

            //ajouter le batiment au joueur
            j.acheter(Constantes.getCoutConstructionVille());
            j.ajouterPointVictoire(2);
        }
        else if (bat.equals("colonie") && Structure.estConstructible(j.getRessources(),"colonie"))
        {
            // Le joueur choisit le site de construction
            // A modeliser
            // Exemple
            Vector2 v = new Vector2();
            SiteConstruction sc = new SiteConstruction(v);

            // La ville est construite a l'emplacement selectionne
            j.construireColonie();

            //ajouter le batiment au joueur
            j.acheter(Constantes.getCoutConstructionVille());
            j.ajouterPointVictoire(1);
        }
        else if(bat.equals("route") && Structure.estConstructible(j.getRessources(),"route"))
        {
            // Le joueur choisit le site de construction
            // A modeliser
            // Exemple
            Vector2 v = new Vector2();
            SiteConstruction sc = new SiteConstruction(v);

            // La ville est construite a l'emplacement selectionne
            j.construireRoute();

            //ajouter le batiment au joueur
            j.acheter(Constantes.getCoutConstructionVille());
        }
    }

    public void echangerAvecJoueur(Joueur j)
    {
        // Le joueur choisit avec quel autre joueur il veut echanger
        // A modeliser
        // Exemple :
        Joueur j2 = new Joueur("Exemple");

        // Le joueur choisit le nombre de ressources qu'il veut echanger et contre quelles ressources
        // A modeliser
        // Exemple :
        j.getRessources().retirerRessource(Constantes.BOIS, 2);
        j2.getRessources().ajouterRessource(Constantes.BLE, 2);
    }

    public void echangerAvecPort(Joueur j)
    {
        // A modeliser
    }

    public void donnerRessourcesAuxJoueurs(Joueur jou, int sommeDes)
    {
        // A modeliser
    }

    public void getTuileVoleur()
    {
        System.out.println(voleur.getPosition());
    }

    public ArrayList<Joueur> getJoueurs()
    {
        return joueurs;
    }

    public void setJoueurs(ArrayList<Joueur> joueurs)
    {
        this.joueurs = joueurs;
    }

    public De getDe1()
    {
        return de1;
    }

    public void setDe1(De de1)
    {
        this.de1 = de1;
    }

    public De getDe2()
    {
        return de2;
    }

    public void setDe2(De de2)
    {
        this.de2 = de2;
    }

    public Voleur getVoleur()
    {
        return voleur;
    }

    public void setVoleur(Voleur voleur)
    {
        this.voleur = voleur;
    }

    public Plateau getPlateau()
    {
        return plateau;
    }

    public void setPlateau(Plateau plateau)
    {
        this.plateau = plateau;
    }
}
