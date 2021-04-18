package com.elohim.ElohimAplicacion.activities.trabajador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.elohim.ElohimAplicacion.R;
import com.elohim.ElohimAplicacion.activities.cliente.MapClientActivity;
import com.elohim.ElohimAplicacion.activities.pedidos.Adaptador;
import com.elohim.ElohimAplicacion.models.Pedido;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajador_pedidos);
        //mPedidoProvider = new PedidoProvider();

        mRecyclerPedidos = findViewById(R.id.recyclerPedido);
        mRecyclerPedidos.setLayoutManager(new LinearLayoutManager(this));

        pedidos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new Adaptador(pedidos, TrabajadorPedidosActivity.this);
        mRecyclerPedidos.setAdapter(adapter);

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
}