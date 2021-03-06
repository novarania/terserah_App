package com.aceculture.terserahapp;

import android.app.Activity;
import android.content.Intent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import android.location.Location;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ConnectionCallbacks, OnConnectionFailedListener {
    Toolbar toolbar;



    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    TextView tvLatlong;
    String lat;
    String longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        final ImageButton jadwal_Btn = (ImageButton) findViewById(R.id.jadwal_btn);
        jadwal_Btn.setOnClickListener((View.OnClickListener) this);

        /*ImageButton buka_Btn = (ImageButton) findViewById(R.id.buka_btn);
        buka_Btn.setOnClickListener((View.OnClickListener) this);*/



        tvLatlong = (TextView) findViewById(R.id.textview1);

        buildGoogleApiClient();

        if(mGoogleApiClient!= null){
            mGoogleApiClient.connect();
        }
        else
            Toast.makeText(this, "Not connected...", Toast.LENGTH_SHORT).show();



    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if(toolbar != null)
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.logo_small);

        ab.setDisplayHomeAsUpEnabled(true);
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


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.jadwal_btn:


                Intent i = new Intent(MainActivity.this, jadwal_imsak_solat.class);
                startActivity(i);
                break;
            /*case R.id.buka_btn:
                Intent j = new Intent(MainActivity.this, tempat_berbuka.class);
                startActivity(j);
                break;*/




                }
        }


    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null) {
            tvLatlong.setText("Latitude: "+ String.valueOf(mLastLocation.getLatitude())+"\nLongitude: "+
                    String.valueOf(mLastLocation.getLongitude()));
            lat = String.valueOf(mLastLocation.getLatitude());
            longi = String.valueOf(mLastLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection suspended...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Failed to connect...", Toast.LENGTH_SHORT).show();
    }
}
