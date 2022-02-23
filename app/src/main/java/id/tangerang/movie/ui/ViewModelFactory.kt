package id.tangerang.movie.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.tangerang.movie.data.MovieRepository

import id.tangerang.movie.di.Injection
import id.tangerang.movie.ui.detail_movie.DetailMovieViewModel
import id.tangerang.movie.ui.list_movie.ListMovieViewModel

class ViewModelFactory private constructor(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListMovieViewModel::class.java) -> {
                ListMovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(movieRepository) as T
            }
//            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
//                BookmarkViewModel(mAcademyRepository) as T
//            }
//            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
//                CourseReaderViewModel(mAcademyRepository) as T
//            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
