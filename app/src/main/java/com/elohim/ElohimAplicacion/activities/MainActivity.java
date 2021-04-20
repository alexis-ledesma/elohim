package com.elohim.ElohimAplicacion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elohim.ElohimAplicacion.R;
import com.elohim.ElohimAplicacion.activities.cliente.FormularuoClientActivity;
import com.elohim.ElohimAplicacion.activities.trabajador.TrabajadorPedidosActivity;
import com.google.firebase.auth.FirebaseAuth;

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

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            String user = mPref.getString("user", "");
            if (user.equals("cliente")){
                Intent intent = new Intent(MainActivity.this, FormularuoClientActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(MainActivity.this, TrabajadorPedidosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}