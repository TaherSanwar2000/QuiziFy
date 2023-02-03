package com.example.quizyfi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizyfi.models.Quiz
import com.example.quizyfi.utils.ColorPicker
import com.example.quizyfi.utils.IconPicker

class QuizAdapter(val  context: Context,val quizes:List<Quiz>):RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    inner class  QuizViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var textViewTitle : TextView = itemView.findViewById(R.id.quizTitle)
        var iconView : ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer : CardView = itemView.findViewById(R.id.cardContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{
            Toast.makeText(context,quizes[position].title,Toast.LENGTH_LONG).show()
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE",quizes[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizes.size
    }
}


