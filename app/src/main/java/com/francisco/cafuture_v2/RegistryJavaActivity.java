package com.francisco.cafuture_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistryJavaActivity extends AppCompatActivity {

    Spinner comboCarreras;
    private EditText mEditTextName;
    private EditText mEditTextApellidos;
    private EditText mEditTextEdad;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;

    //VARIABLES DE LOS DATOS A REGISTRAR
    private String carrera="";
    private String apellidos="";
    private String name= "";
    private String edad= "";
    private String email= "";
    private String password= "";

    FirebaseAuth mAuth;
    FirebaseFirestore mFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry_java);

        mAuth=FirebaseAuth.getInstance();
        mFireStore=FirebaseFirestore.getInstance();

        comboCarreras=findViewById(R.id.comboCarrerasSpinner);

        mEditTextName= findViewById(R.id.nombre_usuario);
        mEditTextApellidos=findViewById(R.id.apellidos_usuario);
        mEditTextEdad= findViewById(R.id.edad_usuario);
        mEditTextEmail= findViewById(R.id.email);
        mEditTextPassword= findViewById(R.id.password);
        mButtonRegister= findViewById(R.id.register2);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.menu_carreras, android.R.layout.simple_spinner_item);
        comboCarreras.setAdapter(adapter);

        mButtonRegister.setOnClickListener(v -> {
            name=mEditTextName.getText().toString();
            apellidos=mEditTextApellidos.getText().toString();
            edad=mEditTextEdad.getText().toString();
            email=mEditTextEmail.getText().toString();
            password=mEditTextPassword.getText().toString();
            comboCarreras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    carrera=parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            if(!name.isEmpty() && !apellidos.isEmpty() && !edad.isEmpty() && !carrera.isEmpty() && !email.isEmpty() && !password.isEmpty()){
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

                FirebaseUser user= mAuth.getCurrentUser();
                String userID= user.getUid();

                DocumentReference documentReference = mFireStore.collection("users").document(userID);
                Map<String, Object> map= new HashMap<>();
                map.put("Carrera",carrera);
                map.put("Apellidos",apellidos);
                map.put("Nombre", name);
                map.put("Edad", edad);

                documentReference.set(map).addOnSuccessListener(aVoid -> {

                    Log.d("Registro", "Data extra de user creado");
                    startActivity(new Intent(RegistryJavaActivity.this, HomeActivity.class));
                    finish();

                }).addOnFailureListener(e -> Toast.makeText(RegistryJavaActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show());

            }else{
                Toast.makeText(RegistryJavaActivity.this, "No se a podido registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
