package com.elohim.ElohimAplicacion.activities.trabajador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elohim.ElohimAplicacion.R;

import com.elohim.ElohimAplicacion.activities.cliente.DetailRequestActivity;
import com.elohim.ElohimAplicacion.activities.cliente.FormularuoClientActivity;
import com.elohim.ElohimAplicacion.models.Pedido;
import com.elohim.ElohimAplicacion.providers.PedidoProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;

import java.util.List;

public class PedidoTrabajadorActivity extends RecyclerView.Adapter<PedidoTrabajadorActivity.PedidosVistaHolder> {
    Context mContext;
    public PedidoTrabajadorActivity(List<Pedido> pedidos, Context c) {
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
        holder.mTextViewBollos.setText(String.valueOf(pedido.getBollos()));
        holder.mTextViewDonas.setText(String.valueOf(pedido.getDonas()));
        holder.mTextViewPastes.setText(String.valueOf(pedido.getPastes()));
        holder.mTextViewTotal.setText(String.valueOf(pedido.getTotal()));
        holder.mTextViewLatitud.setText(String.valueOf(pedido.getDestinoLatitud()));
        holder.mTextViewLongitud.setText(String.valueOf(pedido.getDestinoLongitud()));
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
        TextView mTextViewTotal;
        TextView mTextViewLatitud;
        TextView mTextViewLongitud;
        TextView mTextViewBollos;
        TextView mTextViewDonas;
        TextView mTextViewPastes;

        Button mButtonAceptarPedido;

        PedidoProvider mPedidoProvider;
        AlertDialog mDialog;
        Context mContext2;

        public PedidosVistaHolder(@NonNull View itemView, Context c) {
            super(itemView);
            mContext2 = c;
            mTextViewId =itemView.findViewById(R.id.textViewId);
            mTextViewidCliente =itemView.findViewById(R.id.textViewIdClientePedido);
            mTextViewNombre =itemView.findViewById(R.id.textViewNombrePedido);
            mTextViewDireccion =itemView.findViewById(R.id.textViewDireccionPedido);
            mTextViewNumTelefono =itemView.findViewById(R.id.textViewNumTelefonoPedido);
            mTextViewRoles =itemView.findViewById(R.id.textViewRolesPedido);
            mTextViewConchas =itemView.findViewById(R.id.textViewConchasPedido);
            mTextViewPanques =itemView.findViewById(R.id.textViewPanquesPedido);
            mTextViewTotal =itemView.findViewById(R.id.textViewTotalPedido);
            mTextViewLatitud =itemView.findViewById(R.id.textViewLatitudPedido);
            mTextViewLongitud =itemView.findViewById(R.id.textViewIdLongitudPedido);
            mTextViewBollos =itemView.findViewById(R.id.textViewBollosPedido);
            mTextViewDonas =itemView.findViewById(R.id.textViewDonasPedido);
            mTextViewPastes =itemView.findViewById(R.id.textViewPastesPedido);

            mButtonAceptarPedido = itemView.findViewById(R.id.btnPedido);

            mPedidoProvider = new PedidoProvider();

            mTextViewId.setVisibility(View.GONE);
            mTextViewidCliente.setVisibility(View.GONE);
            mTextViewLatitud.setVisibility(View.GONE);
            mTextViewLongitud.setVisibility(View.GONE);

            mButtonAceptarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
            final float total = Float.parseFloat(mTextViewTotal.getText().toString());
            final double latitud = Double.parseDouble(mTextViewLatitud.getText().toString());
            final double longitud = Double.parseDouble(mTextViewLongitud.getText().toString());
            final int bollos = Integer.parseInt(mTextViewBollos.getText().toString());
            final int donas = Integer.parseInt(mTextViewDonas.getText().toString());
            final int pastes = Integer.parseInt(mTextViewPastes.getText().toString());

            actualizarPedido(id, idCliente, nombre, direccion, numeroTelefono,roles, conchas, panques, total, latitud, longitud, bollos, donas, pastes);
        }

        private void actualizarPedido(String id, String idCliente,String nombre,String direccion,String numeroTelefono,int roles,int conchas,int panques, float total, double latitud, double longitud, int bollos, int donas, int pastes){
            boolean enCamino = true;
            Pedido pedido = new Pedido(idCliente,nombre,direccion,numeroTelefono,roles,conchas,panques,enCamino,total,latitud,longitud,bollos,donas,pastes);
            update(pedido, id);
        }
        private void update(Pedido pedido, String id){
            mPedidoProvider.create(pedido, id).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        if (!Places.isInitialized()) {
                            Places.initialize(mContext2.getApplicationContext(), mContext2.getResources().getString(R.string.google_maps_key));
                        }
                        if (pedido.getDestinoLatitud() != 0.0){
                            Intent intent = new Intent(mContext2, DetailRequestActivity.class);
                            intent.putExtra("origin_lat", 18.33921503491766);
                            intent.putExtra("origin_lng", -99.52976789108938);
                            intent.putExtra("destination_lat", pedido.getDestinoLatitud());
                            intent.putExtra("destination_lng",  pedido.getDestinoLongitud());
                            intent.putExtra("origin", pedido.getDireccion());
                            intent.putExtra("destination","Panaderia Elohim");
                            mContext2.startActivity(intent);
                        } else {
                            Toast.makeText(mContext2, "Este pedido no tiene destino", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext2, "No se pudo tomar el pedido", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
