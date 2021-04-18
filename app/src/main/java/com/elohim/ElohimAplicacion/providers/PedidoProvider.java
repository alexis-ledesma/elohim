package com.elohim.ElohimAplicacion.providers;

import androidx.annotation.NonNull;

import com.elohim.ElohimAplicacion.models.Pedido;
import com.elohim.ElohimAplicacion.models.Trabajador;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PedidoProvider {
    DatabaseReference mDatabase;

    public PedidoProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("pedidos");
    }
    public Task<Void> create(Pedido pedido, String id){
        return mDatabase.child(id).setValue(pedido);
    }
    public Task<Void> push(Pedido pedido){
        return mDatabase.push().setValue(pedido);
    }
    /*public Task<Void> agregarValor(List<Pedido> pedidos){
        return mDatabase.
    }*/
}
