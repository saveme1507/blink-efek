package id.tangerang.movie.data.source.remote

import id.tangerang.movie.data.source.remote.response.DetailMovieResponse
import id.tangerang.movie.data.source.remote.response.DetailTvResponse
import id.tangerang.movie.data.source.remote.response.PopularMovieResponse
import id.tangerang.movie.data.source.remote.response.PopularTvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("tv/popular")
    fun getPopularTv(
        @Query("api_key")
        api_key: String,
        @Query("page")
        page: String
    ): Call<PopularTvResponse>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key")
        api_key: String,
        @Query("page")
        page: String
    ): Call<PopularMovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id")
        id: String,
        @Query("api_key")
        api_key: String,
    ): Call<DetailMovieResponse>

    @GET("tv/{id}")
    fun getDetailTv(
        @Path("id")
        id: String,
        @Query("api_key")
        api_key: String,
    ): Call<DetailTvResponse>
}