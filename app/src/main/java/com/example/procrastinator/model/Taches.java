package com.example.procrastinator.model;

import android.content.Context;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Taches implements Serializable {


    private String titre;
    private String categorie;
    private String urgence;
    private String dateLimite;
    private int num_tache;
    private String duree;

    private String commentaire_tache;
    private Context contexte;


    private static ArrayList<Taches> array_clients = new ArrayList<Taches>();


    public Taches(String titre, String categorie, String urgence, String duree, String dateLimite, Context contexte, String commentaire_tache) {
        this.titre = titre;
        this.categorie = categorie;
        this.urgence = urgence;
        this.duree = duree;
        this.dateLimite = dateLimite;
        this.contexte = contexte;
        this.commentaire_tache = commentaire_tache;

    }

    public Taches(int num_tache, String titre, String categorie, String urgence, String duree, String dateLimite, Context contexte, String commentaire_tache) {
        this.num_tache = num_tache;
        this.titre = titre;
        this.categorie = categorie;
        this.urgence = urgence;
        this.duree = duree;
        this.dateLimite = dateLimite;
        this.contexte = contexte;
        this.commentaire_tache = commentaire_tache;

    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public int getNum_tache() {
        return num_tache;
    }

    public static void addClient(Taches client) {

        array_clients.add(client);

    }

    public static ArrayList<Taches> getArray_clients() {
        return array_clients;
    }

    @Override
    public String toString() {
        return
                "<div style='background-color: #F5F5F5; border-radius: 10px; margin:25px; padding:25px;'><h2>Tâche : " + "</h2>" +
                        "Categorie : " + categorie + "<br>" +
                        "Titre : " + titre + "<br>" +
                        "Urgence : " + urgence + "<br>" +
                        "Duree : " + duree + "<br>" +
                        "Date limite : " + dateLimite + "<br>" +
                        "<h2>Commentaire : " + "</h2>" +
                        commentaire_tache + "<br></div>";
    }

    public static void setArray_clients(ArrayList<Taches> array_clients) {
        Taches.array_clients = array_clients;
    }



    /**
     * concersion  du Client au format JSONArray
     *
     * @return
     */
    public JSONArray convertToJSONArrayAddClient() {
        List laListe = new ArrayList();
        laListe.add(titre);
        laListe.add(categorie);
        laListe.add(urgence);
        laListe.add(duree);
        laListe.add(dateLimite);
        laListe.add(commentaire_tache);

        return new JSONArray(laListe);

    }

    public JSONArray convertToJSONArrayModifClient() {
        List laListe = new ArrayList();
        laListe.add(titre);
        laListe.add(categorie);
        laListe.add(urgence);
        laListe.add(duree);
        laListe.add(dateLimite);
        laListe.add(commentaire_tache);
        laListe.add(num_tache);


        return new JSONArray(laListe);

    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getUrgence() {
        return urgence;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public String getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(String dateLimite) {
        this.dateLimite = dateLimite;
    }



    public void setNum_tache(int num_tache) {
        this.num_tache = num_tache;
    }

    public String getCommentaire_tache() {
        return commentaire_tache;
    }

    public void setCommentaire_tache(String commentaire_tache) {
        this.commentaire_tache = commentaire_tache;
    }

    public Context getContexte() {
        return contexte;
    }

    public void setContexte(Context contexte) {
        this.contexte = contexte;
    }
}
