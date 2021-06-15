package com.ardhacodes.subs1_jetpack.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ardhacodes.subs1_jetpack.data.source.remote.api.ApiConfig
import com.ardhacodes.subs1_jetpack.data.source.remote.api.ApiService
import com.ardhacodes.subs1_jetpack.data.source.remote.response.MovieObjResponse
import com.ardhacodes.subs1_jetpack.data.source.remote.response.MoviesResponse
import com.ardhacodes.subs1_jetpack.data.source.remote.response.TvObjResponse
import com.ardhacodes.subs1_jetpack.data.source.remote.response.TvResponse
import com.ardhacodes.subs1_jetpack.data.source.remote.vo.ApiResponse
import com.ardhacodes.subs1_jetpack.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.security.Key
import javax.inject.Inject

//class RemoteDataSource {
class RemoteDataSource@Inject constructor(private val apiService: ApiService)  {
    private val KEY = "57d7a07963575b131d0f873f638ec911"
//    companion object {
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(): RemoteDataSource = instance ?: synchronized(this)
//        {
//            instance ?: RemoteDataSource()
//        }
//    }
    public fun getPopularMovies(): LiveData<ApiResponse<List<MovieObjResponse>>> {
        EspressoIdlingResource.CountIncrement()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieObjResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getPopularMovie().await()
                resultMovieResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.CountDecrement()
        return resultMovieResponse
    }

    fun getTvList(): LiveData<ApiResponse<List<TvObjResponse>>> {
        EspressoIdlingResource.CountIncrement()
        val resultTvShowResponse = MutableLiveData<ApiResponse<List<TvObjResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getTvPopular().await()
                resultTvShowResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTvShowResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.CountDecrement()
        return resultTvShowResponse
    }
}