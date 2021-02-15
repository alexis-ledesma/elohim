package com.elohim.ElohimAplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;
public class LoginActivity extends AppCompatActivity {

    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;
    Button mButtonLogin;

    FirebaseAuth mAut;
    DatabaseReference mDatabase;

    AlertDialog mDialog;

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputPassword = findViewById(R.id.textInputPassword);
        mButtonLogin = findViewById(R.id.btnLogin);

        mAut = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("Espere un momento porfavor").build();


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login() {

        String email = mTextInputEmail.getText().toString();
        String password = mTextInputPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {

         if(password.length() >= 6) {
             mDialog.show();

            mAut.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "La sesion ha iniciado exitosamente", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                    }
                    mDialog.dismiss();
                }
            });


         }
         else{
             Toast.makeText(this, "La Contraseña debe incluir almenos 6 caracteres", Toast.LENGTH_SHORT).show();
         }

        }
        else{
            Toast.makeText(this, "La Contraseña y el Correo son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }
}