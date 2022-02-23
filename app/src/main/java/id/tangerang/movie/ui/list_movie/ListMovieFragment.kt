package id.tangerang.movie.ui.list_movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import id.tangerang.movie.adapters.AdapterMovie
import id.tangerang.movie.data.source.local.entity.ModelMovie
import id.tangerang.movie.databinding.FragmentListMovieBinding
import id.tangerang.movie.ui.ViewModelFactory
import id.tangerang.movie.ui.detail_movie.DetailMovieActivity
import id.tangerang.movie.utils.GridSpacingItemDecoration

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListMovieFragment : Fragment() {
    private lateinit var binding: FragmentListMovieBinding
    private lateinit var viewModel: ListMovieViewModel
    private lateinit var adapterMovie: AdapterMovie
    private val listMovie = mutableListOf<ModelMovie>()
    private var param1: String = ""
    private var param2: String = ""

    companion object {
        const val TV = "TV"
        const val MOVIE = "MOVIE"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListMovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1).toString()
            param2 = it.getString(ARG_PARAM2).toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListMovieBinding.inflate(layoutInflater, container, false)

        adapterMovie = AdapterMovie(listMovie, requireContext())
        binding.rvMovie.adapter = adapterMovie
        binding.rvMovie.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMovie.addItemDecoration(GridSpacingItemDecoration(30))

        adapterMovie.setOnClickItem(object : AdapterMovie.IClickItem {
            override fun onClick(modelMovie: ModelMovie) {
                val intent = Intent(requireContext(), DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.INTENT_ID, modelMovie.id)
                intent.putExtra(DetailMovieActivity.INTENT_TYPE, param1)
                startActivity(intent)
            }

        })

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[ListMovieViewModel::class.java]

        binding.swipe.post {
            when (param1) {
                MOVIE -> {
                    viewModel.getListMovie().observe(requireActivity()) {
                        it.let {
                            listMovie.clear()
                            listMovie.addAll(it)
                            adapterMovie.notifyDataSetChanged()
                            if (binding.swipe.isRefreshing) binding.swipe.isRefreshing = false
                        }
                    }
                }
                TV -> {
                    viewModel.getListTv().observe(requireActivity()) {
                        it.let {
                            listMovie.clear()
                            listMovie.addAll(it)
                            adapterMovie.notifyDataSetChanged()
                            if (binding.swipe.isRefreshing) binding.swipe.isRefreshing = false
                        }
                    }
                }
            }

        }

        binding.swipe.setOnRefreshListener {
            when (param1) {
                MOVIE -> {
                    viewModel.getListMovie().observe(requireActivity()) {
                        it.let {
                            listMovie.clear()
                            listMovie.addAll(it)
                            adapterMovie.notifyDataSetChanged()
                            binding.swipe.isRefreshing = listMovie.isEmpty()
                        }
                    }
                }
                TV -> {
                    viewModel.getListTv().observe(requireActivity()) {
                        it.let {
                            listMovie.clear()
                            listMovie.addAll(it)
                            adapterMovie.notifyDataSetChanged()
                            binding.swipe.isRefreshing = listMovie.isEmpty()
                        }
                    }
                }
            }
        }

        return binding.root
    }
}