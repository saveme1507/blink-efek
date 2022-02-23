package id.tangerang.movie.utils

import android.content.Context
import android.widget.Toast
import id.tangerang.movie.R
import id.tangerang.movie.data.source.local.entity.ModelMovie
import org.json.JSONArray
import org.json.JSONException

class DataDummy() {
    companion object{
        fun getDummyMovie(context: Context) : MutableList<ModelMovie>{
            val list: MutableList<ModelMovie> = mutableListOf()
            val dataJson = Helpers.getJsonDataFromAsset(context, "movie.json")
            try {
                val jsonArray = JSONArray(dataJson)
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    list.add(
                        ModelMovie(
                            item.getString("backdrop_path"),
                            item.getString("genre_ids"),
                            item.getString("id"),
                            item.getString("original_language"),
                            item.getString("original_title"),
                            item.getString("overview"),
                            item.getString("popularity"),
                            item.getString("poster_path"),
                            item.getString("release_date"),
                            item.getString("title"),
                            item.getString("vote_average"),
                            item.getString("vote_count"),
                        )
                    )
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(context, context.getString(R.string.error_parsing_json), Toast.LENGTH_SHORT).show()
            }
            return list
        }

        fun getDummyTv(context: Context) : MutableList<ModelMovie>{
            val list: MutableList<ModelMovie> = mutableListOf()
            val dataJson = Helpers.getJsonDataFromAsset(context, "tv.json")
            try {
                val jsonArray = JSONArray(dataJson)
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    list.add(
                        ModelMovie(
                            item.getString("backdrop_path"),
                            item.getString("genre_ids"),
                            item.getString("id"),
                            item.getString("original_language"),
                            item.getString("original_title"),
                            item.getString("overview"),
                            item.getString("popularity"),
                            item.getString("poster_path"),
                            item.getString("release_date"),
                            item.getString("title"),
                            item.getString("vote_average"),
                            item.getString("vote_count"),
                        )
                    )
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(context, context.getString(R.string.error_parsing_json), Toast.LENGTH_SHORT).show()
            }
            return list
        }
    }
}