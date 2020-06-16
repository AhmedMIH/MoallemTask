package com.example.task.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R


class SubjectAdapter(var subjectList: List<String>) :
    RecyclerView.Adapter<SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.subject_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.onBind(subjectList[position])
    }
}

class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(subject: String) {
        val subjectName = itemView.findViewById<TextView>(R.id.subjectName_TV)
        subjectName.text = subject
    }
}

