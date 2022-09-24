package com.aibooksearcher.presentation.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aibooksearcher.base.BaseViewHolder
import com.aibooksearcher.databinding.ItemBookMarkBinding
import com.aibooksearcher.presentation.model.Book

class BookMarkListAdapter: ListAdapter<Book, BaseViewHolder>(BOOK_MARK_LIST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(ItemBookMarkBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.run item@ {
            with(holder.binding as ItemBookMarkBinding) {
                book = this@item

                with(holder.itemView) {
                    setOnClickListener {
                        context.startActivity(Intent(context, BookInfoActivity::class.java).apply {
                            putExtra("bookModel", this@item)
                        })
                    }
                }
            }
        }
    }

    companion object {
        private val BOOK_MARK_LIST_COMPARATOR = object: DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book) =
                oldItem.isbn == newItem.isbn

            override fun areContentsTheSame(oldItem: Book, newItem: Book) =
                oldItem == newItem
        }
    }
}