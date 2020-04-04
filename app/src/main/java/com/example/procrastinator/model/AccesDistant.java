package com.example.procrastinator.model;

import android.util.Log;

import com.example.procrastinator.controler.Controler;
import com.example.procrastinator.tools.tools.AccesHTTP;
import com.example.procrastinator.tools.tools.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class AccesDistant implements AsyncResponse {

    // constante
    private static final String SERVERADDR = "http://s794979271.onlinehome.fr/procrastinatorPHP/serveurprocrastinator.php";
    private Controler controler;

    public AccesDistant() {
        controler = Controler.getInstance(null);
    }

    /**
     * retour du serveur distant
     *
     * @param output
     */

    @Override
    public void processFinish(String output) {

        System.out.println("processfinish");
        Log.d("serveur", "******************" + output);
        //découpage message recu
        String[] message = output.split("%");
        //dans message[0] :  soit "enreg", soit "dernier", soit "erreur"
        // dans message[1] : reste du message


        // si il y a 2 cases

        if (message.length > 1) {


            if (message[0].equals("enregTache")) {
                Log.d("enregTache", "******************" + message[1]);
            }else   if (message[0].equals("modifTache")) {
                Log.d("modifTache", "******************" + message[1]);
            }else if (message[0].equals("getListeTaches")) {
                    Log.d("getListeTaches", "******************" + message[1]);



                try {
                    System.out.println("passe dans le try acces Distant");

                    JSONArray jSonInfo = new JSONArray(message[1]);
                    ArrayList<Taches> lesTaches = new ArrayList<>();

                    System.out.println("passe dans le try acces Distant");

                        for (int i = 0; i < jSonInfo.length(); i++) {

                            System.out.println("passe dans le for acces Distant");

                            JSONObject info = new JSONObject(jSonInfo.get(i).toString());


                            int num_tache = info.getInt("numTache");
                            String titre = info.getString("titre");
                            String categorie = info.getString("categorie");
                            String duree = info.getString("duree");

                            String urgence = info.getString("urgence");
                            String dateLimite = info.getString("date_limite");
                            String commentaire = info.getString("commentaire");



                            Taches taches = new Taches(num_tache, titre, categorie, urgence, duree,
                                    dateLimite, null, commentaire );
                            lesTaches.add(taches);
                            System.out.println("passage fin du for acces Distant");

                        }
                        System.out.println("passage controleur qui set le tableau dans accesDistant ligne 72");
                        controler.setLesTaches(lesTaches);

                    } catch (JSONException e) {
                        Log.d("erreur", "conversion JSON impossible " + e.toString());
                    }

                } else {
                    if (message[0].equals("Erreur !")) {
                        Log.d("Erreur !", "******************" + message[1]);
                    }
                }
            }

        }



    public void envoi(String operation, JSONArray lesDonneesJSON) {

        System.out.println("::::::: envoi");
        AccesHTTP accesDonnes = new AccesHTTP();
        // lien de délégation
        accesDonnes.delegate = this;
        // ajout paramètres
        accesDonnes.addParam("operation", operation);
        accesDonnes.addParam("lesdonnees", lesDonneesJSON.toString());
        // appel au serveur
        accesDonnes.execute(SERVERADDR);
    }
}
