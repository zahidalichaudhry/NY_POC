package com.nytimes.poc.ui.article_detail


import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nytimes.poc.R
import com.nytimes.poc.arch.base.BaseFragment
import com.nytimes.poc.databinding.FragmentArticleDetailBinding
import com.nytimes.poc.model.schema.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment : BaseFragment<FragmentArticleDetailBinding>(R.layout.fragment_article_detail) {

    private val viewModel: ArticleDetailViewModel by viewModels()
    companion object {
        const val ARTICLE = "article"
        fun makBundle(article: Article): Bundle {
            val bundle = Bundle()
            bundle.putParcelable(ARTICLE, article)
            return bundle
        }
    }
    override fun initializeComponents() {

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@ArticleDetailFragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(ARTICLE, Article::class.java)
            } else {
                it.getParcelable<Article>(ARTICLE)
            }
            viewModel.setArticleDetailState(article)
        }
    }

    override fun startObservingFragmentEvents() {
        observeDataContextViewModelEvents(viewModel)
        viewModel.obEvents.observe(this@ArticleDetailFragment) { handleViewEvents(it.getEventIfNotHandled()) }
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleUI(it)
        }
    }

    private fun handleViewEvents(event: ArticleDetailEvents?) {

    }
    private fun handleUI(it: ArticleDetailViewState) {

    }
    private fun navigateToDetailPage(model: Article) {


    }
}