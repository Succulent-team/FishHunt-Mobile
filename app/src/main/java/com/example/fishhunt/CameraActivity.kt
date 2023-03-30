package com.example.fishhunt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class CameraActivity : AppCompatActivity() {
    private lateinit var some: TextView
    companion object {
        const val NAME_OF_FISH = "BAD_TEXT"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        some = findViewById(R.id.some)
        val count = intent.getStringExtra(NAME_OF_FISH)
        some.text = count.toString()
    }
}