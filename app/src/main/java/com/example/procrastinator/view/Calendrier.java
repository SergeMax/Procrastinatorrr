package com.example.procrastinator.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.procrastinator.R;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.procrastinator.view.ListeTaches.changeToolbarFont;

public class Calendrier extends AppCompatActivity {

    private static final String TAG = "log...................";
    private TextView btnCalendrier;
    private TextView calendar;
    private Menu menu;
    private CompactCalendarView calendarView;

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

            }
        });

        init();
    }


    private void init() {
        btnCalendrier = (TextView) findViewById(R.id.action_mode);

        calendar = findViewById(R.id.calendar);
        calendarView = findViewById(R.id.calendarView);

        final TextView txtMonth = findViewById(R.id.monthText);
        final SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.FRANCE);
        txtMonth.setText(dateFormatForMonth.format(calendarView.getFirstDayOfCurrentMonth()));
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");


        txtMonth.setTypeface(font);


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


        String string = "10/04/2020";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long epoch = date.getTime();

        Event ev1 = new Event(Color.GREEN, epoch, "Some extra data that I want to store.");
        calendarView.addEvent(ev1);

        //calendarView.addEvent(date);
        List<Event> events = calendarView.getEvents(date); // can also take a Date object

        // events has size 2 with the 2 events inserted previously
        Log.d(TAG, "Events: " + events);

        // define a listener to receive callbacks when certain events happen.
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = calendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
                if (events.size()>0){
                String message = events.get(0).getData().toString();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();}else{
                    Toast.makeText(getApplicationContext(), "Aucune tâche ce jour", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);

                txtMonth.setText("" + (dateFormatForMonth.format(firstDayOfNewMonth)));
            }
        });

        setTitle("Procrastinator");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        this.menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_format_list_bulleted_black_24dp));

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
            Intent i = new Intent(Calendrier.this, ListeTaches.class);
            startActivity(i);
            finish();

            return true;
        }

        if (id == R.id.calendar) {
            Intent i = new Intent(Calendrier.this, ListeTaches.class);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
