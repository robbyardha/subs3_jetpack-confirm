package com.ardhacodes.subs1_jetpack.data.source.datalocal

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.room.MovieTvDao
import com.ardhacodes.subs1_jetpack.utils.Helper
import com.ardhacodes.subs1_jetpack.utils.SortUtils
import com.ardhacodes.subs1_jetpack.utils.SortUtils.MOVIE_ENTITIES
import com.ardhacodes.subs1_jetpack.utils.SortUtils.TV_SHOW_ENTITIES
import javax.inject.Inject


//class LocalDataSource private constructor(private val mMovieTvDao: MovieTvDao) {
//class LocalDataSource(private val modMovieTvDao: MovieTvDao) {

class LocalDataSource @Inject constructor(private val movieTvDao: MovieTvDao) {

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = movieTvDao.getListMovie()

    fun getListFavMovies(): DataSource.Factory<Int, MovieEntity> = movieTvDao.getListMovieFavorite()

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = movieTvDao.getDetailMovieById(movieId)

    fun insertMoviesFav(movies: List<MovieEntity>) = movieTvDao.insertFavMovies(movies)

    fun setUpdateDataMoviesFav(movie: MovieEntity) {
        movie.is_favorite = !movie.is_favorite
        movieTvDao.updateFavMovie(movie)
    }

    fun getAllTvList(): DataSource.Factory<Int, TvEntity> = movieTvDao.getListTv()

    fun getListFavTvs(): DataSource.Factory<Int, TvEntity> = movieTvDao.getListTvFavorite()

    fun getDetailTv(tvId: Int): LiveData<TvEntity> = movieTvDao.getDetailTvById(tvId)

    fun insertTvFav(tv: List<TvEntity>) = movieTvDao.insertFavTv(tv)

    fun setUpdateDataTvFav(tv: TvEntity) {
        tv.is_favorite = !tv.is_favorite
        movieTvDao.updateFavTv(tv)
    }
}