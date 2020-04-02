package com.example.procrastinator.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.procrastinator.R;
import com.example.procrastinator.controler.Controler;
import com.example.procrastinator.model.Taches;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddTache extends AppCompatActivity {


    private Button btnValider;
    private ImageView btnBack;
    private Controler controler;

    private TextInputLayout input_txtTitre;
    private TextView input_txt_urgence;
    private TextInputLayout input_sexe_client_1;
    private TextInputLayout input_txt_DateLimite;
    private TextInputLayout input_txtRepet;
    private TextInputLayout input_txtCommentaire;



    private int tacheNumId;



    private int num_clients;
    private String titre;
    private String urgence;
    private String sexe_client_1;
    private String dateLimite;
    private String adresse_client_1;
    private String nom_client_2;
    private String prenom_client_2;
    private String sexe_client_2;
    private String tel_client_2;
    private String adresse_client_2;
    private String commentaire_tache;

    private ArrayList<ArrayList> tab_polygone_client;
    private Spinner spinnerCategorie;
    private Spinner spinnerUrgence;
    private TextInputLayout input_commentaire_client;

    private boolean modeEdit;
    private String polyClient;
    private TextInputEditText input_txt_urgenceT;
    private TextInputEditText input_txtTitreInput;
    private TextInputEditText input_datelimiteT;
    private TextInputEditText input_repetT;
    private TextInputEditText input_commentaire_clientT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tache);

        modeEdit = false;


        init();

        this.controler = Controler.getInstance(this);

    }

    private void init() {

        //Button/////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btnValider = (Button) findViewById(R.id.btnValider);
        btnBack = (ImageView) findViewById(R.id.arrowBack);

        //TextField//////////////////////////////////////////////////////////////////////////////////////////////////////////

        final TextView txtAjouter = (TextView) findViewById(R.id.txtajouter);

        //EditText//////////////////////////////////////////////////////////////////////////////////////////////////////////

        input_txtTitre = (TextInputLayout) findViewById(R.id.txtTitre1);
        input_txtTitreInput = (TextInputEditText) findViewById(R.id.txtTitreT);


        spinnerUrgence = findViewById(R.id.spinnerUrgence);

        input_txt_DateLimite = (TextInputLayout) findViewById(R.id.txtDateLimite);
        input_datelimiteT = (TextInputEditText) findViewById(R.id.txtDateLimiteT);



        input_commentaire_client = (TextInputLayout) findViewById(R.id.txtCommentaire);
        input_commentaire_clientT = (TextInputEditText) findViewById(R.id.txtCommentaireT);


        //Font///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");

        //InitFontOnButton///////////////////////////////////////////////////////////////////////////////////////////////////

        txtAjouter.setTypeface(font);

        //Listener///////////////////////////////////////////////////////////////////////////////////////////////////////////

        btnBackListener();

        //Spinner////////////////////////////////////////////////////////////////////////////////////////////////////////////
        spinnerCategorie = findViewById(R.id.spinnerCategorie);
        spinnerUrgence = findViewById(R.id.spinnerUrgence);

        String[] items = new String[]{"Catégorie :", "Papirasse adminidstrative", "Cours", "Famille", "Achat"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        spinnerCategorie.setAdapter(adapter);

         items = new String[]{"Urgence de la tâche sur 10 :", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
       adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinnerUrgence.setAdapter(adapter);

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra("numTache")) { // vérifie qu'une valeur est associée à la clé “edittext”

                modeEdit = true;

                tacheNumId = getIntent().getExtras().getInt("numTache");

                txtAjouter.setText("Modifier Tâche");

                btnValider.setText("Modifier");

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            public void run() {

                                try {


                                    ArrayList<Taches> lesTaches = new ArrayList<>();

                                    lesTaches = controler.getLesTaches();


                                    for (int z = 0; z < lesTaches.size(); z++) {
                                        if (lesTaches.get(z).getNum_tache() == tacheNumId) {


                                            input_txtTitre.getEditText().setText(lesTaches.get(z).getTitre());

                                            input_txt_DateLimite.getEditText().setText(lesTaches.get(z).getDateLimite());

                                            if (lesTaches.get(z).getCategorie().equals("M.")) {
                                                spinnerCategorie.setSelection(0);
                                            }else{
                                                spinnerCategorie.setSelection(1);

                                            }

                                            if (lesTaches.get(z).getCategorie().equals("M.")) {
                                             //   input_txt_urgence.getEditText().setText(lesTaches.get(z).getUrgence());

                                                spinnerUrgence.setSelection(0);
                                            }else{
                                                spinnerUrgence.setSelection(1);

                                            }



                                            // spinnerCategorie.("" + lesClients.get(z).getDateLimite());
                                            input_txtCommentaire.getEditText().setText("" + lesTaches.get(z).getCommentaire_tache());
                                            input_commentaire_client.getEditText().setText(lesTaches.get(z).getCommentaire_tache());




                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("probleme chargement donnees terrain");
                                }

                            }
                        },
                        800);


            }
        }

    }

    //Validation Formulaire///////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean validateNomClient1() {
        String emailInput = input_txtTitre.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            //input_txtTitre.setErrorTextColor();
            input_txtTitreInput.setBackgroundResource(R.drawable.error_appearance);

            return false;
        } else {
            input_txtTitre.setError(null);
            return true;
        }
    }



    private boolean validateTelClient1() {
        String emailInput = input_txt_DateLimite.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            input_datelimiteT.setBackgroundResource(R.drawable.error_appearance);
            return false;
        } else {
            input_txt_DateLimite.setError(null);
            return true;
        }
    }

    private boolean validateAdresseClient1() {
        String emailInput = input_txtRepet.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            input_repetT.setBackgroundResource(R.drawable.error_appearance);
            return false;
        } else {
            input_txtRepet.setError(null);
            return true;
        }
    }

    private void setChampsVide() {

        String commentaireClient = input_commentaire_client.getEditText().getText().toString().trim();


        if (commentaireClient.isEmpty()) {
            commentaire_tache = null;
        } else {
            commentaire_tache = input_commentaire_client.getEditText().getText().toString();
        }


    }

    public void confirmInput(View view) throws ParseException {

        if (!validateNomClient1() | !validateTelClient1() | !validateAdresseClient1()) {

            Toast toast=Toast.makeText(getApplicationContext(),"Les champs  *  ne peuvent être vides.",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER,0,450);
            view =toast.getView();
            TextView  view1=(TextView)view.findViewById(android.R.id.message);
            view1.setTextColor(Color.BLACK);
            view.setBackgroundResource(R.drawable.toast_yellow_shape);


            toast.show();


            return;
        }


        /* autoincrement a modifier*/
        // num_secteur_terrain = secteur;
        titre = input_txtTitre.getEditText().getText().toString().toUpperCase();

        urgence =  spinnerUrgence.getSelectedItem().toString();

        String categorie = spinnerCategorie.getSelectedItem().toString();
        dateLimite = input_txt_DateLimite.getEditText().getText().toString();

        commentaire_tache = input_commentaire_client.getEditText().getText().toString();

        setChampsVide();

        String input = "\n";
        input += "Urgence : " + urgence;
        input += "\n";



        if (modeEdit == true){

            Toast.makeText(this, "Client modifié", Toast.LENGTH_SHORT).show();
            System.out.println(input);

            this.controler.modifierTache(tacheNumId, titre, categorie, urgence, dateLimite,
                   this, commentaire_tache);

        }else {



            Toast.makeText(this, "Client ajouté", Toast.LENGTH_SHORT).show();
            System.out.println(input);

            this.controler.creerTache(titre, urgence, sexe_client_1, dateLimite,
                    /*polygone_client, tab_polygone_client*/ this, commentaire_tache);

            Intent o = new Intent(AddTache.this, ListeTaches.class);


            startActivity(o);
            finish();
        }

    }


    //Listener/////////////////////////////////////////////////////////////////////////////////////////////

 /*   public void creerTerrain(int num_terrain, int num_secteur_terrain,
                             String adresse_terrain, String ville_terrain,
                             int cp_ville_terrain, String status_terrain,
                             int prix_terrain, int superficie_terrain,
                             Date droit_entree_terrain, Date droit_exclu_terrain,
                            int id_commercial_terrain,
                             String plan_terrain, String commentaires_terrain,
                             LatLng latLng_terrain, Date dernier_contact) {

        this.controler.creerTerrain(num_terrain, num_secteur_terrain,
                adresse_terrain, ville_terrain,
                cp_ville_terrain, status_terrain,
                prix_terrain, superficie_terrain,
                droit_entree_terrain, droit_exclu_terrain,
                 id_commercial_terrain,
                plan_terrain, commentaires_terrain,
                latLng_terrain, dernier_contact);
    }*/

    private void btnBackListener() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(AddTache.this, ListeTaches.class);


                startActivity(i);
                finish();

            }
        });
    }

}
