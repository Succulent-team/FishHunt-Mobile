package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun go_to_game(view: View){
        val intent = Intent(this, FishHuntActivity::class.java)
        intent.putExtra("arrayId", arrayOf(1..4))
        startActivity(intent)
        finish()

    }
}