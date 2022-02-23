package id.tangerang.movie.utils

import android.annotation.SuppressLint
import android.content.Context
import java.io.IOException
import java.text.Format
import java.text.ParseException
import java.text.SimpleDateFormat

class Helpers {
    companion object {
        fun getJsonDataFromAsset(context: Context, fileName: String): String {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return ""
            }
            return jsonString
        }

        @SuppressLint("SimpleDateFormat")
        fun parseToDateMovie(dateSql: String): String {
            val formatInput = SimpleDateFormat("yyyy-MM-dd")
            var dateResult = ""
            try {
                val dateInput = formatInput.parse(dateSql)
                val f: Format = SimpleDateFormat("MMM dd, yyyy")
                dateResult = f.format(dateInput)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return dateResult
        }
    }
}