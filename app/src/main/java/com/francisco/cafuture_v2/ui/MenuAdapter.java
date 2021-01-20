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
        //Boolean isFollowing = (Boolean) dataSnapshot.getValue();
        double postres_precio=menu.getPrice();
        double postres_tiempo=menu.getTime();
        boolean postres_disponibilidad= (Boolean) menu.isAvailable();

        String postres_price = String.valueOf(postres_precio);
        String postres_time = String.valueOf(postres_tiempo);
        String postres_available = "";


        if(postres_disponibilidad){
            postres_available = "Disponible";
        }
        else{
            postres_available = "No disponible";
        }


        holder.textViewName.setText(menu.getName());
        holder.textViewPrice.setText("$" + postres_price);
        holder.textViewTime.setText(postres_time + " minutos");
        holder.textViewImg.setText(menu.getImg());
        holder.textViewDescription.setText(menu.getDescription());
        holder.textViewAvailable.setText(postres_available);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewPrice;
        TextView textViewTime;
        TextView textViewImg;
        TextView textViewDescription;
        TextView textViewAvailable;

        /*
        TextView textViewDescripcion;
        TextView textViewPrecio;
        */

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            /*
            textViewTitulo=itemView.findViewById(R.id.txt_title);
            textViewDescripcion=itemView.findViewById(R.id.txt_precio);
            textViewPrecio=itemView.findViewById(R.id.txt_desc);

             */
            textViewName=itemView.findViewById(R.id.txt_postres_name);
            textViewPrice=itemView.findViewById(R.id.txt_postres_price);
            textViewTime=itemView.findViewById(R.id.txt_postres_time);
            textViewImg=itemView.findViewById(R.id.txt_postres_img);
            textViewDescription=itemView.findViewById(R.id.txt_postres_desc);
            textViewAvailable=itemView.findViewById(R.id.txt_postres_available);

        }
    }

}
