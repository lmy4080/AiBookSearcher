package com.aibooksearcher.presentation.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aibooksearcher.R
import com.aibooksearcher.databinding.ActivityBookListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookListBinding
    private val viewModel: BookListViewModel by viewModels()
    private lateinit var bookListAdapter: BookListAdapter
    private var keyword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_list)

        getIntentData()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    with(binding) {

                        with(tvTotalBookCount) {
                            text = "총 ${it.bookTotalCount}건"
                        }

                        with(tvSearch) {
                            text = "도서 검색 결과\n'${it.keyword}'"
                        }

                        with(rvBookList) {
                            bookListAdapter.submitList(it.bookList)
                        }
                    }
                }
            }
        }

        with(binding) {

            with(rvBookList) {
                bookListAdapter = BookListAdapter()
                adapter = bookListAdapter
            }
        }

        viewModel.getBookList(keyword)
    }

    private fun getIntentData() {
        keyword = intent.getStringExtra("keyword") ?: ""
    }
}