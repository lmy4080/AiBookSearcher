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
import com.aibooksearcher.databinding.ActivityShoppingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding
    private val viewModel: ShoppingViewModel by viewModels()
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private var keyword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping)

        getIntentData()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    with(binding) {

                        with(layout) {
                            isVisible = it.marketItemList != null
                        }

                        with(tvTotalShoppingCount) {
                            it.shoppingTotalCount?.let {
                                text = "총 ${it}건"
                            }
                        }

                        with(rvShoppingList) {
                            isVisible = !it.marketItemList.isNullOrEmpty()
                        }

                        with(tvEmpty) {
                            isVisible = it.marketItemList.isNullOrEmpty()
                        }

                        it.marketItemList?.let { list ->
                            shoppingListAdapter.submitList(list)
                        }
                    }
                }
            }
        }

        with(binding) {

            with(rvShoppingList) {
                shoppingListAdapter = ShoppingListAdapter()
                adapter = shoppingListAdapter
            }
        }

        viewModel.getMarketItemList("도서 $keyword")
    }

    private fun getIntentData() {
        keyword = intent.getStringExtra("keyword") ?: ""
    }
}