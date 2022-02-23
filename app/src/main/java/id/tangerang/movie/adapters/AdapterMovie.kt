package id.tangerang.movie.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.tangerang.movie.databinding.ItemMovieBinding
import id.tangerang.movie.data.source.local.entity.ModelMovie
import id.tangerang.movie.utils.Const
import id.tangerang.movie.utils.Helpers

internal class AdapterMovie(private val listMovie: MutableList<ModelMovie>, val context: Context) :
    RecyclerView.Adapter<AdapterMovie.ViewHolder>() {
    private lateinit var binding: ItemMovieBinding

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind.tvTitle.text = movie.title
        holder.bind.tvTime.text = Helpers.parseToDateMovie(movie.release_date)

        Glide.with(context)
            .load(Const.baseUrl + movie.poster_path)
            .into(holder.bind.ivPoster)

        holder.itemView.setOnClickListener {
            iClickItem.onClick(movie)
        }
    }

    class ViewHolder(itemView: View, val bind: ItemMovieBinding) : RecyclerView.ViewHolder(itemView)

    private lateinit var iClickItem : IClickItem

    fun setOnClickItem(iClickItem : IClickItem) {
        this.iClickItem = iClickItem
    }

    interface IClickItem {
        fun onClick(modelMovie: ModelMovie)
    }
}
