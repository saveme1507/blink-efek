package id.tangerang.movie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.tangerang.movie.data.source.local.entity.ModelMovie
import id.tangerang.movie.data.source.remote.RemoteDataSource
import id.tangerang.movie.data.source.remote.response.DetailMovieResponse
import id.tangerang.movie.data.source.remote.response.DetailTvResponse
import id.tangerang.movie.data.source.remote.response.PopularMovieResponse
import id.tangerang.movie.data.source.remote.response.PopularTvResponse

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieDataSource {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository(remoteData).apply { instance = this }
            }
    }


    override fun getPopularMovie(): LiveData<MutableList<ModelMovie>> {
        val data = MutableLiveData<MutableList<ModelMovie>>()

        remoteDataSource.getPopularMovie(object : RemoteDataSource.GetPopularMovieCallback {
            override fun onResponse(movieResponse: PopularMovieResponse) {
                val response = mutableListOf<ModelMovie>()
                movieResponse.results.forEach {
                    response.add(
                        ModelMovie(
                            backdrop_path = it.backdropPath,
                            genre_ids = it.genreIds.toString(),
                            id = it.id.toString(),
                            original_language = it.originalLanguage,
                            original_title = it.originalTitle,
                            overview = it.overview,
                            popularity = it.popularity.toString(),
                            poster_path = it.posterPath,
                            release_date = it.releaseDate,
                            title = it.title,
                            vote_average = it.voteAverage.toString(),
                            vote_count = it.voteCount.toString()
                        )
                    )
                }
                data.postValue(response)
            }
        })

        return data
    }

    override fun getPopularTv(): LiveData<MutableList<ModelMovie>> {
        val data = MutableLiveData<MutableList<ModelMovie>>()

        remoteDataSource.getPopularTv(object : RemoteDataSource.GetPopularTvCallback {
            override fun onResponse(tvResponse: PopularTvResponse) {
                val response = mutableListOf<ModelMovie>()
                tvResponse.results.forEach {
                    response.add(
                        ModelMovie(
                            backdrop_path = it.backdropPath ?: "",
                            genre_ids = if (it.genreIds.isNotEmpty()) it.genreIds[0].toString() else "",
                            id = it.id.toString(),
                            original_language = it.originalLanguage,
                            original_title = it.originalName,
                            overview = it.overview,
                            popularity = it.popularity.toString(),
                            poster_path = it.posterPath,
                            release_date = it.firstAirDate,
                            title = it.originalName,
                            vote_average = it.voteAverage.toString(),
                            vote_count = it.voteCount.toString()
                        )
                    )
                }
                data.postValue(response)
            }
        })

        return data
    }

    override fun getDetailMovie(id: String): LiveData<DetailMovieResponse> {
        val data = MutableLiveData<DetailMovieResponse>()
        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.GetDetailMovie {
            override fun onResponse(detailMovieResponse: DetailMovieResponse) {
                data.postValue(detailMovieResponse)
            }
        })
        return data
    }

    override fun getDetailTv(id: String): LiveData<DetailTvResponse> {
        val data = MutableLiveData<DetailTvResponse>()
        remoteDataSource.getDetailTv(id, object : RemoteDataSource.GetDetailTv {
            override fun onResponse(detailTvResponse: DetailTvResponse) {
                data.postValue(detailTvResponse)
            }

        })
        return data
    }

}