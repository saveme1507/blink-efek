package id.tangerang.movie.data

import androidx.lifecycle.LiveData
import id.tangerang.movie.data.source.local.entity.ModelMovie
import id.tangerang.movie.data.source.remote.response.DetailMovieResponse
import id.tangerang.movie.data.source.remote.response.DetailTvResponse

interface MovieDataSource {
    fun getPopularMovie(): LiveData<MutableList<ModelMovie>>
    fun getPopularTv(): LiveData<MutableList<ModelMovie>>
    fun getDetailMovie(id: String): LiveData<DetailMovieResponse>
    fun getDetailTv(id: String): LiveData<DetailTvResponse>
}