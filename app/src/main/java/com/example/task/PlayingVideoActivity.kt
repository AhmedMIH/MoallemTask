package com.example.task

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.VideoView

class PlayingVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_video)
        val b:Bundle = intent.extras!!
        val url = b.get("videoUrl")
        Log.d("test",url.toString())
        val videoView = findViewById<VideoView>(R.id.videoView)
        val myUrl= Uri.parse(url.toString())
        videoView.setVideoURI(myUrl)
        videoView.start()
    }
}

