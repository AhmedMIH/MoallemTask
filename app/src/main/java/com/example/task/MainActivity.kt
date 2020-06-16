package com.example.task

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.Adapter.SubjectAdapter
import com.example.task.Adapter.VideoAdapter
import com.example.task.brodcastReciever.ConnectionLiveData
import com.example.task.brodcastReciever.ConnectionModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var mViewModel:MainViewModel
    var snackBar: Snackbar?=null
    private val subjectList =  ArrayList<String>()
    lateinit var myProgressBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myProgressBar = findViewById(R.id.progressBar2)
        val connectionLiveData =
            ConnectionLiveData(
                applicationContext
            )
        mViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        connectionLiveData.observe(this, androidx.lifecycle.Observer {
            showNetworkError(it)
        })
        mViewModel.video.observe(this, androidx.lifecycle.Observer {
            fetchVideoInRV(it)
        })
        mViewModel.loading.observe(this, androidx.lifecycle.Observer {
            loadingHandling(it,myProgressBar)
        })
        addSubject()
        fetchSubjectInRV()
    }

    private fun addSubject(){
        subjectList.add("physics")
        subjectList.add("Biology")
        subjectList.add("History")
        subjectList.add("Algebra")
    }

    private fun fetchSubjectInRV(){
        val subjectRV = findViewById<RecyclerView>(R.id.subject_RecyclerView)
        subjectRV.adapter = SubjectAdapter(subjectList)
        subjectRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun fetchVideoInRV(value: String) {
        val videoRV = findViewById<RecyclerView>(R.id.video_RecyclerView)
        val videoList =  ArrayList<String>()
        videoList.add(value)
        videoRV.adapter = VideoAdapter(videoList, this)
        videoRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun loadingHandling(it: Boolean, myProgressBar: ProgressBar) {
        if(!it){
            myProgressBar.visibility= View.INVISIBLE
        }
    }

    private fun showNetworkError(it: ConnectionModel) {
        if (!it.isConnected) {
            snackBar = Snackbar.make(findViewById(R.id.main),"You are offline ",Snackbar.LENGTH_LONG)
            snackBar!!.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar!!.view.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            snackBar!!.show()
        } else {
            snackBar?.dismiss()
        }
    }
}