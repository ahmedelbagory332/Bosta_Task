package com.example.bosta_task.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bosta_task.Models.AlbumsModel
import com.example.bosta_task.R
import javax.inject.Inject

class AlbumAdapter  @Inject constructor() :
    ListAdapter<AlbumsModel, AlbumAdapter.AlbumsViewHolder>(AlbumsDiffCallback) {

    var onAlbumClick: ((id: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder =
        AlbumsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_albums_row, parent, false))

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = getItem(position)
        holder.textViewAlbumTitle.text = album.title
        holder.itemView.setOnClickListener {
            onAlbumClick?.invoke(album.id!!)
        }

    }


    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewAlbumTitle : TextView = itemView.findViewById(R.id.albumTitle)


    }


}

object AlbumsDiffCallback : DiffUtil.ItemCallback<AlbumsModel>() {
    override fun areItemsTheSame(oldItem: AlbumsModel, newItem: AlbumsModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AlbumsModel, newItem: AlbumsModel): Boolean {
        return oldItem.id == newItem.id
    }
}

