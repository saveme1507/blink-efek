package id.tangerang.movie.ui.detail_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.tangerang.movie.data.MovieRepository
import id.tangerang.movie.data.source.remote.response.DetailMovieResponse
import id.tangerang.movie.data.source.remote.response.DetailTvResponse

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getDetailMovie(id: String): LiveData<DetailMovieResponse> = movieRepository.getDetailMovie(id)
    fun getDetailTv(id: String): LiveData<DetailTvResponse> = movieRepository.getDetailTv(id)
}
