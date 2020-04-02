package com.example.procrastinator.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.procrastinator.R;
import com.example.procrastinator.controler.Controler;
import com.example.procrastinator.model.Taches;
import com.example.procrastinator.tools.tools.GoFicheIntent;
import com.example.procrastinator.tools.tools.RecyclerviewItemListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListeTaches extends AppCompatActivity  {


    private ImageView btnAddTache;
    private LinearLayoutManager layoutManager;
    private ListTachesAdapter listTachesAdapter;
    private Controler controler;
    GoFicheIntent.OnTonFragmentInteractionListener mListener;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.controler = Controler.getInstance(this);

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
                Snackbar.make(view, "Vous allez ajouter une nouvelle tâche !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(ListeTaches.this, AddTache.class);
                startActivity(i);
                finish();
            }
        });


        //  TextView txtTitre = (TextView) findViewById(R.id.listetache);


        //Font///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");

        //  txtTitre.setTypeface(font);
    }


    private void init() {

        //Button/////////////////////////////////////////////////////////////////////////////////////////////////////////////


        btnAddTache = (ImageView) findViewById(R.id.add);


        //TextField/////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        TextView txtAjouter = (TextView) findViewById(R.id.txtajouter);


        //Font///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");

        //InitFontOnButton///////////////////////////////////////////////////////////////////////////////////////////////////


        //InitListView/////////////////////////////////////////////////////////////////////////////////////////////////////////
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.taches_recyclerview);


        // if (controler.getLesTaches().get(0) != null) {

        final List<Taches> listTachesRemplie = controler.getLesTaches();


        // On récupère notre RecyclerView via son id
        recyclerView = findViewById(R.id.taches_recyclerview);

        // On veut un RecyclerView qui utilise un LinearLayoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // On donne notre adapter à notre RecyclerView
        listTachesAdapter = new ListTachesAdapter(listTachesRemplie, mListener);
        recyclerView.setAdapter(listTachesAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerviewItemListener(getApplicationContext(), recyclerView, new RecyclerviewItemListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Taches taches = listTachesRemplie.get(position);
                //  Toast.makeText(getApplicationContext(), clients.getTitre() + " is selected!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ListeTaches.this, TacheFiche.class);
                i.putExtra("num_client", "" + taches.getNum_tache());


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


        //Listener///////////////////////////////////////////////////////////////////////////////////////////////////////////


        btnAddTacheListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

