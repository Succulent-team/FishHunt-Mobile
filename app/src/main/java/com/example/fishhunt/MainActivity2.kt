package com.example.fishhunt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    private lateinit var counter : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        counter = findViewById(R.id.counter)
    }
    fun countMe (view: View) {
        // Get the value of the text view.
        val countString = counter.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++
        counter.text = count.toString()
        val toasting = Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT)
        toasting.show()
    }
}