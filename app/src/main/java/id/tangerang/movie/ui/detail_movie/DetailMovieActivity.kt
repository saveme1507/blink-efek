package id.tangerang.movie.ui.detail_movie

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer
import id.tangerang.movie.databinding.ActivityDetailMovieBinding
import id.tangerang.movie.ui.ViewModelFactory
import id.tangerang.movie.ui.list_movie.ListMovieFragment
import id.tangerang.movie.utils.Const
import id.tangerang.movie.utils.Helpers
import cc.cloudist.acplibrary.ACProgressConstant

import cc.cloudist.acplibrary.ACProgressPie
import id.tangerang.movie.R


class DetailMovieActivity : AppCompatActivity() {
    private val context = this@DetailMovieActivity
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel

    companion object {
        const val INTENT_ID = "INTENT_ID"
        const val INTENT_TYPE = "INTENT_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (intent.getStringExtra(INTENT_TYPE).toString() == ListMovieFragment.MOVIE) {
            "Detail Movie"
        } else {
            "Detail TV"
        }

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val id = intent.getStringExtra(INTENT_ID).toString()

        val dialog = ACProgressPie.Builder(this)
            .ringColor(Color.WHITE)
            .pieColor(Color.WHITE)
            .updateType(ACProgressConstant.PIE_AUTO_UPDATE)
            .speed(100f)
            .build()
        dialog.show()

        if (intent.getStringExtra(INTENT_TYPE).toString() == ListMovieFragment.MOVIE) {
            viewModel.getDetailMovie(id).observe(this) {
                dialog.dismiss()
                Glide.with(context)
                    .load(Const.baseUrl + it.posterPath)
                    .into(binding.ivPoster)

                binding.tvTitle.text = it.originalTitle
                binding.tvDate.text = Helpers.parseToDateMovie(it.releaseDate)
                binding.tvLanguage.text =
                    HtmlCompat.fromHtml("language <b>" + it.originalLanguage + "</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.tvScore.text =
                    HtmlCompat.fromHtml("score user  <b>" + it.voteAverage + "</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.tvPopular.text =
                    HtmlCompat.fromHtml("popularity  <b>" + it.popularity + "</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.tvOverview.text = it.overview
                binding.tva.text = context.getString(R.string.overview)

                binding.ivPoster.setOnClickListener { _ ->
                    StfalconImageViewer.Builder(context, mutableListOf(Const.baseUrl + it.posterPath)) { imageView: ImageView, s: String ->
                        Glide.with(context)
                            .load(s)
                            .into(imageView)
                    }.show()
                }
            }
        } else {
            viewModel.getDetailTv(id).observe(this) {
                dialog.dismiss()
                Glide.with(context)
                    .load(Const.baseUrl + it.posterPath)
                    .into(binding.ivPoster)

                binding.tvTitle.text = it.originalName
                binding.tvDate.text = Helpers.parseToDateMovie(it.firstAirDate)
                binding.tvLanguage.text =
                    HtmlCompat.fromHtml("language <b>" + it.originalLanguage + "</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.tvScore.text =
                    HtmlCompat.fromHtml("score user  <b>" + it.voteAverage + "</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.tvPopular.text =
                    HtmlCompat.fromHtml("popularity  <b>" + it.popularity + "</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.tvOverview.text = it.overview
                binding.tva.text = context.getString(R.string.overview)

                binding.ivPoster.setOnClickListener { _ ->
                    StfalconImageViewer.Builder(context, mutableListOf(Const.baseUrl + it.posterPath)) { imageView: ImageView, s: String ->
                        Glide.with(context)
                            .load(s)
                            .into(imageView)
                    }.show()
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == android.R.id.home) finish()
        return true
    }


}