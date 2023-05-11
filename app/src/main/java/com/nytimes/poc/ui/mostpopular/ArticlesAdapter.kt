package com.nytimes.poc.ui.mostpopular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.poc.databinding.ItemArticalBinding
import com.nytimes.poc.model.schema.Article

class ArticlesAdapter(
    private val onClick: (Article) -> Unit,
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    var articleModels: List<Article> = emptyList()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemArticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dashboardModel: Article = articleModels[position]
        holder.bind(dashboardModel)
    }

    override fun getItemCount(): Int = articleModels.size

    inner class ViewHolder(
        private val itemBinding: ItemArticalBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {

            itemBinding.apply {
                item = article
                articleItem.setOnClickListener { onClick.invoke(article) }

            }
        }
    }
}