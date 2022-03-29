package com.example.ujikom_james_tauhid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.HashMap;

public class datakeripik extends AppCompatActivity {
    private EditText edidbarang, edjenis, edrasa, edharga;
    private Button btaddata;
    private Button btviewdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datakeripik);
        edidbarang = (EditText) findViewById(R.id.editidbarang);
        edjenis = (EditText) findViewById(R.id.editiJenis);
        edrasa = (EditText) findViewById(R.id.editidrasa);
        edharga = (EditText) findViewById(R.id.editidharga);
        btaddata = (Button) findViewById(R.id.btnaddbarang);
        btviewdata = (Button) findViewById(R.id.btnaddviewdatabarang);

        btaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idbarang = edidbarang.getText().toString().trim();
                final String jenis = edjenis.getText().toString().trim();
                final String rasa = edrasa.getText().toString().trim();
                final String harga = edharga.getText().toString().trim();


                class AddNilai extends AsyncTask<Void, Void, String> {
                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(datakeripik.this, "Menambahkan...", "Tunggu...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(datakeripik.this, s,
                                Toast.LENGTH_LONG).show();

                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put(konfigurasi.KEY_EMP_ID, idbarang);
                        params.put(konfigurasi.KEY_EMP_NAMAJENIS, jenis);
                        params.put(konfigurasi.KEY_EMP_RASA, rasa);
                        params.put(konfigurasi.KEY_EMP_HARGA, harga);


                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(konfigurasi.URL_ADD,
                                params);
                        return res;
                    }
                }
                AddNilai ae = new AddNilai();
                ae.execute();


            }


        });
        btviewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(datakeripik.this, lihatbarang.class));
            }
        });


    }
}