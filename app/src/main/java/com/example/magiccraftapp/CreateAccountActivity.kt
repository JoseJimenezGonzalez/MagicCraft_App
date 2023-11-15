package com.example.magiccraftapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.magiccraftapp.databinding.ActivityCreateAccountBinding
import com.example.magiccraftapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonClick()
    }

    private fun setupButtonClick() {
        //Inicializamos auth
        auth = FirebaseAuth.getInstance()
        //Listener boton crear cuenta
        binding.btnCreateAccount.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.etMail.text.toString()
        val password = binding.etPassword.text.toString()
        val passwordRepeat = binding.etRepeatPassword.text.toString()
        if (email.isEmpty()){
            Toast.makeText(this, "Introduce el correo electronico", Toast.LENGTH_SHORT).show()
        }else if(password.isEmpty() || passwordRepeat.isEmpty()){
            Toast.makeText(this, "El campo de las contraseñas tiene que rellenarse", Toast.LENGTH_SHORT).show()
        }else if(!password.equals(passwordRepeat)){
            Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show()
        }else{
            registerUser(email, password)
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(this, "El registro se ha realizado correctamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@CreateAccountActivity,LoginActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Ha ocurrido un fallo con el registro", Toast.LENGTH_SHORT).show()
            }
        }
    }
}