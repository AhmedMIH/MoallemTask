package com.example.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class MainViewModel :ViewModel(){

    val loading = MutableLiveData<Boolean>()
    val video = MutableLiveData<String>()
    init{
        fetchVideoFromFireStore()
    }
    private fun fetchVideoFromFireStore() {
        loading.value = true
        val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference
        val demoRef: DatabaseReference = rootRef.child("video")

        demoRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(String::class.java)
                video.value = value
                loading.value = false
            }
        })
    }
}