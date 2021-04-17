package com.elohim.ElohimAplicacion.activities.cliente;

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
import com.elohim.ElohimAplicacion.activities.trabajador.MapTrabajadorActivity;
import com.elohim.ElohimAplicacion.activities.trabajador.RegisterTrabajadorActivity;
import com.elohim.ElohimAplicacion.models.Client;
import com.elohim.ElohimAplicacion.models.Pedido;
import com.elohim.ElohimAplicacion.models.Trabajador;
import com.elohim.ElohimAplicacion.providers.AuthProvider;
import com.elohim.ElohimAplicacion.providers.ClientProvider;
import com.elohim.ElohimAplicacion.providers.PedidoProvider;
import com.elohim.ElohimAplicacion.providers.TrabajadorProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class FormularuoClientActivity extends AppCompatActivity {
    Button mButtonPedido;
    TextInputEditText mTextInputNombreCliente;
    TextInputEditText mTextInputDireccion;
    TextInputEditText mTextInputNumero;
    TextInputEditText mTextInputRoles;
    TextInputEditText mTextInputConchas;
    TextInputEditText mTextInputPanques;


    AlertDialog mDialog;
    FirebaseAuth mAut;
    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;
    PedidoProvider mPedidoProvider;
    DatabaseReference mDatabase;
    //SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formularuo_client);

        mDialog = new SpotsDialog.Builder().setContext(FormularuoClientActivity.this).setMessage("Espere un momento porfavor").build();

        mButtonPedido = findViewById(R.id.btnPedido);
        mTextInputNombreCliente = findViewById(R.id.TextInputNombreCliente);
        mTextInputDireccion = findViewById(R.id.TextInputDireccion);
        mTextInputNumero = findViewById(R.id.TextInputNumero);
        mTextInputRoles = findViewById(R.id.TextInputRoles);
        mTextInputConchas = findViewById(R.id.TextInputConchas);
        mTextInputPanques = findViewById(R.id.TextInputPanques);

        //mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        mAut = FirebaseAuth.getInstance();
        mAuthProvider = new AuthProvider();
        mClientProvider = new ClientProvider();
        mPedidoProvider = new PedidoProvider();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mButtonPedido = findViewById(R.id.btnPedido);

        mButtonPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicPedido();
            }
        });
    }
    private void clicPedido(){
        final String nombre = mTextInputNombreCliente.getText().toString();
        final String direccion = mTextInputDireccion.getText().toString();
        final String numero = mTextInputNumero.getText().toString();
        if (!nombre.isEmpty() && !direccion.isEmpty() && !numero.isEmpty() && !mTextInputRoles.getText().toString().isEmpty()
        && !mTextInputConchas.getText().toString().isEmpty() && !mTextInputPanques.getText().toString().isEmpty()) {
            final int roles = Integer.parseInt(mTextInputRoles.getText().toString());
            final int conchas = Integer.parseInt(mTextInputConchas.getText().toString());
            final int panques = Integer.parseInt(mTextInputPanques.getText().toString());
            mDialog.show();
            register2(nombre, direccion, numero, roles, conchas, panques);
        } else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
    private void register2(final String nombre, String direccion, String numero, int roles, int conchas, int panques){
        String idCliente = mAut.getCurrentUser().getUid();
        mDialog.hide();
        Pedido pedido = new Pedido(idCliente, nombre, direccion, numero, roles, conchas, panques);
        create2(pedido);
    }

    private void create2(Pedido pedido) {
        mPedidoProvider.push(pedido).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(FormularuoClientActivity.this, MapClientActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(FormularuoClientActivity.this, "No se pudo crear el pedido", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}