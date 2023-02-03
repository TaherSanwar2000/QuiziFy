package com.example.quizyfi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizyfi.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var firebaseAuth : FirebaseAuth
    private  lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener{
            login()
        }

        binding.tvSignup.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun login(){
        val email = binding.etEmail.text.toString()
        val password = binding.etpassword.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Email And Password must Required", Toast.LENGTH_LONG).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(){
                if(it.isSuccessful)
                {
                    Toast.makeText(this,"You Logged in Successfully", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()

                }
            }
    }

}