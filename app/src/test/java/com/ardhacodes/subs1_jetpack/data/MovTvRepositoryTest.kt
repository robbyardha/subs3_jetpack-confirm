package com.ardhacodes.subs1_jetpack.data

import android.graphics.Movie
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ardhacodes.subs1_jetpack.LiveDataTestUtility
import com.ardhacodes.subs1_jetpack.PagedListUtility
import com.ardhacodes.subs1_jetpack.data.source.datalocal.LocalDataSource
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.data.source.remote.RemoteDataSource
import com.ardhacodes.subs1_jetpack.utils.MoviesTvDataDummy
import com.ardhacodes.subs1_jetpack.utils.MoviesTvDataDummyForTest
import com.ardhacodes.subs1_jetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovTvRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val movTvRepository = FakeMovTvRepository(remoteDataSource, localDataSource)
    private val dummyMovResponse = MoviesTvDataDummyForTest.DataMovies()
    private val dummyTvResponse = MoviesTvDataDummyForTest.DataTvShow()
    private val movResponseArr = MoviesTvDataDummyForTest.DataMovies()[0]
    private val tvResponseArr = MoviesTvDataDummyForTest.DataTvShow()[0]


    @Test
    fun getPopularMovie(){

        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(localDataSource.getAllMovies()).thenReturn(dataSourceFactory)
        movTvRepository.getPopularMovies()

        val movieEntity = Resource.success(PagedListUtility.mockPagedList(MoviesTvDataDummy.DataMovies()))
        verify(localDataSource).getAllMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(dummyMovResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }


    @Test
    fun getPopularTv(){

        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, TvEntity>
        Mockito.`when`(localDataSource.getAllTvList()).thenReturn(dataSourceFactory)
        movTvRepository.getTv()

        val tvEntity = Resource.success(PagedListUtility.mockPagedList(MoviesTvDataDummy.DataTvShow()))
        verify(localDataSource).getAllTvList()
        Assert.assertNotNull(tvEntity.data)
        assertEquals(dummyTvResponse.size.toLong(), tvEntity.data?.size?.toLong())

    }


    @Test
    fun getDetailMovieArr(){

        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movResponseArr
        Mockito.`when`(localDataSource.getDetailMovie(movResponseArr.idmovie)).thenReturn(dummyMovie)


        val data = LiveDataTestUtility.getValue(movTvRepository.getMovieDetail(movResponseArr.idmovie))
        verify(localDataSource).getDetailMovie(movResponseArr.idmovie)
        Assert.assertNotNull(data)
        assertEquals(movResponseArr.id, data.id)

    }

    @Test
    fun getDetailTvArr()
    {
        val dummyTvShow = MutableLiveData<TvEntity>()
        dummyTvShow.value = tvResponseArr
        Mockito.`when`(localDataSource.getDetailTv(tvResponseArr.idtv)).thenReturn(dummyTvShow)

        val data = LiveDataTestUtility.getValue(movTvRepository.getTvDetail(tvResponseArr.idtv))
        verify(localDataSource).getDetailTv(tvResponseArr.idtv)
        Assert.assertNotNull(data)
        assertEquals(tvResponseArr.id, data.id)
    }

    @Test
    fun getFavoriteMovie(){

        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(localDataSource.getListFavMovies()).thenReturn(dataSourceFactory)
        movTvRepository.getFavMovie()

        val movieEntity = Resource.success(PagedListUtility.mockPagedList(MoviesTvDataDummy.DataMovies()))
        verify(localDataSource).getListFavMovies()
        Assert.assertNotNull(movieEntity.data)
        assertEquals(dummyMovResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }


    @Test
    fun getFavoriteTv(){

        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, TvEntity>
        Mockito.`when`(localDataSource.getListFavTvs()).thenReturn(dataSourceFactory)
        movTvRepository.getFavTv()

        val tvEntity = Resource.success(PagedListUtility.mockPagedList(MoviesTvDataDummy.DataTvShow()))
        verify(localDataSource).getListFavTvs()
        Assert.assertNotNull(tvEntity.data)
        assertEquals(dummyTvResponse.size.toLong(), tvEntity.data?.size?.toLong())
    }
}