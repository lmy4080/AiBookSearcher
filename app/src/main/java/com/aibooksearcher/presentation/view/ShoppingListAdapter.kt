package com.aibooksearcher.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aibooksearcher.base.BaseViewHolder
import com.aibooksearcher.databinding.ItemMarketItemBinding
import com.aibooksearcher.presentation.model.MarketItem
import java.text.NumberFormat
import java.util.*


class ShoppingListAdapter: ListAdapter<MarketItem, BaseViewHolder>(MARKET_ITEM_LIST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(ItemMarketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.run item@ {
            with(holder.binding as ItemMarketItemBinding) {

                marketItem = this@item

                with(holder.itemView) {
                    setOnClickListener {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
                    }
                }

                with(tvName) {
                    text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY)
                    else
                        Html.fromHtml(title)
                }

                with(tvPrice) {
                    println("ShoppingListAdapter: price: $lprice: ${NumberFormat.getInstance(Locale.getDefault()).format(lprice.toInt()) + "원"}")
                    text = try {
                        NumberFormat.getInstance(Locale.getDefault()).format(lprice.toInt()) + "원"
                    } catch (e: Exception) {
                        lprice + "원"
                    }
                }
            }
        }
    }

    companion object {
        private val MARKET_ITEM_LIST_COMPARATOR = object: DiffUtil.ItemCallback<MarketItem>() {
            override fun areItemsTheSame(oldItem: MarketItem, newItem: MarketItem) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: MarketItem, newItem: MarketItem) =
                oldItem == newItem
        }
    }
}