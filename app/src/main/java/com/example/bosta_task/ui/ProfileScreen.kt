package com.example.bosta_task.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.bosta_task.R
import com.example.bosta_task.adapter.AlbumAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileScreen : AppCompatActivity() {

    private val userViewModel:UserViewModel by  viewModels()

    @Inject
    lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_screen)

        userViewModel.getUserData()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.profile_screen)

         val userNameTextView:TextView = findViewById(R.id.userNameTv)
        val userAddressTextView:TextView = findViewById(R.id.addressTv)
        val progressBar:ProgressBar = findViewById(R.id.dataLoading)
        val linearLayoutErrorMessage:LinearLayout = findViewById(R.id.layoutError)
        val linearLayoutUserAddress:LinearLayout = findViewById(R.id.addressLayout)
        val linearLayoutAlbums:LinearLayout = findViewById(R.id.albumLayout)
        val linearLayoutUserName:LinearLayout = findViewById(R.id.userNameLayout)
        val recyclerViewAlbumList:RecyclerView = findViewById(R.id.albumList)
         recyclerViewAlbumList.adapter = albumAdapter
        albumAdapter.onAlbumClick={
            val i = Intent(this@ProfileScreen, PhotosScreen::class.java)
            i.putExtra("albumId", it)
            startActivity(i)
        }
        userViewModel.userAlbums.observe(this, Observer {
            albumAdapter.submitList(it)

        })

        userViewModel.userName.observe(this, Observer {
            userNameTextView.text = it
        })

        userViewModel.userAddress.observe(this, Observer {
            userAddressTextView.text = it
        })

        userViewModel.isLoading.observe(this, Observer {

            if (it){
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                linearLayoutAlbums.visibility = View.VISIBLE
                linearLayoutUserName.visibility = View.VISIBLE
                linearLayoutUserAddress.visibility = View.VISIBLE
            }
        })

        userViewModel.showErrorMessage.observe(this, Observer {

            if (it) {
                linearLayoutErrorMessage.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                linearLayoutAlbums.visibility = View.GONE
                linearLayoutUserName.visibility = View.GONE
                linearLayoutUserAddress.visibility = View.GONE

            }else {
                userViewModel.getUserData()
                linearLayoutErrorMessage.visibility = View.GONE
                progressBar.visibility = View.VISIBLE

            }
        })


    }
}