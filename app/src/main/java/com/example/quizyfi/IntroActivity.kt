package com.example.quizyfi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizyfi.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth

class IntroActivity : AppCompatActivity() {

    lateinit var firebaseAuth : FirebaseAuth
    private  lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser != null)
        {
            Toast.makeText(this,"User is Already Logged in",Toast.LENGTH_LONG).show()
            redirect("MAIN")

        }
        binding.btnGet.setOnClickListener{
            redirect("LOGIN")
        }

    }
    private fun redirect(name: String){
        val intent = when(name){
            "LOGIN"->Intent(this, LoginActivity::class.java)

            "MAIN"-> Intent(this, MainActivity::class.java)

            else -> throw Exception("No path exist")
        }
        startActivity(intent)
        finish()
    }
}