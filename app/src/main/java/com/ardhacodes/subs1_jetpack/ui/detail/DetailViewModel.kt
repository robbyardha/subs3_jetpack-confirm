package com.ardhacodes.subs1_jetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ardhacodes.subs1_jetpack.data.MovTvRepository
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.vo.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movTvRepository: MovTvRepository) : ViewModel() {
    companion object {
        const val MOVIE_VIEWMDL = "movie"
        const val TV_VIEWMDL = "tv"
    }

    private lateinit var detailtv: LiveData<Resource<TvEntity>>
    private lateinit var detailmov: LiveData<Resource<MovieEntity>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> {
        return movTvRepository.getMovieDetail(movieId)
    }

    fun getDetailTv(tvShowId: Int): LiveData<TvEntity> {
        return movTvRepository.getTvDetail(tvShowId)
    }

    fun setMovieFavorite(movie: MovieEntity){
        movTvRepository.setFavMovie(movie)
    }

    fun setTvShowFavorite(tvShow: TvEntity){
        movTvRepository.setFavTv(tvShow)
    }
}