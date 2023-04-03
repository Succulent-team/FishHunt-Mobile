package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.android.volley.toolbox.Volley
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class FishHuntActivity : AppCompatActivity() {
    open val name_of_fish = "fish"
    private lateinit var name_target: TextView
    private lateinit var photo_target: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_hunt)
        name_target = findViewById(R.id.name_of_target)
        photo_target = findViewById(R.id.photo_of_target)
        val queue = Volley.newRequestQueue(this)
        val url_address = "http://192.168.20.73:5000/"
        val params = "param1=1&param2=XXX"
        val inp: InputStream? = null
        var url : URL = URL(url_address)
        var client = url.openConnection() as HttpURLConnection
//        client.connect()
//        val responseCode: Int = client.getResponseCode()
//        name_target.text = responseCode.toString()


        fun back_to_menu(view: View) {
            val randomIntent = Intent(this, MainActivity2::class.java)
            startActivity(randomIntent)
        }

        fun go_make_photo(view: View) {
            val randomIntent = Intent(this, CameraActivity::class.java)
            randomIntent.putExtra(CameraActivity.NAME_OF_FISH, name_of_fish)
            startActivity(randomIntent)
        }

//    fun web_func(url: String) {
//        val queue = Volley.newRequestQueue(this)
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            Response.Listener<String> { response ->
//                name_target.text = "tfvgbnhj"
////                name_target.text = response.toString()[0]
////                func(response)
//            },
//            Response.ErrorListener { name_target.text = "That didn't work!" })
//
//        queue.add(stringRequest)
//    }
//    private fun func(response: String){
//        var temp: JSONObject = JSONObject(response)
//        var name = temp.get("photo").toString()
//        name_target.text = temp.get("name").toString()
//        photo_target.text = "SHHHHHHHHHHHIIIIIIIISSSHHH"
//
//    }
    }
}