package com.example.procrastinator.controler;

import android.content.Context;


import com.example.procrastinator.model.AccesDistant;
import com.example.procrastinator.model.Taches;
import com.example.procrastinator.view.ListeTaches;

import org.json.JSONArray;

import java.util.ArrayList;


public final class Controler {

    private static Controler instance = null;
    private static Taches tache;
    private static ArrayList<Taches> tabTache;
    private static String nomFictabClient = "saveTabClient";
    private static AccesDistant accesDistant;
    private static Context context;
    private ArrayList<Taches> lesTaches = new ArrayList<>();

    /**
     * contructeur privé
     */
    private Controler() {
        super();
    }


    // Ceci est un pattern Singleton qui permet de creer qu'une seule instance car si elle existe déja elle renvoi la même

    public static final Controler getInstance(Context contexte) {
        if (contexte != null) {
            Controler.context = contexte;
        }
        if (Controler.instance == null) {
            Controler.instance = new Controler();
         //   recupSerialize(contexte);
//            recupSerializeTabClients(contexte);
            accesDistant = new AccesDistant();
            accesDistant.envoi("getListeTaches", new JSONArray());

        }
        return Controler.instance;
    }

    public ArrayList<Taches> getLesTaches() {
        // System.out.println(lesTaches);
        return this.lesTaches;
    }


    public void setLesTaches(ArrayList<Taches> lesTaches) {
        this.lesTaches = lesTaches;
        System.out.println("passage setLesTaches" + lesTaches);
    }



    public void setListClient(Taches client) {
        Controler.tache = client;
        ((ListeTaches) context).recupListeTaches();
    }


    public void creerTache(String  titre, String categorie, String urgence, String dateLimite, Context context, String commentaire){
        tache = new Taches(titre, categorie, urgence, dateLimite, context, commentaire);

        System.out.println(tache);

        lesTaches.add(tache);

        accesDistant.envoi("enregTache", tache.convertToJSONArrayAddClient());

     /*   Taches.addClient(tache);
        tabTache = Taches.getArray_clients();*/

        //Serializer.serialize(nomFic, tache, contexte);
        //Serializer.serializeTabClients(nomFictabClient, tabTache, contexte);
    }

    public void modifierTache(int num_tache, String  titre, String categorie, String urgence, String dateLimite, Context context, String commentaire) {

        tache = new Taches(num_tache, titre, categorie, urgence, dateLimite, context, commentaire);


        accesDistant.envoi("modifTache", tache.convertToJSONArrayModifClient());

    }




//Taches//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList getTabClients() {
        if (tabTache == null) {
            System.out.println("tabclient est null dans getabclients du controler");
            return null;
        } else {
            return Taches.getArray_clients();
        }
    }

    public String getNom_client_1() {

        if (tache == null) {
            return null;
        } else {
            System.out.println(tache.toString());
            return tache.getTitre();
        }
    }

    public String getUrgence() {
        if (tache == null) {
            return null;
        } else {
            return tache.getUrgence();
        }
    }

    public String getDateLimite() {
        if (tache == null) {
            return null;
        } else {
            return tache.getDateLimite();
        }
    }

    public String getTel_client_1() {
        if (tache == null) {
            return null;
        } else {
            return tache.getDateLimite();
        }
    }




 /*   //Taches//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getTitre() {
        return tache.getTitre();
    }

    public String getUrgence() {
        return tache.getUrgence();
    }

    public int getDateLimite() {
        return tache.getDateLimite();
    }

    public int getDateLimite() {
        return tache.getDateLimite();
    }

    public String getRepet() {
        return tache.getRepet();
    }

    public String getCommentaire() {
        return tache.getCommentaire();
    }

    public String getPrenom_client_2() {
        return tache.getPrenom_client_2();
    }

    public int getSexe_client_2() {
        return tache.getSexe_client_2();
    }

    public int getTel_client_2() {
        return tache.getTel_client_2();
    }

    public String getAdresse_client_2() {
        return tache.getAdresse_client_2();
    }

    public ArrayList<Polygon> getPolygone_client() {
        return tache.getPolygone_client();
    }

    public ArrayList<ArrayList> getTab_polygone_client() {
        return tache.getTab_polygone_client();
    } */

    //Terrain/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
