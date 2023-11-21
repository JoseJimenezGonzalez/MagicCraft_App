package com.example.magiccraftapp

import android.content.Intent
import android.graphics.Color
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
        val email = binding.tietMail.text.toString()
        val password = binding.tietPassword.text.toString()
        val passwordRepeat = binding.tietRepeatPassword.text.toString()
        var validateEmail = false
        var validatePasswordNotEmpty = false
        var validatePasswordAreEquals = false
        if (email.isEmpty() || !email.contains("@") || !email.contains(".")){
            binding.tilMail.error = "El mail no puede estar vacio"
        }else{
            binding.tilMail.error = null // Limpiar el error si hay uno
            binding.tilMail.boxStrokeColor = Color.GREEN
            validateEmail = true
        }
        if(password.isEmpty() || passwordRepeat.isEmpty()){
            binding.tilPassword.error = "La contraseña es obligatioria"
            binding.tilRepeatPassword.error = "La contraseña es obligatioria"
        }else{
            binding.tilPassword.error = null
            binding.tilRepeatPassword.error = null

            binding.tilPassword.boxStrokeColor = Color.GREEN
            binding.tilRepeatPassword.boxStrokeColor = Color.GREEN
            validatePasswordNotEmpty = true
        }
        if(password != passwordRepeat){
            binding.tilPassword.error = "Las contraseñas no coinciden"
            binding.tilRepeatPassword.error = "Las contraseñas no coinciden"
        }else{
            binding.tilPassword.error = null
            binding.tilRepeatPassword.error = null

            binding.tilPassword.boxStrokeColor = Color.GREEN
            binding.tilRepeatPassword.boxStrokeColor = Color.GREEN

            validatePasswordAreEquals = true
        }
        if(validateEmail && validatePasswordNotEmpty && validatePasswordAreEquals){
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