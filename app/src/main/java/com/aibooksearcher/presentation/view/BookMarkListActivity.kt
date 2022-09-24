package com.aibooksearcher.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aibooksearcher.R
import com.aibooksearcher.databinding.ActivityBookMarkListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookMarkListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookMarkListBinding
    private val viewModel: BookMarkListViewModel by viewModels()
    private lateinit var bookMarkListAdapter: BookMarkListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_mark_list)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    with(binding) {

                        with(tvTotalBookMarkCount) {
                            text = "총 ${it.bookList.size}건"
                        }

                        with(rvBookMarkList) {
                            isVisible = it.bookList.isNotEmpty()
                        }

                        with(tvEmpty) {
                            isVisible = it.bookList.isEmpty()
                        }
                    }

                    bookMarkListAdapter.submitList(it.bookList)
                }
            }
        }

        with(binding) {

            with(rvBookMarkList) {
                bookMarkListAdapter = BookMarkListAdapter()
                adapter = bookMarkListAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBookListFromCache()
    }
}