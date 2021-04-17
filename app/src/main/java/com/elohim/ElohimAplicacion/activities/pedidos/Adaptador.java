package com.elohim.ElohimAplicacion.activities.pedidos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elohim.ElohimAplicacion.R;
import com.elohim.ElohimAplicacion.models.Pedido;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.PedidosVistaHolder> {
    public Adaptador(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    List<Pedido> pedidos;

    @NonNull
    @Override
    public PedidosVistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_pedido, parent,false);
        PedidosVistaHolder holder = new PedidosVistaHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PedidosVistaHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        holder.mTextViewNombre.setText(pedido.getNombre());
        holder.mTextViewDireccion.setText(pedido.getDireccion());
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public static class PedidosVistaHolder extends RecyclerView.ViewHolder{
        TextView mTextViewNombre, mTextViewDireccion;

        public PedidosVistaHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewNombre =itemView.findViewById(R.id.textViewNombrePedido);
            mTextViewDireccion =itemView.findViewById(R.id.textViewDireccionPedido);
        }
    }
}
