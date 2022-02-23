package id.tangerang.movie.data.source.remote

import android.util.Log
import id.tangerang.movie.data.source.remote.response.DetailMovieResponse
import id.tangerang.movie.data.source.remote.response.DetailTvResponse
import id.tangerang.movie.data.source.remote.response.PopularMovieResponse
import id.tangerang.movie.data.source.remote.response.PopularTvResponse
import id.tangerang.movie.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
        private const val PAGE = "1"
        private const val API_KEY = "64f48025becb425272cced8aabc6834f"
    }

    interface GetPopularMovieCallback {
        fun onResponse(movieResponse: PopularMovieResponse)
    }

    interface GetPopularTvCallback {
        fun onResponse(tvResponse: PopularTvResponse)
    }

    interface GetDetailMovie {
        fun onResponse(detailMovieResponse: DetailMovieResponse)
    }

    interface GetDetailTv {
        fun onResponse(detailTvResponse: DetailTvResponse)
    }

    fun getPopularMovie(getPopularMovieCallback: GetPopularMovieCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getPopularMovie(API_KEY, PAGE)
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(call: Call<PopularMovieResponse>, response: Response<PopularMovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        getPopularMovieCallback.onResponse(it)
                        EspressoIdlingResource.decrement()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.printStackTrace()}")
            }
        })
    }

    fun getPopularTv(getPopularTvCallback: GetPopularTvCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getPopularTv(API_KEY, PAGE)
        client.enqueue(object : Callback<PopularTvResponse> {
            override fun onResponse(call: Call<PopularTvResponse>, response: Response<PopularTvResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        getPopularTvCallback.onResponse(it)
                        EspressoIdlingResource.decrement()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularTvResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.printStackTrace()}")
            }
        })
    }

    fun getDetailMovie(id: String, getDetailMovie: GetDetailMovie) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovie(id, API_KEY)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        getDetailMovie.onResponse(it)
                        EspressoIdlingResource.decrement()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.printStackTrace()}")
            }
        })
    }

    fun getDetailTv(id:String, getDetailTv: GetDetailTv) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTv(id, API_KEY)
        client.enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(call: Call<DetailTvResponse>, response: Response<DetailTvResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        getDetailTv.onResponse(it)
                        EspressoIdlingResource.decrement()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.printStackTrace()}")
            }
        })
    }

}