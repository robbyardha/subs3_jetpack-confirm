package com.ardhacodes.subs1_jetpack.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.databinding.ItemMovBinding
import com.ardhacodes.subs1_jetpack.databinding.ItemTvBinding
import com.ardhacodes.subs1_jetpack.ui.CallbackMovTv
import com.ardhacodes.subs1_jetpack.ui.detail.DetailMovieTvActivity
import com.ardhacodes.subs1_jetpack.ui.movie.MovieAdapter
import com.ardhacodes.subs1_jetpack.utils.Helper
import com.ardhacodes.subs1_jetpack.utils.loadFromUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvAdapter(val tvFragmentCallback: TvFragmentCallback) :
    PagedListAdapter<TvEntity, TvAdapter.TvViewHolder>(CALLBACK_UTILITY) {
    private val listTv = ArrayList<MovieTvEntity>()
    val path = "https://image.tmdb.org/t/p/"
    val image_w185 = "w185"
    val image_w780 = "w780"


    companion object {
        private val CALLBACK_UTILITY = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.idtv == newItem.idtv
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class TvViewHolder(private val binding: ItemTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvEntity) {
            with(binding) {
                tv.poster_path?.let {
                    binding.ivPoster.loadFromUrl(Helper.IMAGE_URL_API + Helper.POSTER_SIZE_W185 + it)
                }
                var itemTitleBind = binding.itemTitle
                var itemGenreBind = binding.itemGenre
                var itemScoreBind = binding.itemScore
                var itemYearBind = binding.itemYearrelease
//                Glide.with(itemView.context)
//                    .load(path + image_w185 + tv.poster_path)
//                    .apply(RequestOptions())
//                    .into(ivPoster)

//                itemTitle.text = tv.title
//                itemGenre.text = "Release : ${tv.release_date}"
//                itemScore.text = "Popularity : ${tv.popularity}"
//                itemYearrelease.text = "Vote Average : ${tv.vote_average}"

                itemTitleBind.text = tv.title
                itemGenreBind.text = "Release : ${tv.release_date}"
                itemScoreBind.text = "Popularity : ${tv.popularity}"
                itemYearBind.text = "Vote Average : ${tv.vote_average}"

            }
            itemView.setOnClickListener { tvFragmentCallback.onItemClick(tv) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemTvBinding =
            ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

}