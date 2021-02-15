package com.elohim.ElohimAplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SelectOptionAuthActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mButtonIniciarSesion;
    Button mButtonRegistrarse;

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
    }
    public void goToLogin() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void goToRegister() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


}
