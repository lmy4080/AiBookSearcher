package com.aibooksearcher.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aibooksearcher.R
import com.aibooksearcher.databinding.ActivityBookInfoBinding
import com.aibooksearcher.presentation.model.Book
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookInfoBinding
    private val viewModel: BookInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_info)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    with(binding) {
                        book = it.book
                    }
                }
            }
        }

        with(binding) {

            with(ivBookMark) {
                setOnClickListener {
                    viewModel.bookMark()
                }
            }
        }

        viewModel.getBook(intent.getSerializableExtra("bookModel") as Book)
    }
}