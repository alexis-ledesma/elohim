package com.elohim.ElohimAplicacion.activities.trabajador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.elohim.ElohimAplicacion.R;
import com.elohim.ElohimAplicacion.activities.MainActivity;
import com.elohim.ElohimAplicacion.activities.pedidos.Adaptador;
import com.elohim.ElohimAplicacion.includes.MyToolbar;
import com.elohim.ElohimAplicacion.models.Pedido;
import com.elohim.ElohimAplicacion.providers.AuthProvider;
import com.elohim.ElohimAplicacion.providers.PedidoProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrabajadorPedidosActivity extends AppCompatActivity {

    PedidoProvider mPedidoProvider;
    RecyclerView mRecyclerPedidos;


    List<Pedido> pedidos;
    Adaptador adapter;

    private AuthProvider mAuthProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajador_pedidos);
        MyToolbar.show(this, "Pedidos dispobibles", false);
        //mPedidoProvider = new PedidoProvider();

        mRecyclerPedidos = findViewById(R.id.recyclerPedido);
        mRecyclerPedidos.setLayoutManager(new LinearLayoutManager(this));

        pedidos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new Adaptador(pedidos, TrabajadorPedidosActivity.this);
        mRecyclerPedidos.setAdapter(adapter);

        mAuthProvider = new AuthProvider();

        database.getReference().child("Users").child("pedidos").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pedidos.removeAll(pedidos);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    Pedido pedido = snapshot.getValue(Pedido.class);
                    pedido.setId(snapshot.getKey());
                    if(!pedido.isEnCamino()){
                        pedidos.add(pedido);
                    }
                }
                adapter.notifyDataSetChanged();
                /*Intent intent = new Intent(TrabajadorPedidosActivity.this, MapClientActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trabajador_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    void logout() {
        mAuthProvider.logout();
        Intent intent = new Intent(TrabajadorPedidosActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}