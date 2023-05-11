package com.nytimes.poc.ui.mostpopular


import android.content.Intent
import androidx.fragment.app.viewModels
import com.nytimes.poc.R
import com.nytimes.poc.arch.base.BaseFragment
import com.nytimes.poc.databinding.FragmentMostPopularListBinding
import com.nytimes.poc.model.schema.Article
import com.nytimes.poc.ui.article_detail.ArticleDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPopularListFragment : BaseFragment<FragmentMostPopularListBinding>(R.layout.fragment_most_popular_list) {

    private val viewModel: MostPopularListViewModel by viewModels()
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun initializeComponents() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@MostPopularListFragment
        articlesAdapter = ArticlesAdapter(onClick = { navigateToDetailPage(it) } )
        binding.articleRv.adapter = articlesAdapter


    }

    override fun startObservingFragmentEvents() {
        observeDataContextViewModelEvents(viewModel)
        viewModel.obEvents.observe(this@MostPopularListFragment) { handleViewEvents(it.getEventIfNotHandled()) }
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleUI(it)
        }
    }

    private fun handleViewEvents(event: MostPopularListEvents?) {

    }
    private fun handleUI(it: MostPopularListState) {

    }
    private fun navigateToDetailPage(model: Article) {

        navigateTo(R.id.articleDetailFragment,ArticleDetailFragment.makBundle(model))
    }
}