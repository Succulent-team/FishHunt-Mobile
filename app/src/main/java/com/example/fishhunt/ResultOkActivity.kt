package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

class ResultOkActivity : AppCompatActivity() {
    var stringId = ""
    var randomId : Int? = 0
    var res = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_ok)

        val arguments = getIntent().getExtras()
        stringId = arguments!!.getString("arrayId").toString()
        randomId = arguments?.getInt("randomId")

        Log.d("-", stringId)
        for (i in stringId){
            if (i.toString() != randomId.toString()){
                res += i
            }
        }
        Log.d("-", res)
    }
    fun go_fish(view: View){
        val randomIntent = Intent(this, FishHuntActivity::class.java)
        randomIntent.putExtra("arrayId", res)
        startActivity(randomIntent)
        finish()

    }
    fun go_menu(view: View){
        val randomIntent = Intent(this, MainActivity2::class.java)
        startActivity(randomIntent)
        finish()

    }


}