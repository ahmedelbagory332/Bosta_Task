package com.example.bosta_task.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.bosta_task.R
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso


class PhotoZoom : AppCompatActivity() {
    private var imageUrl:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_zoom)

        val toolbar: Toolbar = findViewById(R.id.toolbarPhotoZooming)
        val photoView:PhotoView = findViewById(R.id.photo_view)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.photo_zoom)

        val extras = intent.extras
        if (extras != null) {
             imageUrl = extras.getString("imageUrl").toString()
            Picasso.get().load(imageUrl).into(photoView)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share->{
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out this is photo $imageUrl")
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }
        }
        return true
    }


}