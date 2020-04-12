package com.example.procrastinator.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.procrastinator.R;
import com.example.procrastinator.controler.Controler;
import com.example.procrastinator.model.Taches;
import com.example.procrastinator.tools.tools.GoFicheIntent;
import com.example.procrastinator.tools.tools.RecyclerviewItemListener;
import com.example.procrastinator.tools.tools.longAction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListeTaches extends AppCompatActivity {


    private ImageView btnAddTache;
    private Menu menu;

    // On veut un RecyclerView qui utilise un LinearLayoutManager
    private LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    private ListTachesAdapter listTachesAdapter;
    private Controler controler;
    GoFicheIntent.OnTonFragmentInteractionListener mListener;
    private View btnCalendrier;
    private ArrayList<Taches> listTachesRemplie = new ArrayList<>();
    private RecyclerView  recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        changeToolbarFont((Toolbar) findViewById(R.id.toolbar), this);


        this.controler = Controler.getInstance(this);


        updateListe(this);
        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra("num_tache")) { // vérifie qu'une valeur est associée à la clé “edittext”
                System.out.println("on recupère le parcelable => " + intent.getExtras().getString("num_tache"));

            }
        }

        init();


        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListeTaches.this, AddTache.class);
                startActivity(i);
                finish();
            }
        });


        //  TextView txtTitre = (TextView) findViewById(R.id.listetache);


        //Font///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");

        // longAction.executeLongActionDuring7seconds();



        setListe();



     /*   new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listTachesRemplie.clear();

                ArrayList<Taches> liste = controler.getLesTaches();
              //  listTachesAdapter.notifyDataSetChanged();
                listTachesRemplie.addAll(liste);

                listTachesAdapter.notifyDataSetChanged();



            }
        }, 2000);
*/


        //  txtTitre.setTypeface(font);
    }


    private void updateListe(ListeTaches listeTaches) {

        Intent i = getIntent();
        if (i != null &&  i.hasExtra("classCateg")){
            System.out.println("intent différent de null");
            controler.updateListeParCateg(this);


        }else {
            System.out.println("intent null");

            controler.updateListe(this);
        }

    }

    public void setListe(){

        //InitListView/////////////////////////////////////////////////////////////////////////////////////////////////////////
    recyclerView = (RecyclerView) findViewById(R.id.taches_recyclerview);


        // if (controler.getLesTaches().get(0) != null) {

       listTachesRemplie = controler.getLesTaches();
        System.out.println("liste tache dans setListe avant delay .................................." + listTachesAdapter);



        recyclerView.setLayoutManager(layoutManager);


        // On donne notre adapter à notre RecyclerView
        listTachesAdapter = new ListTachesAdapter(listTachesRemplie, mListener);


        recyclerView.setAdapter(listTachesAdapter);

        recyclerView.setAlpha(0f);

        ObjectAnimator.ofFloat(recyclerView, "alpha", 0f, 1f).setDuration(2000).start();


        //   recyclerView.setAlpha(0f);


        //   ObjectAnimator.ofFloat(recyclerView, "alpha", 0f, 1f).setDuration(2000).start();
        //markerTerrainArrayList.get(y).setAlpha(h);


        recyclerView.addOnItemTouchListener(new RecyclerviewItemListener(getApplicationContext(), recyclerView, new RecyclerviewItemListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Taches taches = listTachesRemplie.get(position);
                //  Toast.makeText(getApplicationContext(), clients.getTitre() + " is selected!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ListeTaches.this, TacheFiche.class);
                i.putExtra("numTache", "" + taches.getNum_tache());


                startActivity(i);
                finish();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        // On sépare chaque ligne de notre liste par un trait
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }




    private void init() {

        //Button/////////////////////////////////////////////////////////////////////////////////////////////////////////////


        btnAddTache = (ImageView) findViewById(R.id.add);
        btnCalendrier = (TextView) findViewById(R.id.action_mode);


        //TextField/////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        TextView txtAjouter = (TextView) findViewById(R.id.txtajouter);


        //Font///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        final Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");


        //InitFontOnButton///////////////////////////////////////////////////////////////////////////////////////////////////


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
/*
                //InitListView/////////////////////////////////////////////////////////////////////////////////////////////////////////
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.taches_recyclerview);


                // if (controler.getLesTaches().get(0) != null) {

                final List<Taches> listTachesRemplie = controler.getLesTaches();


                // On récupère notre RecyclerView via son id
                recyclerView = findViewById(R.id.taches_recyclerview);

                recyclerView.setLayoutManager(layoutManager);


                // On donne notre adapter à notre RecyclerView
                listTachesAdapter = new ListTachesAdapter(listTachesRemplie, mListener);


                recyclerView.setAdapter(listTachesAdapter);

                recyclerView.setAlpha(0f);

                ObjectAnimator.ofFloat(recyclerView, "alpha", 0f, 1f).setDuration(2000).start();


                //   recyclerView.setAlpha(0f);


                //   ObjectAnimator.ofFloat(recyclerView, "alpha", 0f, 1f).setDuration(2000).start();
                //markerTerrainArrayList.get(y).setAlpha(h);


                recyclerView.addOnItemTouchListener(new RecyclerviewItemListener(getApplicationContext(), recyclerView, new RecyclerviewItemListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Taches taches = listTachesRemplie.get(position);
                        //  Toast.makeText(getApplicationContext(), clients.getTitre() + " is selected!", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(ListeTaches.this, TacheFiche.class);
                        i.putExtra("numTache", "" + taches.getNum_tache());


                        startActivity(i);
                        finish();


                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));


                // On sépare chaque ligne de notre liste par un trait
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);*/
            }
        }, 1000);


        //Listener///////////////////////////////////////////////////////////////////////////////////////////////////////////


        btnAddTacheListener();


    }

    private void btnCalendrierListener() {
        btnCalendrier.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(ListeTaches.this, Calendrier.class);
                startActivity(i);
                finish();


            }
        });

    }



    public static void changeToolbarFont(Toolbar toolbar, Activity context) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                if (tv.getText().equals(toolbar.getTitle())) {
                    applyFont(tv, context);
                    break;
                }
            }
        }
    }

    public static void applyFont(TextView tv, Activity context) {
        tv.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/vfuturalight.otf"));
        tv.setTextSize(27);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        Intent i = getIntent();
        if (i!= null && i.hasExtra("classCateg")){
            this.menu = menu;
            this.menu.getItem(1).setTitle("Trier par urgence");

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mode) {

            Intent i = getIntent();
            if (i.hasExtra("classCateg")){
                Boolean classCateg = true;
                Intent ii = new Intent(ListeTaches.this, ListeTaches.class);
                startActivity(ii);
                finish();


            }else {
                Boolean classCateg = true;
                Intent ii = new Intent(ListeTaches.this, ListeTaches.class);
                ii.putExtra("classCateg", classCateg);
                startActivity(ii);
                finish();
            }





            return true;
        }

        if (id == R.id.calendar) {
            Intent i = new Intent(ListeTaches.this, Calendrier.class);
            startActivity(i);
            finish();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void btnAddTacheListener() {

        btnAddTache.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });
    }


    public void recupListeTaches() {
        if (controler.getLesTaches().get(0) != null) {

            List<Taches> listTachesRemplie = controler.getLesTaches();

            for (int i = 0; i < 100; i++) {
                // new item
                //  listClientRemplie.add(new Taches(controler.getTitre(), controler.getUrgence(), controler.getDateLimite(), controler.getDateLimite(), controler.getRepet(), controler.getCommentaire(), controler.getPrenom_client_2(), controler.getSexe_client_2(), controler.getTel_client_2(),controler.getAdresse_client_2(), null));
            }

        }

    }

    public void goListeClients(String num_client) {


    }


}

