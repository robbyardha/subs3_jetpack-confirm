package com.ardhacodes.subs1_jetpack.data.source.datalocal.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_movies")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "idmovie")
    var idmovie : Int = 0,

    @NonNull
    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "release_date")
    var release_date: String? = null,

    @ColumnInfo(name = "popularity")
    var popularity: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "vote_average")
    var vote_average: String? = null,

    @ColumnInfo(name = "poster_path")
    var poster_path: String? = null,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    var is_favorite: Boolean = false,
):Parcelable
