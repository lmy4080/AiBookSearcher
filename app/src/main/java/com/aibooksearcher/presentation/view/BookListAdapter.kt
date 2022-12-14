package com.aibooksearcher.presentation.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aibooksearcher.R
import com.aibooksearcher.base.BaseViewHolder
import com.aibooksearcher.databinding.ItemBookForListBinding
import com.aibooksearcher.extension.toDate
import com.aibooksearcher.presentation.model.Book

class BookListAdapter: ListAdapter<Book, BaseViewHolder>(BOOK_LIST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(ItemBookForListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.run item@ {
            with(holder.binding as ItemBookForListBinding) {
                this@item.apply {
                    pubdate = pubdate.toDate(holder.itemView.context.getString(R.string.dateFormat)) ?: ""
                }
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
        private val BOOK_LIST_COMPARATOR = object: DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book) =
                oldItem.isbn == newItem.isbn

            override fun areContentsTheSame(oldItem: Book, newItem: Book) =
                oldItem == newItem
        }
    }
}