package com.nytimes.poc.utils.binding_adapters

import com.nytimes.poc.utils.binding_adapters.AdapterType.*
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.poc.model.schema.Article
import com.nytimes.poc.ui.mostpopular.ArticlesAdapter

object RecyclerViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("recyclerItems", "adapterType", requireAll = false)
    @Suppress("UNCHECKED_CAST")
    fun setRecyclerItems(
        recyclerView: RecyclerView,
        listItems: List<Any>?,
        adapterType: AdapterType
    ) {
        listItems?.let {
            when (adapterType) {
                ARTICLE_ADAPTER -> {
                    val adapter = recyclerView.adapter as ArticlesAdapter
                    adapter.articleModels = listItems as List<Article>
                }
            }
        }
    }


}

enum class AdapterType {
    ARTICLE_ADAPTER,
}