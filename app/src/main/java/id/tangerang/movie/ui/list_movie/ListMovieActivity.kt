package id.tangerang.movie.ui.list_movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import id.tangerang.movie.R
import id.tangerang.movie.adapters.AdapterViewPager
import id.tangerang.movie.databinding.ActivityListMovieBinding

class ListMovieActivity : AppCompatActivity() {
    private val context = this@ListMovieActivity
    private lateinit var binding: ActivityListMovieBinding
    private var backPress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = AdapterViewPager(context)
        adapter.addFragment(ListMovieFragment.newInstance(ListMovieFragment.MOVIE, "Movie"), getString(R.string.movie))
        adapter.addFragment(ListMovieFragment.newInstance(ListMovieFragment.TV, "TV"), getString(R.string.tv_show))

        binding.pager.adapter = adapter
        binding.pager.currentItem = 0
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }

    override fun onBackPressed() {
        if (backPress) {
            super.onBackPressed()
            return
        }
        this.backPress = true
        Toast.makeText(this, getString(R.string.msg_double_click_close), Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ backPress = false }, 2000)
    }
}