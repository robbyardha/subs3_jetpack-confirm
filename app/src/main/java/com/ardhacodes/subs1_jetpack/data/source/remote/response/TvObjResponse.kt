package com.ardhacodes.subs1_jetpack.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvObjResponse(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var title: String? = null,

    @SerializedName("first_air_date")
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

