package com.elohim.ElohimAplicacion.providers;

import com.elohim.ElohimAplicacion.models.Trabajador;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrabajadorProvider {
    DatabaseReference mDatabase;

    public TrabajadorProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("trabajadores");
    }
    public Task<Void> create(Trabajador trabajador){
        return mDatabase.child(trabajador.getId()).setValue(trabajador);
    }
}
