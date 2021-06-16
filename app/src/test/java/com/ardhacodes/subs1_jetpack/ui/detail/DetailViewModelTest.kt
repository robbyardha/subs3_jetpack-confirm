package com.ardhacodes.subs1_jetpack.ui.detail

import android.graphics.Movie
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ardhacodes.subs1_jetpack.data.MovTvRepository
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.utils.MoviesTvDataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import androidx.lifecycle.Observer
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.utils.MoviesTvDataDummyForTest
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest
{
    private lateinit var viewmodel: DetailViewModel
    val dataMov = MoviesTvDataDummyForTest.DataMovies()[0]
    val dataTv = MoviesTvDataDummyForTest.DataTvShow()[0]
    val movieId = dataMov.idmovie
    val tvShowId = dataTv.idtv

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movTvRepository: MovTvRepository

    @Mock
    private lateinit var observer: Observer<MovieTvEntity>

    @Mock
    private lateinit var obsMov: Observer<MovieEntity>


    @Mock
    private lateinit var obsTv: Observer<TvEntity>

    @get:Rule
    var instantTaskRule = InstantTaskExecutorRule()

    @Before
    fun setDataMovie() {
//        lateinit var catalogRepository: CatalogRepository
        viewmodel = DetailViewModel(movTvRepository)
//        viewmodel.setMovieById(movieId)
//        viewmodel.setTvShowById(tvShowId)
    }



    @Test
    fun getMov()
    {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dataMov

        `when`(movTvRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movEntity = viewmodel.getDetailMovie(movieId).value as MovieEntity

        assertNotNull(movEntity)
        assertEquals(dataMov.id, movEntity.id)
        assertEquals(dataMov.title, movEntity.title)
        assertEquals(dataMov.release_date, movEntity.release_date)
        assertEquals(dataMov.popularity, movEntity.popularity)
        assertEquals(dataMov.overview, movEntity.overview)
        assertEquals(dataMov.vote_average, movEntity.vote_average)
        assertEquals(dataMov.poster_path, movEntity.poster_path)
        viewmodel.getDetailMovie(movieId).observeForever(obsMov)
        verify(obsMov).onChanged(dataMov)
    }


    @Test
    fun getTv()
    {
        val tv = MutableLiveData<TvEntity>()
        tv.value = dataTv

        `when`(movTvRepository.getTvDetail(tvShowId)).thenReturn(tv)
        val tvEntity = viewmodel.getDetailTv(tvShowId).value as TvEntity

        assertNotNull(tvEntity)
        assertEquals(dataTv.id, tvEntity.id)
        assertEquals(dataTv.title, tvEntity.title)
        assertEquals(dataTv.release_date, tvEntity.release_date)
        assertEquals(dataTv.popularity, tvEntity.popularity)
        assertEquals(dataTv.overview, tvEntity.overview)
        assertEquals(dataTv.vote_average, tvEntity.vote_average)
        assertEquals(dataTv.poster_path, tvEntity.poster_path)
        viewmodel.getDetailTv(tvShowId).observeForever(obsTv)
        verify(obsTv).onChanged(dataTv)
    }

    @Test
    fun getAllData()
    {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dataMov

        `when`(movTvRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movEntity = viewmodel.getDetailMovie(movieId).value as MovieEntity

        assertNotNull(movEntity)
        assertEquals(dataMov.id, movEntity.id)
        assertEquals(dataMov.title, movEntity.title)
        assertEquals(dataMov.release_date, movEntity.release_date)
        assertEquals(dataMov.popularity, movEntity.popularity)
        assertEquals(dataMov.overview, movEntity.overview)
        assertEquals(dataMov.vote_average, movEntity.vote_average)
        assertEquals(dataMov.poster_path, movEntity.poster_path)
        viewmodel.getDetailMovie(movieId).observeForever(obsMov)
        verify(obsMov).onChanged(dataMov)

        val tv = MutableLiveData<TvEntity>()
        tv.value = dataTv

        `when`(movTvRepository.getTvDetail(tvShowId)).thenReturn(tv)
        val tvEntity = viewmodel.getDetailTv(tvShowId).value as TvEntity

        assertNotNull(tvEntity)
        assertEquals(dataTv.id, tvEntity.id)
        assertEquals(dataTv.title, tvEntity.title)
        assertEquals(dataTv.release_date, tvEntity.release_date)
        assertEquals(dataTv.popularity, tvEntity.popularity)
        assertEquals(dataTv.overview, tvEntity.overview)
        assertEquals(dataTv.vote_average, tvEntity.vote_average)
        assertEquals(dataTv.poster_path, tvEntity.poster_path)
        viewmodel.getDetailTv(tvShowId).observeForever(obsTv)
        verify(obsTv).onChanged(dataTv)
    }

//    old data

//    @Test
//    fun getData()
//    {
//        val movie = viewmodel.getMovieById()
//        val tvShow = viewmodel.getTvShowById()
//
//        assertNotNull(movie)
//        assertEquals(dataMov.title, movie?.title)
//        assertEquals(dataMov.release_date, movie?.release_date)
//        assertEquals(dataMov.popularity, movie?.popularity)
//        assertEquals(dataMov.overview, movie?.overview)
//        assertEquals(dataMov.vote_average, movie?.vote_average)
//        assertEquals(dataMov.poster_path, movie?.poster_path)
//
//        assertNotNull(tvShow)
//        assertEquals(dataTv.title, tvShow?.title)
//        assertEquals(dataTv.release_date, tvShow?.release_date)
//        assertEquals(dataTv.popularity, tvShow?.popularity)
//        assertEquals(dataTv.overview, tvShow?.overview)
//        assertEquals(dataTv.vote_average, tvShow?.vote_average)
//        assertEquals(dataTv.poster_path, tvShow?.poster_path)
//    }

//    @Test
//    fun getDataMov()
//    {
//        val movie = viewmodel.getMovieById()
//        assertNotNull(movie)
//        assertEquals(dataMov.title, movie?.title)
//        assertEquals(dataMov.genre, movie?.genre)
//        assertEquals(dataMov.yearrelease, movie?.yearrelease)
//        assertEquals(dataMov.poster, movie?.poster)
//        assertEquals(dataMov.score, movie?.score)
//        assertEquals(dataMov.duration, movie?.duration)
//        assertEquals(dataMov.overview, movie?.overview)
//    }
//    @Test
//    fun getDataTv()
//    {
//        val tvShow = viewmodel.getTvShowById()
//        assertNotNull(tvShow)
//        assertEquals(dataTv.title, tvShow?.title)
//        assertEquals(dataTv.genre, tvShow?.genre)
//        assertEquals(dataTv.yearrelease, tvShow?.yearrelease)
//        assertEquals(dataTv.poster, tvShow?.poster)
//        assertEquals(dataTv.score, tvShow?.score)
//        assertEquals(dataTv.duration, tvShow?.duration)
//        assertEquals(dataTv.overview, tvShow?.overview)
//    }

//    @Test
//    fun getData() {
//        val movie = viewmodel.getMovieById()
//        val tvShow = viewmodel.getTvShowById()
//        assertNotNull(tvShow)
//        assertNotNull(movie)
//        if (movie != null) {
//            assertEquals(dataMov.title, movie.title)
//            assertEquals(dataMov.genre, movie.genre)
//            assertEquals(dataMov.yearrelease, movie.yearrelease)
//            assertEquals(dataMov.poster, movie.poster)
//            assertEquals(dataMov.score, movie.score)
//            assertEquals(dataMov.duration, movie.duration)
//            assertEquals(dataMov.overview, movie.overview)
//        }
//        if (tvShow != null) {
//            assertEquals(dataTv.title, tvShow.title)
//            assertEquals(dataTv.genre, tvShow.genre)
//            assertEquals(dataTv.yearrelease, tvShow.yearrelease)
//            assertEquals(dataTv.poster, tvShow.poster)
//            assertEquals(dataTv.score, tvShow.score)
//            assertEquals(dataTv.duration, tvShow.duration)
//            assertEquals(dataTv.overview, tvShow.overview)
//        }
//    }
}
