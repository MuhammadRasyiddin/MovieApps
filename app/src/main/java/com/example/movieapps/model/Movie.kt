package com.example.movieapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("popularity")
    var popularity: String? = null,
    @SerializedName("vote_count")
    var vote_count: String? = null,
    @SerializedName("video")
    var video: Boolean? = null,
    @SerializedName("poster_path")
    var poster_path: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("adult")
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("original_language")
    var original_language: String? = null,
    @SerializedName("original_title")
    var original_title: String? = null,
    @SerializedName("genre_ids")
    var genre_ids: List<Int>? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var vote_average: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("release_date")
    var release_date: String? = null
) : Parcelable