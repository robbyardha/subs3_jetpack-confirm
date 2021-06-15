package com.ardhacodes.subs1_jetpack.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieObjResponse(
    @SerializedName("id")
    var id: Int = 0 ,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("release_date")
    var release_date: String? = null,

    @SerializedName("popularity")
    var popularity: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("vote_average")
    var vote_average: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,
)
