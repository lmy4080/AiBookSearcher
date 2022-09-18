package com.aibooksearcher.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aibooksearcher.R
import com.aibooksearcher.databinding.ActivityBookListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookListBinding
    private val viewModel: BookListViewModel by viewModels()
    private var keyword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_list)

        getIntentData()

        with(binding) {

        }

        viewModel.getBookList(keyword)
    }

    private fun getIntentData() {
        keyword = intent.getStringExtra("keyword") ?: ""
    }
}