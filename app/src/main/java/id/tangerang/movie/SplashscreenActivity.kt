package id.tangerang.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import id.tangerang.movie.databinding.ActivitySplashscreenBinding
import id.tangerang.movie.ui.list_movie.ListMovieActivity

class SplashscreenActivity : AppCompatActivity() {
    private val context = this@SplashscreenActivity
    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivLogo.startAnimation(
            AnimationUtils.loadAnimation(context, R.anim.fade_in)
        )

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(context, ListMovieActivity::class.java))
            finish()
        }, 2000)
    }
}