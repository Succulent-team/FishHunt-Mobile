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
    /*fun countMe (view: View) {
        val countString = counter.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++
        counter.text = count.toString()
        val toasting = Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT)
        toasting.show()
    }*/
    fun go_to_game(view: View){
        val randomIntent = Intent(this, FishHuntActivity::class.java)
        startActivity(randomIntent)
    }
}