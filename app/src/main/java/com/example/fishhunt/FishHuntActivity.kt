package com.example.fishhunt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


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
        val url = "http://192.168.20.73:5000"
        val jsonParams = JSONObject()
        jsonParams.put("name", "10");
        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonParams,
            Response.Listener<JSONObject> { response ->
                var temp: JSONObject = response
                name_target.text = temp.get("name").toString()
                val photo = temp.get("photo").toString()
                val double_photo = photo + photo + photo
                photo_target.text = "fvygbnhjm"

            },
            Response.ErrorListener { name_target.text = "Not Work" })
        queue.add(request)

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