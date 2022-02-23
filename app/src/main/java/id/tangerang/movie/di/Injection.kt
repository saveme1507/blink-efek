package id.tangerang.movie.di

import android.content.Context

import id.tangerang.movie.data.MovieRepository
import id.tangerang.movie.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val remoteDataSource = RemoteDataSource()

        return MovieRepository.getInstance(remoteDataSource)
    }
}
