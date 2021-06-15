package com.ardhacodes.subs1_jetpack.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ardhacodes.subs1_jetpack.data.MovTvRepository
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.vo.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(private val movTvRepository: MovTvRepository) : ViewModel() {
    fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return movTvRepository.getPopularMovies()
    }

    fun getPopularTv(): LiveData<Resource<PagedList<TvEntity>>> {
        return movTvRepository.getTv()
    }
}