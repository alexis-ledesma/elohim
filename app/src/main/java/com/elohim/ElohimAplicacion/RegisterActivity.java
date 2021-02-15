package com.elohim.ElohimAplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elohim.ElohimAplicacion.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences mPref;
    FirebaseAuth mAuth;
    DatabaseReference  mDatabase;

    //VIEW
    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;
    TextInputEditText mTextInputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        mButtonRegister = findViewById(R.id.btnRegistrarse);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputPassword = findViewById(R.id.textInputPassword);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    registerUser();
            }
        });
    }

    void registerUser(){
        final String name = mTextInputName.getText().toString();
        final String email = mTextInputEmail.getText().toString();
        final String password = mTextInputPassword.getText().toString();
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

            if (password.length() >= 6){
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUser(name, email);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }


    }
    void saveUser(String name, String email) {
        String selectedUser = mPref.getString("user", "");
        User user = new User();
        user.setEmail(email);
        user.setName(name);

        if (selectedUser.equals("trabajador"))  {
            mDatabase.child("Users").child("trabajadores").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Falló la operación", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if (selectedUser.equals("cliente")){
            mDatabase.child("Users").child("clientes").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Falló la operación", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}