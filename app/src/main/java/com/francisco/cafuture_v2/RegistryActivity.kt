package com.francisco.cafuture_v2

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

import com.francisco.cafuture_v2.R
import com.francisco.cafuture_v2.HomeActivity
import com.francisco.cafuture_v2.ProviderType
import com.francisco.cafuture_v2.RegistryActivity
import com.google.firebase.auth.FirebaseAuth

class RegistryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        setup2()
    }
    private fun setup2(){

        val register2 = findViewById<Button>(R.id.register2)
        val email2 = findViewById<EditText>(R.id.email2)
        val password2 = findViewById<EditText>(R.id.password2)

        register2.setOnClickListener {
            if (email2.text.isNotEmpty() && password2.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    email2.text.toString(),
                    password2.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }
    private fun showHome(email: String, provider: ProviderType) {
        val intent: Intent = Intent (this, HomeActivity::class.java).apply{
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(intent)
        finish()
    }
    private fun showAlert(){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error registrando al usuario, el usuario ya existe")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }
}


