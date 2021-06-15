package com.ardhacodes.subs1_jetpack.injection.uiinject.main

import com.ardhacodes.subs1_jetpack.injection.uiinject.fav.FavoriteFragmentBuildersModule
import com.ardhacodes.subs1_jetpack.ui.favorite.FavoriteFragment
import com.ardhacodes.subs1_jetpack.ui.movie.MovieFragment
import com.ardhacodes.subs1_jetpack.ui.tv.TvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TvFragment

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteFragment() : FavoriteFragment
}