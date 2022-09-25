package com.aibooksearcher.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

                        with(layout) {
                            isVisible = it.bookList != null
                        }

                        with(tvTotalBookCount) {
                            it.bookTotalCount?.let {
                                text = "총 ${it}건"
                            }
                        }

                        with(tvSearch) {
                            it.keyword?.let {
                                text = "도서 검색 결과\n'${it}'"
                            }
                        }

                        with(rvBookList) {
                            isVisible = !it.bookList.isNullOrEmpty()
                        }

                        with(tvEmpty) {
                            isVisible = it.bookList.isNullOrEmpty()
                        }

                        with(tvShopping) {
                            isVisible = !it.bookList.isNullOrEmpty()
                        }

                        it.bookList?.let { list ->
                            bookListAdapter.submitList(list)
                        }
                    }
                }
            }
        }

        with(binding) {

            with(tvShopping) {
                setOnClickListener {
                    startActivity(Intent(this@BookListActivity, ShoppingActivity::class.java).apply {
                        putExtra("keyword", keyword)
                    })
                }
            }

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