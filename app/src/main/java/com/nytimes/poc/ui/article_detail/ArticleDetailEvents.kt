package com.nytimes.poc.ui.article_detail

import com.nytimes.poc.model.schema.Article

sealed class ArticleDetailEvents {

}
data class ArticleDetailViewState(
    val article: Article? = null,
) {
    companion object {
        val IDLE = ArticleDetailViewState(null)
    }
}