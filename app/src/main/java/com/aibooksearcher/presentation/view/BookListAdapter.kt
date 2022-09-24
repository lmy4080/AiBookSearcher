package com.aibooksearcher.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aibooksearcher.base.BaseViewHolder
import com.aibooksearcher.databinding.ItemBookForListBinding
import com.aibooksearcher.presentation.model.Book

class BookListAdapter: ListAdapter<Book, BaseViewHolder>(BOOK_LIST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(ItemBookForListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.run item@ {
            with(holder.binding as ItemBookForListBinding) {
                book = this@item

                with(holder.itemView) {

                }
            }
        }
    }

    companion object {
        private val BOOK_LIST_COMPARATOR = object: DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book) =
                oldItem.isbn == newItem.isbn

            override fun areContentsTheSame(oldItem: Book, newItem: Book) =
                oldItem == newItem
        }
    }
}