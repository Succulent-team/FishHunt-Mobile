package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class ResultOkActivity : AppCompatActivity() {
    private lateinit var go: Button
    private var now_id_fish: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_ok)
        val arguments = getIntent().getExtras()
        now_id_fish = arguments?.getString("now_id_fish").toString()
    }
    fun go_fish(view: View){
        val randomIntent = Intent(this, FishHuntActivity::class.java)
        randomIntent.putExtra("now_id_fish", (now_id_fish.toInt() + 1).toString())
        startActivity(randomIntent)
        finish()
    }
    fun go_menu(view: View){
        val randomIntent = Intent(this, MainActivity2::class.java)
        randomIntent.putExtra("now_id_fish", (now_id_fish.toInt() + 1).toString())
        startActivity(randomIntent)
        finish()
    }


}