package com.example.fishhunt

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.util.*

class CameraActivity : AppCompatActivity() {

//    private lateinit var some: TextView
    private val REQUEST_TAKE_PHOTO = 1
    private lateinit var imageView: ImageView
    private lateinit var take_photo: Button
    private lateinit var some: TextView

    private var now_id_fish: String = ""
    private var url_start: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        imageView = findViewById(R.id.camera_view)
        take_photo = findViewById(R.id.take_photo)
        some = findViewById(R.id.some)
        val arguments = getIntent().getExtras()
        now_id_fish = arguments?.getString("now_id_fish").toString()
        url_start = arguments?.getString("url_start").toString()
        take_photo.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }


    }

    fun back_to_info_fish(view : View){
        val intent = Intent(this, FishHuntActivity::class.java)
        intent.putExtra("now_id_fish", now_id_fish)
        startActivity(intent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            // Фотка сделана, извлекаем миниатюру картинки
            val thumbnailBitmap = data?.extras?.get("data") as Bitmap

            val url_for_get_info = "$url_start/validate_fish/"

            val bitmap = thumbnailBitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val image = stream.toByteArray()
            val encodedString: String = Base64.getEncoder().encodeToString(image)
            make_post_request(url_for_get_info, encodedString)
//            imageView.setImageBitmap(thumbnailBitmap)
        }
    }

    fun make_post_request(url : String, image : String) {
        val queue = Volley.newRequestQueue(this);
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                var temp = JSONObject(response)
                if (temp.getBoolean("result")){
                    access()
                }
                else{
                    fail()
                }
            },
            Response.ErrorListener { not_work() })
        {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["fish_id"] = now_id_fish
                params["image"] = image
                return params
            }

        }

        queue.add(stringRequest)
    }

    fun access(){
        val randomIntent = Intent(this, ResultOkActivity::class.java)
        randomIntent.putExtra("now_id_fish", now_id_fish)
        startActivity(randomIntent)
    }
    fun fail(){
        val randomIntent = Intent(this, ResultFailActivity::class.java)
        randomIntent.putExtra("now_id_fish", now_id_fish)
        startActivity(randomIntent)
    }

    fun not_work(){
        take_photo.text = "not work"
        fail()
    }




}
