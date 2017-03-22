package com.mygdx.game.model;

/**
 * Created by XXIII on 15/03/2017.
 */

public class Constantes {
    /*
        Constantes pour les types de ressources
     */
    public static final int MONTANT_RESSOURCE_MAXIMUM = 19; // montant maximum de ressources par type

    public static final int BOIS = 1;
    public static final int LAINE = 2;
    public static final int BLE = 3;
    public static final int ARGILE = 4;
    public static final int PIERRE = 5;

    public static final int NUMERO_RESSOURCE_MIN = 1;
    public static final int NUMERO_RESSOURCE_MAX = 5;
    public static final int TAILLE_TABLEAU_RESSOURCE = NUMERO_RESSOURCE_MAX + 1; // sert pour la taille de tableau, on ne travaille pas avec l'indice 0 du talbeau

    public static String nomRessource(int ressource) {
        String nom;
        switch (ressource) {
            case BOIS:
                nom = "bois";  break;
            case LAINE:
                nom = "laine";   break;
            case BLE:
                nom = "ble"; break;
            case ARGILE:
                nom = "argile"; break;
            case PIERRE:
                nom = "pierre";  break;
            default :
                nom = null; // ne doit pas se produire
        }
        return nom;
    }

    /*
        Constantes pour les structures
     */
    public static final float STRUCTURE_DELTA_X = 24.0f; // largeur d'un SiteConstruction /2
    public static final float STRUCTURE_DELTA_Y = 24.0f; // hauteur d'un SiteConstruction /2

    public static final int MONTANT_VILLE_MAXIMUM = 4;
    public static final int MONTANT_COLONIE_MAXIMUM = 5;
    public static final int MONTANT_ROUTE_MAXIMUM = 15;

    public static final int VILLE = 1;
    public static final int COLONIE = 2;
    public static final int ROUTE = 3;

    public static final int POINTS_VICTOIRE_VILLE = 2;
    public static final int POINTS_VICTOIRE_COLONIE = 1;
    public static final int POINTS_VICTOIRE_ROUTE = 0;

    public static final int NUMERO_STRUCTURE_MIN = 1;
    public static final int NUMERO_STRUCTURE_MAX = 3;
    public static final int TAILLE_TABLEAU_STRUCTURE = NUMERO_STRUCTURE_MAX + 1; // sert pour la taille de tableau, on ne travaille pas avec l'indice 0 du talbeau

    public static String nomStructure(int strucure) {
        String nom;
        switch (strucure) {
            case VILLE:
                nom = "ville";  break;
            case COLONIE:
                nom = "colonie";   break;
            case ROUTE:
                nom = "route"; break;
            default :
                nom = null; // ne doit pas se produire
        }
        return nom;
    }

    public static final int ROUTE_ORIENTATION_MIN = 1;
    public static final int ROUTE_ORIENTATION_MAX = 4;
    public static final int ROUTE_ORIENTATION_AUCUNE = 0; // n'est pas une route
    public static final int ROUTE_ORIENTATION_HORIZONTALE = 1;
    public static final int ROUTE_ORIENTATION_VERTICALE = 2;
    public static final int ROUTE_ORIENTATION_CROISSANTE = 3;
    public static final int ROUTE_ORIENTATION_DECROISSANTE = 4;

    /*
        Constantes des coûts de construction
     */
    // indice 0 non utilisé, bois, laine, blé, argile, minerai
    public static final int[] getCoutConstructionVille() {
        int[] tableauCoutConstructionVille = new int[TAILLE_TABLEAU_RESSOURCE];
        tableauCoutConstructionVille[BOIS] = 0;
        tableauCoutConstructionVille[LAINE] = 0;
        tableauCoutConstructionVille[BLE] = 2;
        tableauCoutConstructionVille[ARGILE] = 0;
        tableauCoutConstructionVille[PIERRE] = 3;
        return tableauCoutConstructionVille;
    }

    public static final int[] getCoutConstructionColonie() {
        int[] tableauCoutConstructionColonie = new int[TAILLE_TABLEAU_RESSOURCE];
        tableauCoutConstructionColonie[BOIS] = 1;
        tableauCoutConstructionColonie[LAINE] = 1;
        tableauCoutConstructionColonie[BLE] = 1;
        tableauCoutConstructionColonie[ARGILE] = 1;
        tableauCoutConstructionColonie[PIERRE] = 0;
        return tableauCoutConstructionColonie;
    }

    public static final int[] getCoutConstructionRoute() {
        int[] tableauCoutConstructionRoute = new int[TAILLE_TABLEAU_RESSOURCE];
        tableauCoutConstructionRoute[BOIS] = 1;
        tableauCoutConstructionRoute[LAINE] = 0;
        tableauCoutConstructionRoute[BLE] = 0;
        tableauCoutConstructionRoute[ARGILE] = 1;
        tableauCoutConstructionRoute[PIERRE] = 0;
        return tableauCoutConstructionRoute;
    }

    public static final int[] getCoutCarteDeveloppement() {
        int[] tableauCoutCarteDeveloppement = new int[TAILLE_TABLEAU_RESSOURCE];
        tableauCoutCarteDeveloppement[BOIS] = 0;
        tableauCoutCarteDeveloppement[LAINE] = 1;
        tableauCoutCarteDeveloppement[BLE] = 1;
        tableauCoutCarteDeveloppement[ARGILE] = 0;
        tableauCoutCarteDeveloppement[PIERRE] = 1;
        return tableauCoutCarteDeveloppement;
    }


}
