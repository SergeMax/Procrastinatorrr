package com.example.procrastinator.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.procrastinator.Animation;
import com.example.procrastinator.R;
import com.example.procrastinator.controler.Controler;
import com.example.procrastinator.model.Taches;

import java.util.ArrayList;

public class TacheFiche extends AppCompatActivity {



    private Button btnEditer;
    private ImageView btnBack;


    private Controler controler;
    private int tacheNumId;
    private TextView txtUrgenceT;
    private TextView txtDateLimiteT;
    private TextView txtRepetT;
    private TextView txtCommentaireT;
    private TextView txtTitre1T;
    private TextView txtCategorie;
    private Button btnSupprimer;
    private TextView txtTitre1;
    private TextView txtUrgence1;
    private TextView txtDateLimite;
    private TextView txtRepet;
    private TextView txtCommentaire;
    private TextView dureeT;
    private TextView duree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.controler = Controler.getInstance(this);
        setContentView(R.layout.activity_tache_fiche);
        Intent intent = getIntent();


        if (intent != null) {
            if (intent.hasExtra("numTache")) { // vérifie qu'une valeur est associée à la clé “edittext”

                tacheNumId = Integer.parseInt(getIntent().getExtras().getString("numTache"));// on récupère la valeur associée à la clé



            }
        }

        init();

    }

    private void init() {

        //Button/////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btnEditer = (Button) findViewById(R.id.btnValider);
        btnBack = (ImageView) findViewById(R.id.arrowBack);
        btnSupprimer = (Button) findViewById(R.id.btnSupprimer);



        //TextField//////////////////////////////////////////////////////////////////////////////////////////////////////////


        // TextView txtContact = (TextView) findViewById(R.id.txtcontact);

         txtCategorie = (TextView) findViewById(R.id.txtCategorie);



        //initTitre//////////////////////////////////////////////////////////////////////////////////////////////////////////


        txtTitre1 = findViewById(R.id.txtTitre1);
        txtUrgence1 = findViewById(R.id.txtUrgence1);
        txtDateLimite = findViewById(R.id.txtDateLimite);
        txtCommentaire = findViewById(R.id.txtCommentaire);
        duree = findViewById(R.id.txtDuree);

        //initText///////////////////////////////////////////////////////////////////////////////////////////////////////////


        txtTitre1T = findViewById(R.id.txtTitreT);
        txtUrgenceT = findViewById(R.id.txtUrgenceT);
        txtDateLimiteT = findViewById(R.id.txtDateLimiteT);
        txtCommentaireT = findViewById(R.id.txtCommentaireT);
        dureeT = findViewById(R.id.txtDureeT);

        //Font///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");
        Typeface fontMed = Typeface.createFromAsset(getAssets(), "fonts/vfuturamedium.otf");


        //InitFontOnButton///////////////////////////////////////////////////////////////////////////////////////////////////

txtCategorie.setAlpha(0);
        ObjectAnimator.ofFloat(txtCategorie, "alpha", 0f, 1f).setDuration(2000).start();

        txtCategorie.setVisibility(View.INVISIBLE);
        txtCategorie.setTypeface(font);
        txtCategorie.setTypeface(font);


        txtTitre1.setTypeface(fontMed);
        txtUrgence1.setTypeface(fontMed);
        txtDateLimite.setTypeface(fontMed);
        txtCommentaire.setTypeface(fontMed);

        //initText///////////////////////////////////////////////////////////////////////////////////////////////////////////


        txtTitre1T.setTypeface(fontMed);
        txtUrgenceT.setTypeface(fontMed);
        txtDateLimiteT.setTypeface(fontMed);
        txtCommentaireT.setTypeface(fontMed);
        dureeT.setTypeface(fontMed);







        //Listener///////////////////////////////////////////////////////////////////////////////////////////////////////////

        btnBackListener();
        btnEditerListener();
        btnSupprimerListener();



        //Init donnees


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void run() {

                        try {

                            ArrayList<Taches> lesTaches = new ArrayList<>();

                            lesTaches = controler.getLesTaches();


                            for (int z = 0; z < lesTaches.size(); z++) {
                                if (lesTaches.get(z).getNum_tache() == tacheNumId) {



                                    txtCategorie.setText(lesTaches.get(z).getCategorie());

                                    txtTitre1T.setText("" + lesTaches.get(z).getTitre());
                                    txtUrgenceT.setText(lesTaches.get(z).getUrgence()+ " /10");
                                    txtDateLimiteT.setText(lesTaches.get(z).getDateLimite());
                                    dureeT.setText(lesTaches.get(z).getDuree());
                                    txtCommentaireT.setText(lesTaches.get(z).getCommentaire_tache());

                                    txtCategorie.setVisibility(View.VISIBLE);
                                    Animation.animFonduOpacity(1000, txtCategorie);
                                    Animation.animFonduOpacity(0, txtDateLimiteT);
                                    Animation.animFonduOpacity(100, txtTitre1T);
                                    Animation.animFonduOpacity(200, txtUrgenceT);
                                    Animation.animFonduOpacity(300, txtRepetT);
                                    Animation.animFonduOpacity(400, dureeT);
                                    Animation.animFonduOpacity(500, txtCommentaireT);



                                    final ArrayList<Taches> finalLesTaches = lesTaches;
                                    final int finalZ = z;


                                }


                            }

                            System.out.println("chargement terrain ok");

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("probleme chargement donnees terrain");
                        }

                    }
                },
                1000);


    }

    private void btnSupprimerListener() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(TacheFiche.this, ListeTaches.class);


                startActivity(i);
                finish();

            }
        });
    }

    private void btnBackListener() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(TacheFiche.this, ListeTaches.class);


                startActivity(i);
                finish();


            }
        });
    }

    private void btnEditerListener() {

        btnEditer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(TacheFiche.this, AddTache.class);

                i.putExtra("numTache", tacheNumId);

                startActivity(i);
                finish();
            }
        });
    }

}

