package com.francisco.cafuture_v2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.francisco.cafuture_v2.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class menu_comida_mexicana extends AppCompatActivity {

    RecyclerView recyclerViewMenu;
    MenuAdapter mAdapter;
    FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_comida_mexicana);

        recyclerViewMenu = findViewById(R.id.recyclerMenu_comida_mexicana);
        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));
        mFirestore= FirebaseFirestore.getInstance();

        //Query query = mFirestore.collection("prueba");
        //Query query = mFirestore.collection("cafes");
        Query query = mFirestore.collection("/cafes/Ww7dV8tl5vN2ii6XhUGlM3dSQ873/categorias_menu/AM7EMMqi0QueVznZi7yc/menu");
        //Query query = mFirestore.collection("prueba");

        FirestoreRecyclerOptions<Menu>firestoreRecyclerOptions=new FirestoreRecyclerOptions.Builder<Menu>().setQuery(query, Menu.class).build();

        mAdapter = new MenuAdapter(firestoreRecyclerOptions); //Checar clase MenuAdapter
        mAdapter.notifyDataSetChanged();
        recyclerViewMenu.setAdapter(mAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}