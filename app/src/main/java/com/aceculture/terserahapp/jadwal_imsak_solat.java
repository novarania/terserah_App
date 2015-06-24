package com.aceculture.terserahapp;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DigitalClock;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.text.TextUtils.substring;

public class jadwal_imsak_solat extends AppCompatActivity {
    Toolbar toolbar;
    public static int bulanSekarang;
    public static int tahunSekarang;
    public static int tanggalSekarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_imsak_solat);
        setupToolbar();

        //TextClock text_clock = (TextClock) findViewById(R.id.textClock);
        TextView text_jam = (TextView) findViewById(R.id.textjam);
        final TextView text_menit = (TextView) findViewById(R.id.textmenit);
        TextView sholat = (TextView) findViewById(R.id.Sholat);
        TextView waktu_imsak = (TextView) findViewById(R.id.waktuImsak);
        TextView waktu_subuh = (TextView) findViewById(R.id.waktuSubuh);
        TextView waktu_dzuhur = (TextView) findViewById(R.id.waktuDzuhur);
        TextView waktu_asar = (TextView) findViewById(R.id.waktuAsar);
        TextView waktu_maghrib = (TextView) findViewById(R.id.waktuMaghrib);
        TextView waktu_isya = (TextView) findViewById(R.id.waktuIsya);

        DigitalClock dc = (DigitalClock) findViewById(R.id.digitalClock1);

        //buat nyari tahu sekarang tanggal berapa
        Calendar cal = Calendar.getInstance();
        bulanSekarang = cal.get(Calendar.MONTH);
        tahunSekarang = cal.get(Calendar.YEAR);
        tanggalSekarang = cal.get(Calendar.DAY_OF_MONTH);

        //buat grab json
        // we will using AsyncTask during parsing
        new AsyncTaskParseJson().execute();
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

    // you can make this class as another java file so it will be separated from your main activity.
    public class AsyncTaskParseJson extends AsyncTask<String, String, JSONObject> {

        final String TAG = "AsyncTaskParseJson.java";

        // set your json string url here
        String yourJsonStringUrl = "http://praytime.info/getprayertimes.php?lat=-7.7828&lon=110.360802&gmt=420&m="+(bulanSekarang+1)+"&y="+tahunSekarang+"&school=0";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);

                // cari object JSON di tanggal saat ini
                JSONObject tanggal = json.getJSONObject(String.valueOf(tanggalSekarang));
                Log.e(TAG, tanggal.getString("Imsaak"));

                return tanggal;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, yourJsonStringUrl);
                Log.e(TAG, String.valueOf(tanggalSekarang));
            }
            return null;
        }

        @Override
        protected void onPostExecute(final JSONObject jsonObj){
            try {
                TextView waktu_imsak = (TextView) findViewById(R.id.waktuImsak);
                TextView waktu_subuh = (TextView) findViewById(R.id.waktuSubuh);
                TextView waktu_dzuhur = (TextView) findViewById(R.id.waktuDzuhur);
                TextView waktu_asar = (TextView) findViewById(R.id.waktuAsar);
                TextView waktu_maghrib = (TextView) findViewById(R.id.waktuMaghrib);
                TextView waktu_isya = (TextView) findViewById(R.id.waktuIsya);

                waktu_imsak.setText(jsonObj.getString("Imsaak"));
                waktu_subuh.setText(jsonObj.getString("Fajr"));
                waktu_dzuhur.setText(jsonObj.getString("Dhuhr"));
                waktu_asar.setText(jsonObj.getString("Asr"));
                waktu_maghrib.setText(jsonObj.getString("Maghrib"));
                waktu_isya.setText(jsonObj.getString("Isha"));

                waktuImsyak = jsonObj.getString("Imsaak");
                waktuSubuh = jsonObj.getString("Fajr");
                waktuDhuhur = jsonObj.getString("Dhuhr");
                waktuAshar = jsonObj.getString("Asr");
                waktuMaghrib = jsonObj.getString("Maghrib");
                waktuIsya = jsonObj.getString("Isha");

                PenentuanWaktuSolat();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static String waktuImsyak;
    public static String waktuSubuh;
    public static String waktuDhuhur;
    public static String waktuAshar;
    public static String waktuMaghrib;
    public static String waktuIsya;

    public void PenentuanWaktuSolat(){
        //deklarasi element view
        TextView text_jam = (TextView) findViewById(R.id.textjam);
        TextView text_menit = (TextView) findViewById(R.id.textmenit);
        TextView sholat = (TextView) findViewById(R.id.Sholat);
        //deklarasi variabel lokal method
        int sisaImsyak, sisaSubuh, sisaDhuhur, sisaAshar, sisaMaghrib, sisaIsya;
        int sisaJam, sisaMenit;

        sisaImsyak = SisaWaktuSolat(waktuImsyak);
        sisaSubuh = SisaWaktuSolat(waktuSubuh);
        sisaDhuhur = SisaWaktuSolat(waktuDhuhur);
        sisaAshar = SisaWaktuSolat(waktuAshar);
        sisaMaghrib = SisaWaktuSolat(waktuMaghrib);
        sisaIsya = SisaWaktuSolat(waktuIsya);

        //cari waktu terdekat
        if (sisaImsyak<sisaSubuh && sisaImsyak>0 && sisaSubuh>0){
            sisaJam = sisaImsyak/60;
            sisaMenit = sisaImsyak%60;
            text_jam.setText(String.valueOf(sisaJam));
            text_menit.setText(String.valueOf(sisaMenit));
            sholat.setText("Imsyak");
        }
        else if (sisaSubuh<sisaDhuhur && sisaSubuh>0 && sisaDhuhur>0){
            sisaJam = sisaSubuh/60;
            sisaMenit = sisaSubuh%60;
            text_jam.setText(String.valueOf(sisaJam));
            text_menit.setText(String.valueOf(sisaMenit));
            sholat.setText("Subuh");
        }
        else if (sisaDhuhur<sisaAshar && sisaDhuhur>0 && sisaAshar>0){
            sisaJam = sisaDhuhur/60;
            sisaMenit = sisaDhuhur%60;
            text_jam.setText(String.valueOf(sisaJam));
            text_menit.setText(String.valueOf(sisaMenit));
            sholat.setText("Dhuhur");
        }
        else if (sisaAshar<sisaMaghrib && sisaAshar>0 && sisaMaghrib>0){
            sisaJam = sisaAshar/60;
            sisaMenit = sisaAshar%60;
            text_jam.setText(String.valueOf(sisaJam));
            text_menit.setText(String.valueOf(sisaMenit));
            sholat.setText("Ashar");
        }
        else if (sisaMaghrib<sisaIsya && sisaMaghrib>0 && sisaIsya>0){
            sisaJam = sisaMaghrib/60;
            sisaMenit = sisaMaghrib%60;
            text_jam.setText(String.valueOf(sisaJam));
            text_menit.setText(String.valueOf(sisaMenit));
            sholat.setText("Maghrib");
        }
        else{
            sisaJam = sisaIsya/60;
            sisaMenit = sisaIsya%60;
            text_jam.setText(String.valueOf(sisaJam));
            text_menit.setText(String.valueOf(sisaMenit));
            sholat.setText("Isya");
        }
    }

    /**
     * buat menghitung sisa waktu berdasarkan waktu saat ini
     * @param waktuSolat
     * @return int sisaWaktuTotal
     */
    public int SisaWaktuSolat(String waktuSolat){
        final String TAG = "AsyncTaskParseJson.java";
        int jamSekarang, menitSekarang;
        //buat nyari tahu sekarang jam berapa dan menit ke berapa
        Calendar cal = Calendar.getInstance();
        jamSekarang = cal.get(Calendar.HOUR_OF_DAY);
        menitSekarang = cal.get(Calendar.MINUTE);
        //buat menentukan sisa waktu imsyak
        int jamImsyak = Integer.parseInt(substring(waktuSolat, 0, 2));
        int menitImsyak = Integer.parseInt(substring(waktuSolat, 3, 5));
        int sisaJamImsyak, sisaMenitImsyak, sisaTotalImsyak;
        Log.e(TAG, String.valueOf(jamImsyak));
        Log.e(TAG, String.valueOf(menitImsyak));
        sisaJamImsyak = jamImsyak - jamSekarang;
        if (sisaJamImsyak<0){
            sisaJamImsyak = 0;
            sisaMenitImsyak = 0;
            sisaTotalImsyak = 0;
            return sisaTotalImsyak;
        }
        sisaMenitImsyak = menitImsyak - menitSekarang;
        if (menitSekarang>menitImsyak){
            sisaMenitImsyak = menitImsyak+60-menitSekarang;
            sisaJamImsyak = sisaJamImsyak - 1;
            if (sisaJamImsyak<0){
                sisaJamImsyak = 0;
                sisaMenitImsyak = 0;
                sisaTotalImsyak = 0;
            }
        }
        sisaTotalImsyak = (sisaJamImsyak*60)+sisaMenitImsyak;
        Log.e(TAG, String.valueOf(sisaTotalImsyak));
        return sisaTotalImsyak;
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if(toolbar != null)
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
