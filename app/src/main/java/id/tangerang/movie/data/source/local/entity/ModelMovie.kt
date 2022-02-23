package id.tangerang.movie.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelMovie(
    var backdrop_path: String,
    var genre_ids: String,
    var id: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: String,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var vote_average: String,
    var vote_count: String,
) : Parcelable
