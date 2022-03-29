package com.example.ujikom_james_tauhid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class lihatbarang extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listViewbarang;
    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatbarang);
        listViewbarang = (ListView) findViewById(R.id.listviewbaranglist);
        listViewbarang.setOnItemClickListener(this);
        getJSON();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(lihatbarang.this, "Mengambil Data", "Mohon Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            private void showEmployee() {
                JSONObject jsonObject = null;
                ArrayList<HashMap<String, String>> list = new
                        ArrayList<HashMap<String, String>>();
                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray result =
                            jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jo = result.getJSONObject(i);
                        String idbarang = jo.getString(konfigurasi.TAG_ID);
                        String jeniskeripik = jo.getString(konfigurasi.TAG_NAMAJENIS);
                        String rasakeripik = jo.getString(konfigurasi.TAG_RASA);
                        String harga = jo.getString(konfigurasi.TAG_HARGA);


                        HashMap<String, String> employees = new HashMap<>();
                        employees.put(konfigurasi.TAG_ID, idbarang);
                        employees.put(konfigurasi.TAG_NAMAJENIS, jeniskeripik);
                        employees.put(konfigurasi.TAG_RASA, rasakeripik);
                        employees.put(konfigurasi.TAG_HARGA, harga);
                        list.add(employees);
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
                ListAdapter adapter = new SimpleAdapter(
                        lihatbarang.this, list, R.layout.activity_listitembarang,
                        new String[]{konfigurasi.TAG_ID, konfigurasi.TAG_NAMAJENIS,
                                konfigurasi.TAG_RASA,konfigurasi.TAG_HARGA},
                        new int[]{R.id.listidbarang,R.id.listidjenis,
                                R.id.listidrasa,R.id.listidharga,});
                listViewbarang.setAdapter(adapter);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}