package com.ardhacodes.subs1_jetpack.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.databinding.ActivityDetailMovieTvBinding
import com.ardhacodes.subs1_jetpack.ui.detail.DetailViewModel.Companion.MOVIE_VIEWMDL
import com.ardhacodes.subs1_jetpack.ui.detail.DetailViewModel.Companion.TV_VIEWMDL
import com.ardhacodes.subs1_jetpack.ui.movie.MovieViewModel
import com.ardhacodes.subs1_jetpack.utils.Helper
import com.ardhacodes.subs1_jetpack.utils.Helper.EXTRA_MOVIE
import com.ardhacodes.subs1_jetpack.utils.Helper.EXTRA_TV_SHOW
import com.ardhacodes.subs1_jetpack.utils.Helper.TYPE_TVSHOW
import com.ardhacodes.subs1_jetpack.utils.Helper.setImageGlide
import com.ardhacodes.subs1_jetpack.utils.loadFromUrl
import com.ardhacodes.subs1_jetpack.viewmodel.ViewModelFactory
import com.ardhacodes.subs1_jetpack.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailMovieTvActivity : DaggerAppCompatActivity() {
    companion object {
        const val EXTRA_MOV = "extra_mov"
        const val EXTRA_CATEGORY = "extra_cat"
    }

    private lateinit var detailbinding: ActivityDetailMovieTvBinding
    private lateinit var viewModel: DetailViewModel
    val path = "https://image.tmdb.org/t/p/"
    val image_w185 = "w185"
    val image_w780 = "w780"
    private var dataCategory: String? = null

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailbinding = ActivityDetailMovieTvBinding.inflate(layoutInflater)
        val view = detailbinding.root
        setContentView(view)

//        supportActionBar?.hide()
//        viewModelProviderConfig()
//        val actionBar = supportActionBar
//        actionBar!!.title = "Detail"
//        actionBar?.setDisplayHomeAsUpEnabled(true)


        viewModel = ViewModelProvider(
            this@DetailMovieTvActivity, factory
        )[DetailViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_MOV, 0)
        val category = intent.getStringExtra(EXTRA_CATEGORY)

        if (category.equals(Helper.TYPE_MOVIE, ignoreCase = true)) {
            DetailMovie(id)
        } else if (category.equals(Helper.TYPE_TVSHOW, ignoreCase = true)) {
            DetailTv(id)
//            val actionBar = supportActionBar
//            actionBar!!.title = "Detail"
//            actionBar.setDisplayHomeAsUpEnabled(true)
        }

//        Declaration Binding
//        var titlebinding = detailbinding.tvTitle
//        var genrebinding = detailbinding.tvGenre
//        var releasebinding = detailbinding.tvYear
//        var scorebinding = detailbinding.tvScore
//        var durationbinding = detailbinding.tvDuration
//        var overviewbinding = detailbinding.tvOverview
//        var posterbinding = detailbinding.ivPoster

        //Load
//        titlebinding.text = result.title
//        genrebinding.text = result.genre
//        releasebinding.text = "Release : ${result.yearrelease}"
//        scorebinding.text = "Score : ${result.score}"
//        durationbinding.text = "Duration : ${result.duration}"
//        durationbinding.text = result.duration
//        overviewbinding.text = result.overview
        //load Image using Glide
//        Glide.with(this@DetailMovieTvActivity)
//                .load(result.poster)
//                .apply(RequestOptions())
//                .into(posterbinding)

//        Load image using helper
        //        setImageGlide(this@DetailMovieTvActivity, result.poster, detailbinding.ivPoster)

//        Databinding quick
//        detailbinding.tvTitle.text = result.title
//        detailbinding.tvGenre.text = result.genre
//        detailbinding.tvYear.text = "Release : ${result.yearrelease}"
//        detailbinding.tvScore.text = "Score : ${result.score}"
//        detailbinding.tvDuration.text = "Duration : ${result.duration}"
//        detailbinding.tvOverview.text = result.overview

    }

    private fun DetailMovie(movieId: Int) {
        viewModel.getDetailMovie(movieId).observe(this, Observer {
            loadDataMovTv(it, null)
        })
    }

    private fun DetailTv(tvShowId: Int) {
        viewModel.getDetailTv(tvShowId).observe(this, Observer {
            it?.let {
                loadDataMovTv(null, it)
            }
        })
    }

    private fun loadDataMovTv(movie: MovieEntity?, tvShow: TvEntity?) {
        val urlImage = movie?.poster_path ?: tvShow?.poster_path
        val statusFavorite = movie?.is_favorite ?: tvShow?.is_favorite

        var tvTitleBind = detailbinding.tvTitle
        var tvGenreBindAsRelease = detailbinding.tvGenre
        var tvYearBindAsPopularity = detailbinding.tvYear
        var tvScoreBindAsVote = detailbinding.tvScore
        var tvOverviewBind = detailbinding.tvOverview
        var ivPoster = detailbinding.ivPoster
        var floatActionFav = detailbinding.floatingActionFavorite

        tvTitleBind.text = movie?.title ?: tvShow?.title
        tvGenreBindAsRelease.text = "Release : ${movie?.release_date ?: tvShow?.release_date}"
        tvYearBindAsPopularity.text = "Popularity : ${movie?.popularity ?: tvShow?.popularity}"
        tvScoreBindAsVote.text = "Vote Average : ${movie?.vote_average ?: tvShow?.vote_average}"
        tvOverviewBind.text = movie?.overview ?: tvShow?.overview

        statusFavorite?.let { status ->
            setLoveButtonIsFavorite(status)
        }

        Glide.with(this@DetailMovieTvActivity)
            .load(Helper.IMAGE_URL_API + Helper.POSTER_SIZE_W185 + urlImage)
            .apply(RequestOptions())
            .into(ivPoster)

        floatActionFav.setOnClickListener {
            setUpdateDataFav(movie, tvShow)
        }

//        detailbinding.ivPoster.loadFromUrl( Helper.IMAGE_URL_API + Helper.POSTER_SIZE_W185 + urlImage)


        /* setImageWithGlide(
                 this@DetailActivity,
                 API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + film.poster, binding.imgHome
         )*/
//        detailbinding.floatingActionFavorite.setOnClickListener{
//            setFav(movie, tvShow)
//        }

    }

    private fun setLoveButtonIsFavorite(status: Boolean) {
        if (status) {
            detailbinding.floatingActionFavorite.setImageResource(R.drawable.ic_love_red)
        } else {
            detailbinding.floatingActionFavorite.setImageResource(R.drawable.ic_love_border)
        }
    }

    private fun setUpdateDataFav(movie: MovieEntity?, tvShow: TvEntity?) {
        if (movie != null) {
            if (movie.is_favorite) {

            } else {

            }
            viewModel.setMovieFavorite(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.is_favorite) {
                } else {

                }
                viewModel.setTvShowFavorite(tvShow)
            }
        }
    }

//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return super.onSupportNavigateUp()
//    }

}