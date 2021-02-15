package com.elohim.ElohimAplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mButtonSoyTrabajador;
    Button mButtonSoyCliente;

    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();

        mButtonSoyCliente = findViewById(R.id.btnSoyCliente);
        mButtonSoyTrabajador = findViewById(R.id.btnSoyTrabajador);

        mButtonSoyCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user", "cliente");
                editor.apply();
                goToSelectAuth();
            }
        });
        mButtonSoyTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user", "trabajador");
                editor.apply();
                goToSelectAuth();
            }
        });
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}