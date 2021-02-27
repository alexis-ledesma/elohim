package com.elohim.ElohimAplicacion.activities.trabajador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elohim.ElohimAplicacion.R;
import com.elohim.ElohimAplicacion.activities.cliente.RegisterActivity;
import com.elohim.ElohimAplicacion.includes.MyToolbar;
import com.elohim.ElohimAplicacion.models.Client;
import com.elohim.ElohimAplicacion.models.Trabajador;
import com.elohim.ElohimAplicacion.providers.AuthProvider;
import com.elohim.ElohimAplicacion.providers.ClientProvider;
import com.elohim.ElohimAplicacion.providers.TrabajadorProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import dmax.dialog.SpotsDialog;

public class RegisterTrabajadorActivity extends AppCompatActivity {

    AlertDialog mDialog;

    AuthProvider mAuthProvider;
    TrabajadorProvider mTrabajadorProvider;

    //VIEW
    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputVehiculoMarca;
    TextInputEditText mTextInputVehiculoPlaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_trabajador);

        MyToolbar.show(this, "Registro de trabajador", true);

        mAuthProvider = new AuthProvider();
        mTrabajadorProvider = new TrabajadorProvider();


        mDialog = new SpotsDialog.Builder().setContext(RegisterTrabajadorActivity.this).setMessage("Espere un momento porfavor").build();

        mButtonRegister = findViewById(R.id.btnRegistrarse);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputVehiculoMarca = findViewById(R.id.textInputVehiculoMarca);
        mTextInputVehiculoPlaca = findViewById(R.id.textInputVehiculoPlaca);
        mTextInputPassword = findViewById(R.id.textInputPassword);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickRegister();
            }
        });
    }

    void clickRegister(){
        final String name = mTextInputName.getText().toString();
        final String email = mTextInputEmail.getText().toString();
        final String vehiculoMarca = mTextInputVehiculoMarca.getText().toString();
        final String vehiculoPlaca = mTextInputVehiculoPlaca.getText().toString();
        final String password = mTextInputPassword.getText().toString();
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !vehiculoMarca.isEmpty() && !vehiculoPlaca.isEmpty()) {

            if (password.length() >= 6){
                mDialog.show();
                register(name, email, password, vehiculoMarca, vehiculoPlaca);
            }
            else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }


    }
    void register(final String name, String email, String password, String vehiculoMarca, String vehiculoPlaca){
        mAuthProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.hide();
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Trabajador trabajador = new Trabajador(id, name, email, vehiculoMarca, vehiculoPlaca);
                    create(trabajador);

                }
                else {
                    Toast.makeText(RegisterTrabajadorActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void create(Trabajador trabajador) {
        mTrabajadorProvider.create(trabajador).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Toast.makeText(RegisterTrabajadorActivity.this, "El registro se realizó exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterTrabajadorActivity.this, MapTrabajadorActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterTrabajadorActivity.this, "No se pudo crear el cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}