package com.francisco.cafuture_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistryJavaActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEdad;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;

    //VARIABLES DE LOS DATOS A REGISTRAR
    private String name= "";
    private String edad= "";
    private String email= "";
    private String password= "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry_java);

        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        mEditTextName= findViewById(R.id.nombre_usuario);
        mEditTextEdad= findViewById(R.id.edad_usuario);
        mEditTextEmail= findViewById(R.id.email);
        mEditTextPassword= findViewById(R.id.password);
        mButtonRegister= findViewById(R.id.register2);

        mButtonRegister.setOnClickListener(v -> {
            name=mEditTextName.getText().toString();
            edad=mEditTextEdad.getText().toString();
            email=mEditTextEmail.getText().toString();
            password=mEditTextPassword.getText().toString();

            if(!name.isEmpty() && !edad.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                if(password.length() >= 6){
                    registerUser();
                }else{
                    Toast.makeText(RegistryJavaActivity.this, "La contraseña debe de tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(RegistryJavaActivity.this, "Debes de completar los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Map<String, Object> map= new HashMap<>();
                map.put("Nombre", name);
                map.put("Edad", edad);

                String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                mDatabase.child("Usuarios").child("clientes").child(id).setValue(map).addOnCompleteListener(task2 -> {
                    if(task2.isSuccessful()){
                        startActivity(new Intent(RegistryJavaActivity.this, HomeActivity.class));
                        finish();
                    }else{
                        Toast.makeText(RegistryJavaActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(RegistryJavaActivity.this, "No se a podido registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}