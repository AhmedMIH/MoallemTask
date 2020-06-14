package com.example.task

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel:MainViewModel


    lateinit var myProgressBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myProgressBar = findViewById(R.id.progressBar2)

        mViewModel=ViewModelProvider(this).get(MainViewModel::class.java)

        mViewModel.video.observe(this, androidx.lifecycle.Observer {
            fetchVideoInRV(it)
        })
        mViewModel.loading.observe(this, androidx.lifecycle.Observer {
            loadingHandling(it,myProgressBar)
        })
        val subjectRV = findViewById<RecyclerView>(R.id.subject_RecyclerView)
        val subjectList =  ArrayList<String>()
        subjectList.add("physics")
        subjectList.add("Biology")
        subjectList.add("History")
        subjectList.add("Algebra")
        subjectRV.adapter = SubjectAdapter(subjectList)
        subjectRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }



    private fun fetchVideoInRV(value: String) {
        val videoRV = findViewById<RecyclerView>(R.id.video_RecyclerView)
        val videoList =  ArrayList<String>()
        videoList.add(value)
        videoRV.adapter = VideoAdapter(videoList,this)
        videoRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }



    private fun loadingHandling(it: Boolean, myProgressBar: ProgressBar) {
        if(!it){
            myProgressBar.visibility= View.INVISIBLE
        }
    }
}