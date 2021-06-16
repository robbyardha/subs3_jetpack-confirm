package com.ardhacodes.subs1_jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ardhacodes.subs1_jetpack.data.source.datalocal.LocalDataSource
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.data.source.remote.RemoteDataSource
import com.ardhacodes.subs1_jetpack.data.source.remote.response.MovieObjResponse
import com.ardhacodes.subs1_jetpack.data.source.remote.response.TvObjResponse
//import com.ardhacodes.subs1_jetpack.data.source.remote.response.MovieResponse
import com.ardhacodes.subs1_jetpack.data.source.remote.response.TvResponse
import com.ardhacodes.subs1_jetpack.data.source.remote.vo.ApiResponse
import com.ardhacodes.subs1_jetpack.utils.AppExecutors
import com.ardhacodes.subs1_jetpack.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FakeMovTvRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovTvDataSource {
    override fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieObjResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieObjResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: List<MovieObjResponse>) {
                val movPopular = ArrayList<MovieEntity>()
                for (item in data) {
                    val movie = MovieEntity(
                        null,
                        item.id,
                        item.title,
                        item.release_date,
                        item.popularity,
                        item.overview,
                        item.vote_average,
                        item.poster_path,
                        false
                    )
                    movPopular.add(movie)
                }
                localDataSource.insertMoviesFav(movPopular)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        return localDataSource.getDetailMovie(movieId)
    }

    override fun getFavMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListFavMovies(), config).build()
    }

    override fun setFavMovie(movie: MovieEntity) {
        val coroutineScp = CoroutineScope(Dispatchers.IO)
        coroutineScp.launch {
            localDataSource.setUpdateDataMoviesFav(movie)
        }
    }

    override fun getTv(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvObjResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllTvList(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvObjResponse>>> {
                return remoteDataSource.getTvList()
            }

            override fun saveCallResult(data: List<TvObjResponse>) {
                val tvArr = ArrayList<TvEntity>()
                for (item in data) {
                    val tvShow = TvEntity(
                        null,
                        item.id,
                        item.title,
                        item.release_date,
                        item.popularity,
                        item.overview,
                        item.vote_average,
                        item.poster_path,
                        false
                    )
                    tvArr.add(tvShow)
                }

                localDataSource.insertTvFav(tvArr)
            }
        }.asLiveData()
    }

    override fun getTvDetail(tvShowId: Int): LiveData<TvEntity> {
        return localDataSource.getDetailTv(tvShowId)
    }

    override fun getFavTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListFavTvs(), config).build()
    }

    override fun setFavTv(tvShow: TvEntity) {
        val coroutineScp = CoroutineScope(Dispatchers.IO)
        coroutineScp.launch {
            localDataSource.setUpdateDataTvFav(tvShow)
        }
    }

}