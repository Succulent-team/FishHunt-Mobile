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

class CameraActivity : AppCompatActivity() {
//    private lateinit var some: TextView
    private val REQUEST_TAKE_PHOTO = 1
    private lateinit var imageView: ImageView
    private lateinit var take_photo: Button
    private lateinit var go_check_photo: Button
    private var now_id_fish: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        imageView = findViewById(R.id.camera_view)
        take_photo = findViewById(R.id.take_photo)
        take_photo = findViewById(R.id.take_photo)
        take_photo.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }

        val arguments = getIntent().getExtras()
        now_id_fish = arguments?.getString("now_id_fish")
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
            imageView.setImageBitmap(thumbnailBitmap)
        }
    }




}
