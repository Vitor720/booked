package com.ddapps.booked.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddapps.booked.R
import com.ddapps.booked.databinding.SalesRowBinding
import com.ddapps.booked.models.Book
import com.ddapps.booked.viewmodels.SalesViewModel


class SalesBookAdapter(var booksOnSale: MutableList<Book>, var viewModel: SalesViewModel) : RecyclerView.Adapter<SalesBookAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: SalesRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.sales_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return booksOnSale.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookOnDisplay = booksOnSale[position]

        holder.buyButton.setOnClickListener {
            val bookPurchased: Boolean =  viewModel.buyCurrentBook(bookOnDisplay)
            if (bookPurchased){
                booksOnSale.remove(bookOnDisplay)
                notifyDataSetChanged()
                Toast.makeText(it.context, "Parabens pela compra", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(it.context, "Saldo insuficiente para efetuar o pagamento.", Toast.LENGTH_LONG).show()
            }
        }

        holder.apply {
            Glide.with(itemView).load(bookOnDisplay.thumbnailHd).into(salesBookImage)
            salesBookName.text = bookOnDisplay.title
            salesBookWriter.text = bookOnDisplay.writer
            salesBookPrice.text = "Preço: R$ ${bookOnDisplay.price}"
        }
    }

    inner class ViewHolder internal constructor(val binding: SalesRowBinding) : RecyclerView.ViewHolder(binding.root){
        val salesBookImage = binding.SalesBookImage
        val salesBookWriter = binding.salesBookWriter
        val salesBookName = binding.salesBookName
        val salesBookPrice = binding.salesBookPrice
        val buyButton = binding.buyButton
    }
}