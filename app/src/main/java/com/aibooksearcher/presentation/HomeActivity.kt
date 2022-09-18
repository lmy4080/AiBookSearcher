package com.aibooksearcher.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aibooksearcher.R
import com.aibooksearcher.databinding.ActivityHomeBinding
import com.airbnb.lottie.LottieDrawable
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        with(binding) {

            with(lavRecognition) {
                repeatCount = LottieDrawable.INFINITE

                setOnClickListener {
                    startActivityForResult(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.KOREA.toString())
                        putExtra(RecognizerIntent.EXTRA_PROMPT, "도서명을 말씀하세요.")
                    }, 100)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 || resultCode == RESULT_OK) {

            val keyword = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0).toString()
            if(keyword.isBlank()) {
                return
            } else {
                startActivity(Intent(this, BookListActivity::class.java).apply {
                    putExtra("keyword", data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0).toString())
                })
            }
        }
    }
}