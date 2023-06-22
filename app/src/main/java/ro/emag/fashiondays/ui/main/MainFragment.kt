package ro.emag.fashiondays.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ro.emag.fashiondays.MainFragmentBinding
import ro.emag.fashiondays.R
import ro.emag.fashiondays.ui.itemlist.ProductListAdapter

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private val adapter = ProductListAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
        DataBindingUtil.inflate<MainFragmentBinding>(inflater, R.layout.fragment_main, container, false)
        .also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            productsRv.adapter = adapter

            swipeRefresh.setOnRefreshListener {
                this@MainFragment.viewModel.fetchProductsList()
                swipeRefresh.isRefreshing = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when(it) {
                        is MainFragmentUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            var message = ""
                            when(it.error) {
                                MainFragmentUiError.GenericError -> {
                                    message = getString(R.string.generic_message)
                                }
                                MainFragmentUiError.NoProducts -> {
                                    message = getString(R.string.no_products_message)
                                }
                            }
                            view.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
                        }
                        MainFragmentUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is MainFragmentUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.setProducts(it.products)
                        }
                    }
                    }
                }
                }
    }

}