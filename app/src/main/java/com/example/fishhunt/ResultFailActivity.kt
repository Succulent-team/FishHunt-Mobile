package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class ResultFailActivity : AppCompatActivity() {
    private var arrayId : IntArray? = IntArray(0)
    private var randomId : Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_fail)

        val arguments = getIntent().getExtras()
        arrayId = arguments?.getIntArray("arrayId")
        randomId = arguments?.getInt("randomId")
    }
    fun go_fish(view: View){
        val randomIntent = Intent(this, FishHuntActivity::class.java)
        randomIntent.putExtra("arrayId", arrayId)
        startActivity(randomIntent)
        finish()
    }
    fun go_menu(view: View){
        val randomIntent = Intent(this, MainActivity2::class.java)
        startActivity(randomIntent)
        finish()
    }


}