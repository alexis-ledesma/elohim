package com.elohim.ElohimAplicacion.activities.pedidos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elohim.ElohimAplicacion.R;
import com.elohim.ElohimAplicacion.activities.cliente.FormularuoClientActivity;
import com.elohim.ElohimAplicacion.activities.cliente.MapClientActivity;
import com.elohim.ElohimAplicacion.activities.trabajador.TrabajadorPedidosActivity;
import com.elohim.ElohimAplicacion.models.Pedido;
import com.elohim.ElohimAplicacion.providers.PedidoProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class Adaptador extends RecyclerView.Adapter<Adaptador.PedidosVistaHolder> {
    Context mContext;
    public Adaptador(List<Pedido> pedidos, Context c) {
        mContext = c;
        this.pedidos = pedidos;
    }

    List<Pedido> pedidos;

    @NonNull
    @Override
    public PedidosVistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_pedido, parent,false);
        PedidosVistaHolder holder = new PedidosVistaHolder(v,mContext);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PedidosVistaHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        holder.mTextViewId.setText(pedido.getId());
        holder.mTextViewidCliente.setText(pedido.getIdCliente());
        holder.mTextViewNombre.setText(pedido.getNombre());
        holder.mTextViewDireccion.setText(pedido.getDireccion());
        holder.mTextViewNumTelefono.setText(pedido.getNumero());
        holder.mTextViewRoles.setText(String.valueOf(pedido.getRoles()));
        holder.mTextViewConchas.setText(String.valueOf(pedido.getConchas()));
        holder.mTextViewPanques.setText(String.valueOf(pedido.getPanques()));
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public static class PedidosVistaHolder extends RecyclerView.ViewHolder{
        TextView mTextViewId;
        TextView mTextViewidCliente;
        TextView mTextViewNombre;
        TextView mTextViewDireccion;
        TextView mTextViewNumTelefono;
        TextView mTextViewRoles;
        TextView mTextViewConchas;
        TextView mTextViewPanques;

        Button mButtonAceptarPedido;

        PedidoProvider mPedidoProvider;
        AlertDialog mDialog;
        Context mContext2;

        public PedidosVistaHolder(@NonNull View itemView, Context c) {
            super(itemView);
            mContext2 = c;
            mTextViewId =itemView.findViewById(R.id.textViewId);
            mTextViewidCliente =itemView.findViewById(R.id.textViewidClientePedido);
            mTextViewNombre =itemView.findViewById(R.id.textViewNombrePedido);
            mTextViewDireccion =itemView.findViewById(R.id.textViewDireccionPedido);
            mTextViewNumTelefono =itemView.findViewById(R.id.textViewNumTelefonoPedido);
            mTextViewRoles =itemView.findViewById(R.id.textViewRolesPedido);
            mTextViewConchas =itemView.findViewById(R.id.textViewConchasPedido);
            mTextViewPanques =itemView.findViewById(R.id.textViewPanquesPedido);

            mButtonAceptarPedido = itemView.findViewById(R.id.btnPedido);

            mPedidoProvider = new PedidoProvider();

            mTextViewId.setVisibility(View.GONE);
            mTextViewidCliente.setVisibility(View.GONE);

            mDialog = new SpotsDialog.Builder().setContext(mContext2).setMessage("Espere un momento porfavor").build();

            mButtonAceptarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.show();
                    clickAceptarPedido();
                }
            });
        }

        private void clickAceptarPedido(){
            final String id = mTextViewId.getText().toString();
            final String idCliente = mTextViewidCliente.getText().toString();
            final String nombre = mTextViewNombre.getText().toString();
            final String direccion = mTextViewDireccion.getText().toString();
            final String numeroTelefono = mTextViewNumTelefono.getText().toString();
            final int roles = Integer.parseInt(mTextViewRoles.getText().toString());
            final int conchas = Integer.parseInt(mTextViewConchas.getText().toString());
            final int panques = Integer.parseInt(mTextViewPanques.getText().toString());

            actualizarPedido(id, idCliente, nombre, direccion, numeroTelefono,roles, conchas, panques);
        }

        private void actualizarPedido(String id, String idCliente,String nombre,String direccion,String numeroTelefono,int roles,int conchas,int panques){
            boolean enCamino = true;
            Pedido pedido = new Pedido(idCliente,nombre,direccion,numeroTelefono,roles,conchas,panques,enCamino);
            mDialog.hide();
            update(pedido, id);
        }
        private void update(Pedido pedido, String id){
            mPedidoProvider.create(pedido, id).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(mContext2, MapClientActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mContext2.startActivity(intent);
                    } else {
                        Toast.makeText(mContext2, "No se pudo tomar el pedido", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
