package com.ddapps.booked.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddapps.booked.R
import com.ddapps.booked.databinding.BookRowBinding
import com.ddapps.booked.models.Book
import com.ddapps.booked.viewmodels.SalesViewModel

class HomeAdapter(val bookShelf: MutableList<Book>, val viewModel: SalesViewModel) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view: BookRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.book_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookShelf.size
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val currentBook = bookShelf[position]

        holder.bookmark.setOnClickListener {
            viewModel.bookmark(currentBook)
            bookShelf.remove(currentBook)
            bookShelf.add(0, currentBook)
            notifyDataSetChanged()
        }

        holder.apply {
            bookTitle.text = currentBook.title
            bookWriter.text = currentBook.writer
            Glide.with(itemView).load(currentBook.thumbnailHd).into(bookImage)
        }
    }

    inner class ViewHolder internal constructor(val binding: BookRowBinding) : RecyclerView.ViewHolder(binding.root){
        val bookTitle = binding.bookName
        val bookWriter =  binding.bookWriter
        val bookImage = binding.bookImage
        val bookmark = binding.bookmark
    }

}