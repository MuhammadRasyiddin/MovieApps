package com.example.movieapps.utils

object movieUtils {
    fun getImagePoster(path: String?): String {
        return getImage(500, path)
    }

    private fun getImage(size: Int, path: String?): String {
        return "https://image.tmdb.org/t/p/w${size}/$path"
    }
}