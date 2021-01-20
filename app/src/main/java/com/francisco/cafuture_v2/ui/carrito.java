package com.francisco.cafuture_v2.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.francisco.cafuture_v2.R;

public class carrito extends AppCompatActivity {

    Button comprar, seguir;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);


        //Acciones de los botones
        comprar=(Button)findViewById(R.id.btnComprar);

        comprar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(carrito.this, Menu.class);
                startActivity(i);

                Toast.makeText(carrito.this, "Orden realizada", Toast.LENGTH_SHORT).show();
            }


        });

        /*seguir=(Button)findViewById(R.id.seguirComprando);
        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(carrito.this, Menu.class);
                startActivity(i);

                //Toast.makeText(carrito.this, "Orden realizada", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}