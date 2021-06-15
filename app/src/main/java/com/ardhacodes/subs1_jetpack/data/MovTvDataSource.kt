package com.ardhacodes.subs1_jetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.vo.Resource

interface MovTvDataSource {
    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getFavMovie(): LiveData<PagedList<MovieEntity>>

    fun setFavMovie(movie: MovieEntity)

    fun getTv(): LiveData<Resource<PagedList<TvEntity>>>

    fun getTvDetail(tvShowId: Int): LiveData<TvEntity>

    fun getFavTv(): LiveData<PagedList<TvEntity>>

    fun setFavTv(tvShow: TvEntity)
}