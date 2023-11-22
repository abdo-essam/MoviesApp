package com.example.moviesapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.data.model.home.ResponseMoviesLIst
import com.example.moviesapp.databinding.ItemHomeMoviesLastBinding
import javax.inject.Inject


class LastMoviesAdapter @Inject constructor() : RecyclerView.Adapter<LastMoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemHomeMoviesLastBinding
    private var moviesList = emptyList<ResponseMoviesLIst.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHomeMoviesLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(moviesList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = moviesList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItems(item: ResponseMoviesLIst.Data) {
            binding.apply {
                movieNameTxt.text = item.title
                movieRateTxt.text = item.imdbRating
                movieCountryTxt.text = item.country
                movieYearTxt.text = item.year
                moviePosterImg.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((ResponseMoviesLIst.Data) -> Unit)? = null

    fun setData(data: List<ResponseMoviesLIst.Data>) {
        val moviesDiffUtil = MoviesDiffUtils(moviesList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<ResponseMoviesLIst.Data>, private val newItem: List<ResponseMoviesLIst.Data>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }
    }
}