package com.example.moviesapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.model.home.ResponseGenreLIst
import com.example.moviesapp.databinding.ItemHomeGenreListBinding
import javax.inject.Inject


class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private lateinit var binding: ItemHomeGenreListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHomeGenreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseGenreLIst.ResponseGenreLIstItem) {
            binding.apply {
                nameTxt.text = item.name
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<ResponseGenreLIst.ResponseGenreLIstItem>() {
        override fun areItemsTheSame(oldItem: ResponseGenreLIst.ResponseGenreLIstItem, newItem: ResponseGenreLIst.ResponseGenreLIstItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseGenreLIst.ResponseGenreLIstItem, newItem: ResponseGenreLIst.ResponseGenreLIstItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}
