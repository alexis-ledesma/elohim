package com.elohim.ElohimAplicacion.providers;

import com.elohim.ElohimAplicacion.models.Pedido;
import com.elohim.ElohimAplicacion.models.Trabajador;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PedidoProvider {
    DatabaseReference mDatabase;

    public PedidoProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("clientes").child("pedidos");
    }
    public Task<Void> create(Pedido pedido){
        return mDatabase.child(pedido.getId()).setValue(pedido);
    }
}
