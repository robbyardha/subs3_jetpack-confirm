package com.ardhacodes.subs1_jetpack.ui.movie

import android.content.Intent
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.databinding.ItemMovBinding
import com.ardhacodes.subs1_jetpack.ui.CallbackMovTv
import com.ardhacodes.subs1_jetpack.ui.detail.DetailMovieTvActivity
import com.ardhacodes.subs1_jetpack.utils.Helper
import com.ardhacodes.subs1_jetpack.utils.loadFromUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(private val callbackMov: CallbackMov) : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(CALLBACK_UTILITY) {
    private var listMovie = ArrayList<MovieTvEntity>()
    val path = "https://image.tmdb.org/t/p/"
    val image_w185 = "w185"
    val image_w780 = "w780"

    companion object {
        private val CALLBACK_UTILITY = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.idmovie == newItem.idmovie
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class MovieViewHolder(val binding: ItemMovBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding){
                movie.poster_path?.let{
                    binding.ivPoster.loadFromUrl(Helper.IMAGE_URL_API + Helper.POSTER_SIZE_W185 + it)
                }

                /*    Glide.with(itemView)
                            .load(film.poster)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .centerCrop()
                            .into(imgMovie)*/
//                binding.itemTitle.text = movie.title
//                binding.itemTitle.text = movie.title

                var itemTitleBind = binding.itemTitle
                var itemGenreBind = binding.itemGenre
                var itemScoreBind = binding.itemScore
                var itemYearBind = binding.itemYearrelease
                itemTitleBind.text = movie.title
                itemGenreBind.text = "Release : ${movie.release_date}"
                itemScoreBind.text = "Popularity : ${movie.popularity}"
                itemYearBind.text = "Vote Average : ${movie.vote_average}"

//                itemTitle.text = movie.title
//                itemGenre.text = "Release : ${movie.release_date}"
//                itemScore.text = "Popularity : ${movie.popularity}"
//                itemYearrelease.text = "Vote Average : ${movie.vote_average}"

                itemView.setOnClickListener {
                    callbackMov.onItemClicked(movie)
                }

                /* Glide.with(itemView.context)
                     .load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + )
                     .apply(RequestOptions())
                     .into(imgMovie)*/
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemMovieBindings = ItemMovBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBindings)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

//    override fun getItemCount(): Int {
//        return listMovie.size
//    }

}