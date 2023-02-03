package com.example.quizyfi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizyfi.databinding.ActivityQuestionBinding
import com.example.quizyfi.databinding.ActivityQuizBinding
import com.example.quizyfi.models.Question
import com.example.quizyfi.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    var quizzes : MutableList<Quiz>? = null
    var questions:MutableMap<String,Question>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFireStore()
        setUpEvent()
    }

    private fun setUpEvent(){
        binding.btnprevious.setOnClickListener{
            index--
            bindViews()
        }
        binding.btnNxt.setOnClickListener{

                index++
                bindViews()

        }
        binding.btnSubmit.setOnClickListener{

            val intent = Intent(this, ResultActivity::class.java)
            val json  = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
            finish()

        }
    }

    private fun setUpFireStore()
    {
       val  firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date!=null){
            firestore.collection("quizzes").whereEqualTo("title",date).get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty)
                    {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].question
                        bindViews()
                    }
                }
        }
    }

    private fun bindViews(){
        binding.btnprevious.visibility = View.GONE
        binding.btnNxt.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE

        if(index == 1)
        {
            binding.btnNxt.visibility = View.VISIBLE
        }
    else if(index == questions!!.size )
        {
            binding.btnSubmit.visibility = View.VISIBLE
            binding.btnprevious.visibility = View.VISIBLE
        }
    else{
            binding.btnprevious.visibility = View.VISIBLE
            binding.btnNxt.visibility = View.VISIBLE

        }

       val question = questions!!["question$index"]
        question?.let {
            binding.description.text = it.description
            val optionAdaptor = OptionAdaptor(this,it)
            binding.optionList.layoutManager = LinearLayoutManager(this)
            binding.optionList.adapter = optionAdaptor
            binding.optionList.setHasFixedSize(true)
        }


    }


}