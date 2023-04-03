package com.example.fishhunt


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject


class FishHuntActivity : AppCompatActivity() {
    open val name_of_fish = "fish"
    private lateinit var res_request: TextView
    private lateinit var background_image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_hunt)
        background_image = findViewById(R.id.background_image)
        res_request = findViewById(R.id.res_request)

        make_get_request("http://10.50.16.163:5000/fish/")

    }

    fun back_to_menu(view: View) {
        val randomIntent = Intent(this, MainActivity2::class.java)
        startActivity(randomIntent)
    }

    fun go_make_photo(view: View) {
        val randomIntent = Intent(this, CameraActivity::class.java)
        randomIntent.putExtra(CameraActivity.NAME_OF_FISH, name_of_fish)
        startActivity(randomIntent)
    }

    fun make_get_request(url : String) {
        val queue = Volley.newRequestQueue(this);
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val resp : JSONObject = JSONObject(response)
                res_request.text = resp.get("name").toString()
                val description = resp.get("description").toString()
                val url_to_fish = resp.get("image").toString()
                make_picasso(url_to_fish, background_image)
            },
            Response.ErrorListener { res_request.text = "That didn't work!" })

        queue.add(stringRequest)
    }

    fun make_picasso(url : String, item : ImageView){
        Picasso.with(this)
            .load(url)
            .into(item);
    }

}
