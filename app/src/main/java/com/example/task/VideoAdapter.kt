package com.example.task

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class VideoAdapter(var videoList: List<String>,var context:Context) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.onBind(videoList[position],context)
    }
}

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(video: String, context: Context) {
        val play = itemView.findViewById<ImageView>(R.id.play_Button)
        play.setOnClickListener {
            val i = Intent(context,PlayingVideoActivity::class.java)
            i.putExtra("videoUrl",video)
            context.startActivity(i)
        }

    }
}

