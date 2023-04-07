package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private lateinit var counter : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun go_to_game(view: View){
        val intent = Intent(this, FishHuntActivity::class.java)

        intent.putExtra("now_id_fish", "1")
        startActivity(intent)
        finish()

    }
}