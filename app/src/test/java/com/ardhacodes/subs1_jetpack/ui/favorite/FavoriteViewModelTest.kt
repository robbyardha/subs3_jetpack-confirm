package com.ardhacodes.subs1_jetpack.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ardhacodes.subs1_jetpack.data.MovTvRepository
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.ui.detail.DetailViewModel
import com.ardhacodes.subs1_jetpack.ui.main.MainViewModel
import com.ardhacodes.subs1_jetpack.ui.movie.MovieViewModel
import com.ardhacodes.subs1_jetpack.ui.tv.TvViewModel
import com.ardhacodes.subs1_jetpack.utils.MoviesTvDataDummy
import com.ardhacodes.subs1_jetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class FavoriteViewModelTest {
    private lateinit var viewmodeldetail: DetailViewModel
    private lateinit var viewmodelmov: MovieViewModel
    private lateinit var viewmodeltv: TvViewModel
    private lateinit var viewmodelfav: FavoriteViewModel
    //    val dataMov = MoviesTvDataDummy.DataMovies()[0]
//    val dataTv = MoviesTvDataDummy.DataTvShow()[0]
    val dataMov = MoviesTvDataDummy.DataMovies()
    val dataTv = MoviesTvDataDummy.DataTvShow()

    @get:Rule
    var instantTask = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepos: MovTvRepository

    @Mock
    private lateinit var observer: Observer<List<MovieTvEntity>>

    @Mock
    private lateinit var obsMov: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var obsTv: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvPagedList: PagedList<TvEntity>

    @Before
    fun setData()
    {
        viewmodelmov = MovieViewModel(catalogRepos)
        viewmodelmov.getdDataMovie()

        viewmodeltv = TvViewModel(catalogRepos)
        viewmodeltv.getdDataTv()

        viewmodeldetail = DetailViewModel(catalogRepos)


        viewmodelfav = FavoriteViewModel(catalogRepos)
//        viewmodeldetail.setMovieById(movieId)
//        viewmodeldetail.setTvShowById(tvShowId)
    }

    @Test
    fun getFavMovie()
    {
        val mov = moviePagedList
        Mockito.`when`(mov.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = mov

        Mockito.`when`(catalogRepos.getFavMovie()).thenReturn(movie)
        val movieEntity = viewmodelfav.getFavMovieList().value
        verify(catalogRepos).getFavMovie()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(5, movieEntity?.size)

        viewmodelfav.getFavMovieList().observeForever(obsMov)
        Mockito.verify(obsMov).onChanged(mov)
    }

    @Test
    fun getFavTv()
    {
        val tvPgls = tvPagedList
        Mockito.`when`(tvPgls.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<TvEntity>>()
        tv.value = tvPgls

        Mockito.`when`(catalogRepos.getFavTv()).thenReturn(tv)
        val tvEntity = viewmodelfav.getFavTvList().value
        verify(catalogRepos).getFavTv()
        Assert.assertNotNull(tvEntity)
        Assert.assertEquals(5, tvEntity?.size)

        viewmodelfav.getFavTvList().observeForever(obsTv)
        Mockito.verify(obsTv).onChanged(tvPgls)
    }

}