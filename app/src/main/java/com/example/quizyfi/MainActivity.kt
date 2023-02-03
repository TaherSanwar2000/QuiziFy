package com.example.quizyfi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizyfi.databinding.ActivityQuizBinding
import com.example.quizyfi.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    lateinit var adapter: QuizAdapter
    private var quizlist  = mutableListOf<Quiz>()

    lateinit var firestore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()

    }


    fun setUpViews(){
        setUpFireStore()
        setUpDrawLayout()
        setUpRecyclerView()
        setDatePicker()
    }


    private fun setDatePicker(){
        binding.datePicker.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"DatePicker")
            datePicker.addOnPositiveButtonClickListener {

                val dateFormat = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormat.format(Date(it))

                val intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener{

            }
            datePicker.addOnCancelListener{

            }
        }
    }


    private fun setUpFireStore(){
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener{ value , error ->
            if (value == null || error != null)
            {
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            quizlist.clear()
            quizlist.addAll(value.toObjects((Quiz::class.java)))
            adapter.notifyDataSetChanged()
        }

    }

    private fun setUpRecyclerView(){
        adapter = QuizAdapter(this,quizlist)
        binding.quizRecycler.layoutManager = GridLayoutManager(this,2)
        binding.quizRecycler.adapter = adapter
    }

    fun  setUpDrawLayout(){
        setSupportActionBar(binding.appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.mainDrawer,R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
        binding.navView.setNavigationItemSelectedListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
            binding.mainDrawer.closeDrawers()
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}