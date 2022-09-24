package com.aibooksearcher.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aibooksearcher.R
import com.aibooksearcher.databinding.ActivityBookInfoBinding

class BookInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_info)
    }
}