package com.francisco.cafuture_v2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.francisco.cafuture_v2.R;

public class MenuAdapter extends FirestoreRecyclerAdapter<Menu, MenuAdapter.ViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
    */
    public MenuAdapter(@NonNull FirestoreRecyclerOptions<Menu> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Menu menu) {
        holder.textViewTitulo.setText(menu.getTitulo());
        holder.textViewPrecio.setText(menu.getPrecio());
        holder.textViewDescripcion.setText(menu.getDescripcion());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitulo;
        TextView textViewDescripcion;
        TextView textViewPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitulo=itemView.findViewById(R.id.txt_title);
            textViewDescripcion=itemView.findViewById(R.id.txt_precio);
            textViewPrecio=itemView.findViewById(R.id.txt_desc);

        }
    }

}
