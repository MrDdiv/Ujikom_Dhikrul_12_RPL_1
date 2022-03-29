package com.example.ujikom_james_tauhid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.HashMap;

public class datapenjualan extends AppCompatActivity {
    private EditText edidjual,edidbarang,edidtersedia,edidterjual;
    private Button btadjualan;
    private Button btviewjualan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datapenjualan);
        edidjual=(EditText)findViewById(R.id.editidpenjualan);
        edidbarang=(EditText)findViewById(R.id.editidbarang);
        edidtersedia=(EditText)findViewById(R.id.editidjumlahtersedia);
        edidterjual=(EditText)findViewById(R.id.editidjumlahterjual);
        btadjualan=(Button) findViewById(R.id.btnaddpenjualan);
        btviewjualan=(Button) findViewById(R.id.btnaddviewpenjualan);
        btadjualan=(Button) findViewById(R.id.btnaddpenjualan);

        btadjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idjual = edidjual.getText().toString().trim();
                final String idbarang = edidbarang.getText().toString().trim();
                final String idbrgtersedia = edidtersedia.getText().toString().trim();
                final String idterjual = edidterjual.getText().toString().trim();



                class AddNilai extends AsyncTask<Void, Void, String> {
                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(datapenjualan.this,"Menambahkan...", "Tunggu...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(datapenjualan.this, s,
                                Toast.LENGTH_LONG).show();

                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put(konfigurasi.KEY_EMP_IDJUAL, idjual);
                        params.put(konfigurasi.KEY_EMP_IDBARANG, idbarang);
                        params.put(konfigurasi.KEY_EMP_JUMLAHTERSEDIA, idbrgtersedia);
                        params.put(konfigurasi.KEY_EMP_JUMLAHTERJUAL, idterjual);





                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(konfigurasi.URL_EMP_ADD_JUALAN,
                                params);
                        return res;
                    }
                }
                AddNilai ae = new AddNilai();
                ae.execute();


            }


        });
        btviewjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(datapenjualan.this,homepage.class));

            }
        });



    }
}