package com.aceculture.terserahapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class tempat_berbuka extends AppCompatActivity {
    Toolbar toolbar;
    ListView list_tempat_makan_dekat;
    ArrayAdapter<String> adapter;

    ListTempatMakanAdapter listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_berbuka);
        setupToolbar();


        listItem = new ListTempatMakanAdapter();

        ListView listItemView = (ListView)findViewById(R.id.listTempatMakanDekat);
        listItemView.setAdapter(listItem);



    }
    public class listViewTempatMakan
    {
        String namaTempatMakan;
        String alamatTempatMakan;

    }

    public  List<listViewTempatMakan> DataListTempatMakan= getDatafromListTempatMakan();
    public class ListTempatMakanAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return DataListTempatMakan.size();
        }

        @Override
        public Object getItem(int arg0) {
            return DataListTempatMakan.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            if (arg1==null){
                LayoutInflater inflater = (LayoutInflater) tempat_berbuka.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                arg1 = inflater.inflate(R.layout.list_view, arg2,false);
            }
            TextView nama_tempat_makan = (TextView)arg1.findViewById(R.id.namaTempatMakan);
            TextView alamat_tempat_makan = (TextView)arg1.findViewById(R.id.alamatTempatMakan);


            listViewTempatMakan item = DataListTempatMakan.get(arg0);
            nama_tempat_makan.setText(item.namaTempatMakan);
            alamat_tempat_makan.setText(item.alamatTempatMakan);

            return arg1;
        }

        public listViewTempatMakan getListViewTempatMakan(int position){
            return DataListTempatMakan.get(position);
        }
    }

    public static List<listViewTempatMakan> listOfViewTempatMakan = new ArrayList<listViewTempatMakan>();
    public List<listViewTempatMakan> getDatafromListTempatMakan()
    {
        if (true){
            listViewTempatMakan item = new listViewTempatMakan();
            item.namaTempatMakan = "Nikkou Ramen";
            item.alamatTempatMakan = "AM Sangaji";
            listOfViewTempatMakan.add(item);
        }
        return listOfViewTempatMakan;
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
        getMenuInflater().inflate(R.menu.menu_tempat_berbuka, menu);
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
