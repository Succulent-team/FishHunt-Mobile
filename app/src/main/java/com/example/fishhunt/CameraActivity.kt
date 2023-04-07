package com.example.fishhunt

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.util.*

class CameraActivity : AppCompatActivity() {
    private val REQUEST_TAKE_PHOTO = 1
    var now_id_fish = ""
    var url_start = ""
    var bool_access : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)


        val arguments = getIntent().getExtras()
        now_id_fish = arguments?.getString("now_id_fish").toString()
        url_start = arguments?.getString("url_start").toString()


        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            val randomIntent = Intent(this, MainActivity2::class.java)
            Toast.makeText(applicationContext, "CameraActivity_error", Toast.LENGTH_SHORT).show()
            startActivity(randomIntent)
            finish()
        }
        if (bool_access){
            access()
        }
        else{
            fail()
        }
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
        else{
            val randomIntent = Intent(this, FishHuntActivity::class.java)
            Toast.makeText(applicationContext, "CameraCancel_error", Toast.LENGTH_SHORT).show()
            startActivity(randomIntent)
            finish()
        }
    }
    fun make_post_request(url : String, image : String) {
        val queue = Volley.newRequestQueue(this);
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                var temp = JSONObject(response)
                bool_access = temp.getBoolean("result")
            },
            Response.ErrorListener {
                Toast.makeText(applicationContext, "Network_error", Toast.LENGTH_SHORT).show()
                not_work()

            })
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
        startActivity(randomIntent)
        finish()
    }
    fun fail(){
        val randomIntent = Intent(this, ResultFailActivity::class.java)
        startActivity(randomIntent)
        finish()
    }

    fun not_work(){
        val randomIntent = Intent(this, FishHuntActivity::class.java)
        startActivity(randomIntent)
        finish()
    }









}