package com.aceculture.terserahapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton jadwal_Btn = (ImageButton) findViewById(R.id.jadwal_btn);
        jadwal_Btn.setOnClickListener((View.OnClickListener) this);
        ImageButton buka_Btn = (ImageButton) findViewById(R.id.buka_btn);
        buka_Btn.setOnClickListener((View.OnClickListener) this);
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
                //Log.i("clicks","You Clicked B1");
                Intent i = new Intent(MainActivity.this, jadwal_imsak_solat.class);
                startActivity(i);
                break;
            case R.id.buka_btn:
                Intent j = new Intent(MainActivity.this, tempat_berbuka.class);
                startActivity(j);
                break;

        }
    }

}
