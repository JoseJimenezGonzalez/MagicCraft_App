package com.example.magiccraftapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.magiccraftapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomClick()
    }

    private fun setupBottomClick() {
        //Inicializamos aut
        auth = FirebaseAuth.getInstance()
        //Listener Ceate account
        binding.tvCreateAccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, CreateAccountActivity::class.java)
            startActivity(intent)
        }
        //Listener Login
        binding.btnLogin.setOnClickListener {
            val mail = binding.etMail.text.toString()
            val password = binding.etPassword.text.toString()
            checkData(mail, password)
        }
    }

    //Compruebo que no mete ni usuario ni contraseña vacia
    private fun checkData(mail: String, password: String) {
        if(mail.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "No puede haber campos vacios", Toast.LENGTH_SHORT).show()
        }else{
            login(mail, password)
        }
    }
    //Compruebo si existe en mi base de datos
    private fun login(mail: String, password: String) {
        auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(this) { task->
            if (task.isSuccessful){
                val user = auth.currentUser
                navigateToMainIfAuthenticated(user)
            }else{
                Toast.makeText(this, "Fallo en la autenticacion, revisa mail y/o contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //Revisa que no sea null: Desconectado o no iniciado sesion, problemas autenticacion, usuario eliminado
    private fun navigateToMainIfAuthenticated(user: FirebaseUser?) {
        if(user != null){
            //El usuario esta en base de datos y mail y pass son correctos
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show()
        }
    }
}