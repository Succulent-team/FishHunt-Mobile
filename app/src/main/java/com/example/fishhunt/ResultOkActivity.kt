package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class ResultOkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_ok)
    }
    fun go_fish(view: View){
        val randomIntent = Intent(this, FishHuntActivity::class.java)
        startActivity(randomIntent)
        finish()

    }
    fun go_menu(view: View){
        val randomIntent = Intent(this, MainActivity2::class.java)
        startActivity(randomIntent)
        finish()

    }


}