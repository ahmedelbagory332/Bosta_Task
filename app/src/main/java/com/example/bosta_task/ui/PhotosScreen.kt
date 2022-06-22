package com.example.bosta_task.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.bosta_task.Models.PhotoModel
import com.example.bosta_task.R
import com.example.bosta_task.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class PhotosScreen : AppCompatActivity() , SearchView.OnQueryTextListener {

    private val userViewModel:UserViewModel by  viewModels()
    @Inject
    lateinit var photoAdapter: PhotoAdapter
    private var photos: List<PhotoModel> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_screen)
        val extras = intent.extras
        if (extras != null) {
            val albumId = extras.getInt("albumId")
            userViewModel.getPhotos(albumId)
        }

        val toolbar: Toolbar = findViewById(R.id.photoToolbar)
        val recyclerViewPhotoList: RecyclerView = findViewById(R.id.photoList)
        val progressBar: ProgressBar = findViewById(R.id.dataLoading2)
        val linearLayoutErrorMessage: LinearLayout = findViewById(R.id.layoutError)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.album_details)

        recyclerViewPhotoList.adapter = photoAdapter

        photoAdapter.onPhotoClick={
            val i = Intent(this@PhotosScreen, PhotoZoom::class.java)
            i.putExtra("imageUrl", it)
            startActivity(i)
        }

        userViewModel.photos.observe(this, Observer {
            photoAdapter.submitList(it)
            photos = it
        })


        userViewModel.isLoading.observe(this, Observer {

            if (it) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
        })

        userViewModel.showErrorMessage.observe(this, Observer {

            if (it) {
                linearLayoutErrorMessage.visibility = View.VISIBLE
                progressBar.visibility = View.GONE

            }else {
                userViewModel.getUserData()
                linearLayoutErrorMessage.visibility = View.GONE
                progressBar.visibility = View.VISIBLE

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.queryHint = "search in images"
        return true

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val userinput = newText!!.lowercase(Locale.ROOT)
        val newlist: MutableList<PhotoModel> = mutableListOf()


        for (search in photos) {
            if (search.title!!.contains(userinput)) {
                newlist.add(search)

            }

        }
        if(newlist.isEmpty()){
            Toast.makeText(this,getString(R.string.no_results_found), Toast.LENGTH_LONG).show()
            photoAdapter.submitList(newlist)
        }
        else{
            photoAdapter.submitList(newlist)
        }

        return true
    }

}