package com.example.bosta_task.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bosta_task.Models.PhotoModel
import com.example.bosta_task.R
import com.squareup.picasso.Picasso
import javax.inject.Inject


class PhotoAdapter  @Inject constructor(private val ctx:Application) :
    ListAdapter<PhotoModel, PhotoAdapter.PhotoViewHolder>(PhotoDiffCallback) {

    var onPhotoClick: ((url: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_photo_row, parent, false))

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)

        Picasso.get().load(photo.url).into(holder.imageViewPhoto)

        holder.itemView.setOnClickListener {
            onPhotoClick?.invoke(photo.url!!)
        }

    }


    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageViewPhoto : ImageView = itemView.findViewById(R.id.photo)


    }


}

object PhotoDiffCallback : DiffUtil.ItemCallback<PhotoModel>() {
    override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
        return oldItem.id == newItem.id
    }
}

