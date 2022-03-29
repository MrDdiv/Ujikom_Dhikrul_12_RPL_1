package com.example.ujikom_james_tauhid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

public class login extends AppCompatActivity {
    EditText useran, passan;
    Button btnlogin;
    String userkey, passkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        useran=(EditText) findViewById(R.id.namauser);
        passan=(EditText) findViewById(R.id.password);
        btnlogin=(Button) findViewById(R.id.signup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userkey=useran.getText().toString();
                passkey=passan.getText().toString();

                if (userkey.equals("saya")&& passkey.equals("saya123")){
                    Toast.makeText(getApplicationContext(),"LOGIN BERHASIL",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this,homepage.class));
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                    builder.setMessage("USER NAME DAN PASSWORD SALAH").setNegativeButton("COBA LAGI",null).create().show();
                }
            }
        });
    }
}