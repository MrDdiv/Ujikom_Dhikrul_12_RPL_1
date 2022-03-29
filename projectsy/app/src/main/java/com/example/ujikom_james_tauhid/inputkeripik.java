package com.example.ujikom_james_tauhid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.HashMap;

public class inputkeripik extends AppCompatActivity {
    private EditText idbarang,jeniskrpk,rskrpk,harga;
    private Button btntmbhkeripik;
    private Button btnviewkeripik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputkeripik);

        idbarang=(EditText) findViewById(R.id.editidbarang);
        jeniskrpk=(EditText) findViewById(R.id.editidjeniskeripik);
        rskrpk=(EditText) findViewById(R.id.editidrasakeripik);
        harga=(EditText) findViewById(R.id.editidhargakeripik);



        btntmbhkeripik=(Button) findViewById(R.id.btnaddkeripik);
        btnviewkeripik=(Button) findViewById(R.id.btnaddviewkeripik);

        btntmbhkeripik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idbarangkrpk =idbarang .getText().toString().trim();
                final String jenis =jeniskrpk.getText().toString().trim();
                final String rasa = rskrpk.getText().toString().trim();
                final String hargakrpk = harga.getText().toString().trim();

                class Addtmbahkeripik extends AsyncTask<Void, Void, String> {
                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(inputkeripik.this,
                                "Menambahkan...", "Tunggu...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(inputkeripik.this, s,
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put(konfigurasi.KEY_EMP_ID, idbarangkrpk);
                        params.put(konfigurasi.KEY_EMP_NAMAJENIS, jenis);
                        params.put(konfigurasi.KEY_EMP_RASA, rasa);
                        params.put(konfigurasi.KEY_EMP_HARGA, hargakrpk);

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(konfigurasi.URL_ADD,
                                params);
                        return res;
                    }
                }
                Addtmbahkeripik ae = new Addtmbahkeripik();
                ae.execute();


            }


        });
        btnviewkeripik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(inputkeripik.this,datakeripik.class));
            }
        });

    }
}