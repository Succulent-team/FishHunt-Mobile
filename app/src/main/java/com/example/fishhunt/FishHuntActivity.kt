package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class FishHuntActivity : AppCompatActivity() {
    open val name_of_fish = "fish"
    private lateinit var name_target: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_hunt)
        name_target = findViewById(R.id.name_of_target)
        name_target.text = "bvrenhdjvrnm"

        val url = "http://10.50.16.163:5000"
        web_func(url)

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

    private fun web_func(url: String) {
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                name_target.text = response.toString()
            },
            Response.ErrorListener { name_target.text = "That didn't work!" })

        queue.add(stringRequest)
    }


}