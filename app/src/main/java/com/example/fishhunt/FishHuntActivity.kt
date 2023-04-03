package com.example.fishhunt


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
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
    private lateinit var name: TextView
    private lateinit var description: Button
    private lateinit var photo: ImageView
    private var now_id_fish: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_hunt)
        name = findViewById(R.id.name)
        description = findViewById(R.id.description)
        photo = findViewById(R.id.photo)

        val arguments = getIntent().getExtras()
        now_id_fish = arguments?.getString("now_id_fish")
        val url_for_get_info = "http://10.50.16.163:5000/fish/$now_id_fish/"
        make_get_request(url_for_get_info)

    }

    fun back_to_menu(view: View) {
        val randomIntent = Intent(this, MainActivity2::class.java)
        startActivity(randomIntent)
    }

    fun go_make_photo(view: View) {
        val randomIntent = Intent(this, CameraActivity::class.java)
        randomIntent.putExtra("id_fish", now_id_fish)
        startActivity(randomIntent)
    }

    fun make_get_request(url : String) {
        val queue = Volley.newRequestQueue(this);
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val resp : JSONObject = JSONObject(response)
                name.text = resp.get("name").toString()
                description.text = resp.get("description").toString()
                val url_to_fish_resp = resp.get("image").toString()
                make_picasso(url_to_fish_resp, photo)
            },
            Response.ErrorListener { name.text = "That didn't work!" })

        queue.add(stringRequest)
    }

    fun make_picasso(url : String, item : ImageView){
        Picasso.with(this)
            .load(url)
            .into(item);
    }

}
