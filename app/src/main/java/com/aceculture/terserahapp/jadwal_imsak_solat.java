package com.aceculture.terserahapp;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class jadwal_imsak_solat extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_imsak_solat);
        setupToolbar();

        TextClock text_clock = (TextClock) findViewById(R.id.textClock);
        TextView text_jam = (TextView) findViewById(R.id.textjam);
        TextView text_menit = (TextView) findViewById(R.id.textmenit);
        TextView sholat = (TextView) findViewById(R.id.Sholat);
        TextView waktu_imsak = (TextView) findViewById(R.id.waktuImsak);
        TextView waktu_subuh = (TextView) findViewById(R.id.waktuSubuh);
        TextView waktu_dzuhur = (TextView) findViewById(R.id.waktuDzuhur);
        TextView waktu_asar = (TextView) findViewById(R.id.waktuAsar);
        TextView waktu_maghrib = (TextView) findViewById(R.id.waktuMaghrib);
        TextView waktu_isya = (TextView) findViewById(R.id.waktuIsya);

    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if(toolbar != null)
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jadwal_imsak_solat, menu);
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
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
