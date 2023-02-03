package com.example.quizyfi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizyfi.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    lateinit var firebaseAuth : FirebaseAuth
    private  lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener{
            signup()
        }

        binding.tvLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
    private fun  signup(){
        val email = binding.etEmail.text.toString()
        val password = binding.etpassword.text.toString()
        val confirm = binding.etconfirm.text.toString()

        if(email.isBlank() || password.isBlank() || confirm.isBlank())
        {
            Toast.makeText(this,"Email and Password Can't be Empty..",Toast.LENGTH_LONG).show()
            return
        }
        if (password != confirm)
        {
            Toast.makeText(this,"Password Not Match.",Toast.LENGTH_LONG).show()
            return
        }


        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(){
                if(it.isSuccessful)
                {
                    Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()

                }
            }
    }

}