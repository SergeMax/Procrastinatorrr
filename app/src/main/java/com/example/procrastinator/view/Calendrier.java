package com.example.procrastinator.view;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.procrastinator.R;

import static com.example.procrastinator.view.ListeTaches.changeToolbarFont;

public class Calendrier extends AppCompatActivity {

    private TextView btnCalendrier;
    private TextView calendar;
    private Menu menu;

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


    private void init(){
        btnCalendrier = (TextView) findViewById(R.id.action_mode);

        calendar = findViewById(R.id.calendar);

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
