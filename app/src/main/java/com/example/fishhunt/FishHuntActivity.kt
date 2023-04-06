package com.example.fishhunt


import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
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
import java.io.ByteArrayOutputStream
import java.util.*


class FishHuntActivity : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var description: Button
    private lateinit var photo: ImageView
    private var now_id_fish: String = ""
    private val url_start = "https://9650-2a03-d000-7005-f007-abad-39be-d4b6-b7ef.ngrok.io"


    private val REQUEST_TAKE_PHOTO = 1
    private lateinit var take_photo: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_hunt)
        name = findViewById(R.id.name)
        description = findViewById(R.id.description)
        photo = findViewById(R.id.photo)

        take_photo = findViewById(R.id.take_photo)

        val arguments = getIntent().getExtras()
        now_id_fish = arguments?.getString("now_id_fish").toString()

        val url_for_get_info = "$url_start/fishes_list/$now_id_fish/"

        make_get_request(url_for_get_info)

        take_photo.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }

    }

    fun back_to_menu(view: View) {
        val randomIntent = Intent(this, MainActivity2::class.java)
        startActivity(randomIntent)
        finish()
    }

//    fun go_make_photo(view: View) {
//        val randomIntent = Intent(this, CameraActivity::class.java)
//        randomIntent.putExtra("now_id_fish", now_id_fish)
//        randomIntent.putExtra("url_start", url_start)
//        startActivity(randomIntent)
//        finish()
//    }

    fun make_get_request(url : String) {
        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
//                Log.d("-", response.toString())
                val json = JSONObject(response)
                val name_resp = "Ваша цель:\n" + reverse(json.getString("name"))
                val description_resp = reverse(json.getString("description"))
                name.text = name_resp
                description.text = description_resp
                val url_to_fish_resp = json.getString("image")
//                Log.d("-", "2")
                make_picasso(url_to_fish_resp, photo)
//                Log.d("-", "3")
            },
            Response.ErrorListener {Log.d("-", "Not work internet") })
//        Log.d("-", "0")
        queue.add(stringRequest)
//        Log.d("-", "work")
    }

    fun reverse(start_str : String):String{
        var result = ""
        var temp : Char
        val byte_string = start_str.toByteArray()
        var i = 0
        val end_str = byte_string.size
        while (i < end_str){
            if (byte_string[i].toInt()==61*-1){
                temp = getAlphaChar(byte_string[i+1].toInt()*-1, byte_string[i+3].toInt()*-1)
                i += 4
                result += temp
            }
            else if(byte_string[i].toInt()==13 && byte_string[i+1].toInt()==10){
                i += 2
                result += "\n"
            }
            else{
                when(byte_string[i].toInt()){
                    (32) -> result += " "
                    (44) -> result += ","
                    (46) -> result += "."
                }
                i += 1
            }
        }
        return result
    }

    fun getAlphaChar(a1:Int, a2:Int):Char{
        val low_s = "абвгдежзийклмнопрстуфхцчшщъыьэюя"
        val up_s = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
        val s = " "
        if (a1==112){
            if (a2 > 80){
                val t = (a2 - 112) * -1
                return up_s[t]
            }
            val t = (a2 - 80) * -1
            return low_s[t]

        }
        if (a1==111){
            val t = (a2 - 128) * -1 + 16
            return low_s[t]
        }

        return s[0]
    }




    fun make_picasso(url : String, item : ImageView){
        val url_test = "https://raw.githubusercontent.com/matv864/progamist/main/images/third.jpg"
        Picasso.with(this)
            .load(url_test)
            .centerCrop()
            .fit()
            .into(item);
        Log.d("pic", url)
    }
//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------
//camera

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
//                Log.d("--", response.toString())
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
        finish()
    }
    fun fail(){
        val randomIntent = Intent(this, ResultFailActivity::class.java)
        randomIntent.putExtra("now_id_fish", now_id_fish)
        startActivity(randomIntent)
        finish()
    }

    fun not_work(){
        take_photo.text = "Плохое соединение"
    }

}
