package com.elohim.ElohimAplicacion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elohim.ElohimAplicacion.R;
import com.elohim.ElohimAplicacion.activities.cliente.RegisterActivity;
import com.elohim.ElohimAplicacion.activities.trabajador.RegisterTrabajadorActivity;


public class SelectOptionAuthActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mButtonIniciarSesion;
    Button mButtonRegistrarse;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Seleccionar Opcion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mButtonIniciarSesion = findViewById(R.id.btnIniciarSesion);
        mButtonRegistrarse = findViewById(R.id.btnRegistrarse);
        mButtonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
        mButtonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
    }
    public void goToLogin() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void goToRegister() {
        String typerUser = mPref.getString("user", "");
        if (typerUser.equals("cliente")){
            Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterTrabajadorActivity.class);
            startActivity(intent);
        }

    }


}
