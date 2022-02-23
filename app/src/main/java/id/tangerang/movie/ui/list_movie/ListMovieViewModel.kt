package id.tangerang.movie.ui.list_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.tangerang.movie.data.MovieRepository
import id.tangerang.movie.data.source.local.entity.ModelMovie

class ListMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getListMovie(): LiveData<MutableList<ModelMovie>> = movieRepository.getPopularMovie()
    fun getListTv(): LiveData<MutableList<ModelMovie>> = movieRepository.getPopularTv()
}
