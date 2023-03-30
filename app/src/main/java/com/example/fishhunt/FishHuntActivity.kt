package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class FishHuntActivity : AppCompatActivity() {
    val name_of_fish = "517"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_hunt)
    }
    fun back_to_menu(view: View){
        val randomIntent = Intent(this, MainActivity2::class.java)
        startActivity(randomIntent)
    }

    fun go_make_foto(view: View){
        val randomIntent = Intent(this, CameraActivity::class.java)
        randomIntent.putExtra(CameraActivity.NAME_OF_FISH, name_of_fish)
        startActivity(randomIntent)
    }

}