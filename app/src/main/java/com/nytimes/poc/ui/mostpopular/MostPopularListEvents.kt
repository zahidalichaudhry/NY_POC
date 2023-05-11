package com.nytimes.poc.ui.mostpopular

import com.nytimes.poc.model.schema.Article

sealed class MostPopularListEvents {

}
data class MostPopularListState(
    val articlesListState: List<Article> = emptyList(),
) {
    companion object {
        val IDLE = MostPopularListState(emptyList())
    }
}