package com.example.procrastinator.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.procrastinator.Animation;
import com.example.procrastinator.controler.Controler;
import com.example.procrastinator.model.Taches;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.procrastinator.R;
import com.sun.mail.imap.protocol.Item;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import static android.graphics.Color.rgb;
import static com.example.procrastinator.view.ListeTaches.changeToolbarFont;

public class Calendrier extends AppCompatActivity {

    private static final String TAG = "log...................";
    private TextView btnCalendrier;
    private TextView calendar;
    private Menu menu;
    private CompactCalendarView calendarView;
    private Controler controler;
    private List<Taches> listTachesRemplie;
    private int colorr;
    private TextView citation;

    private Random randomGenerator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        changeToolbarFont((Toolbar) findViewById(R.id.toolbar), this);

        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Calendrier.this, AddTache.class);
                startActivity(i);
                finish();
            }
        });

        this.controler = Controler.getInstance(this);
        listTachesRemplie = controler.getLesTaches();

        updateListe(this);

        init();
    }


    private void init() {
        btnCalendrier = (TextView) findViewById(R.id.action_mode);

        calendar = findViewById(R.id.calendar);
        calendarView = findViewById(R.id.calendarView);

        citation = findViewById(R.id.citation);

        ArrayList<String> tabCitation = new ArrayList<>();

        randomGenerator = new Random();


        tabCitation.add("\"Fais quelque chose aujourd'hui pour lequel ton futur toi te remerciera demain\"");
        tabCitation.add("\"Les lendemains de procrastination sont toujours pénible.\"  Gaëtant Faucer");
        tabCitation.add("\"En suivant le chemin qui s'appelle plus tard, nous arrivons sur la place qui s'apelle jamais.\"  Sénèque");
        tabCitation.add("\"Tout ce qui peut être fait un autre jour, le peut être aujourd'hui.\" Michel de Montaigne");
        tabCitation.add("\"Un aujourd'hui vaut deux demain.\" Francis Quarles");


        int index = randomGenerator.nextInt(tabCitation.size());
        String item = tabCitation.get(index);

        citation.setText(item);
        citation.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scroll_animation));

        final TextView txtMonth = findViewById(R.id.monthText);
        final SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.FRANCE);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");

        String str = dateFormatForMonth.format(calendarView.getFirstDayOfCurrentMonth());
        String output = str.substring(0, 1).toUpperCase() + str.substring(1);

        txtMonth.setText("" + output);

        txtMonth.setTypeface(font);
        citation.setTypeface(font);


        calendarView.setLocale(TimeZone.getDefault(), Locale.FRANCE);
        calendarView.setUseThreeLetterAbbreviation(true);
        //  calendarView.

        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
//ev1
        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev2 = new Event(Color.GREEN, 1433704251000L);
        calendarView.addEvent(ev2);

        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        //calendarView.addEvent(date);
        // can also take a Date object


        for (int i = 0; i < listTachesRemplie.size(); i++) {
            String dateStr = listTachesRemplie.get(i).getDateLimite();


            Date date = null;
            try {
                date = format.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            long epoch = date.getTime();

            String data = "" + listTachesRemplie.get(i).getCategorie().toUpperCase() + "\n \n" +
                    "__________________________ \n\n" +
                    "Titre : " + listTachesRemplie.get(i).getTitre() + "\n \n" +
                    "Urgence : " + listTachesRemplie.get(i).getUrgence() + "\n \n" +
                    "Commentaire : \n \n" + listTachesRemplie.get(i).getCommentaire_tache();


            switch (listTachesRemplie.get(i).getUrgence()) {
                case "1":
                    colorr = rgb(245, 253, 169);
                    break;
                case "2":
                    colorr = Integer.parseInt(String.valueOf(rgb(221, 246, 155)));

                    break;
                case "3":
                    colorr = Integer.parseInt(String.valueOf(rgb(175, 246, 155)));
                    break;
                case "4":
                    colorr = Integer.parseInt(String.valueOf(rgb(169, 245, 253)));
                    break;
                case "5":
                    colorr = Integer.parseInt(String.valueOf(rgb(169, 203, 253)));
                    break;
                case "6":
                    colorr = Integer.parseInt(String.valueOf(rgb(79, 148, 252)));
                    break;
                case "7":
                    colorr = Integer.parseInt(String.valueOf(rgb(251, 136, 54)));
                    break;
                case "8":
                    colorr = Integer.parseInt(String.valueOf(rgb(251, 77, 54)));
                    break;
                case "9":
                    colorr = Integer.parseInt(String.valueOf(rgb(150, 19, 2)));
                    break;
                case "10":
                    colorr = Integer.parseInt(String.valueOf(rgb(50, 6, 0)));
                    break;
                default:

            }


            Event ev1 = new Event(colorr, epoch, data);


            calendarView.addEvent(ev1);

        }

        //  List<Event> events = calendarView.getEvents();


        // events has size 2 with the 2 events inserted previously
        //   Log.d(TAG, "Events: " + events);

        // define a listener to receive callbacks when certain events happen.
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                int compteur = 0;
                List<Event> events = calendarView.getEvents(dateClicked);
                if (events.size() > 0) {

                    for (int z = 0; z < events.size(); z++) {

                        String message = events.get(z).getData().toString();
                        message = message + "\n \n" + "__________________________ \n\n" + (z + 1) + "/" + events.size();


                        final Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);

                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 400);

                        View view = toast.getView();
                        TextView view1 = (TextView) view.findViewById(android.R.id.message);
                        view1.setTextColor(Color.BLACK);

                        view.setBackgroundResource(R.drawable.toast_yellow_shape);


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.show();

                            }
                        }, compteur);

                        compteur = compteur + 4000;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Aucune tâche ce jour", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);

                String str = dateFormatForMonth.format(firstDayOfNewMonth);
                String output = str.substring(0, 1).toUpperCase() + str.substring(1);

                txtMonth.setText("" + output);
            }
        });

        setTitle("Procrastinator");


    }

    private void updateListe(Context context) {

        Intent i = getIntent();
        if (i != null && i.hasExtra("classCateg")) {
            System.out.println("intent différent de null");
            controler.updateListeParCateg(this);


        } else {
            System.out.println("intent null");

            controler.updateListe(this);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        this.menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_format_list_bulleted_black_24dp));


        MenuItem item2 = this.menu.findItem(R.id.action_mode);
        item2.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement

        if (id == R.id.calendar) {
            Intent i = new Intent(Calendrier.this, ListeTaches.class);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
