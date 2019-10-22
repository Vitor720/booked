package com.ddapps.booked.ui.main.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddapps.booked.R
import com.ddapps.booked.adapters.SalesBookAdapter
import com.ddapps.booked.data.network.Resource
import com.ddapps.booked.data.network.Status
import com.ddapps.booked.databinding.FragmentBookSalesBinding
import com.ddapps.booked.models.Book
import com.ddapps.booked.util.SALES_TITLE
import com.ddapps.booked.viewmodels.SalesViewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.dsl.module

val salesFragmentModule = module {
    factory {  BookSalesFragment() }
}

class BookSalesFragment : Fragment() {

    private val salesViewModel: SalesViewModel by sharedViewModel()
    private lateinit var binding: FragmentBookSalesBinding
    private lateinit var salesRecycler: RecyclerView

    private var observer = Observer<Resource<MutableList<Book>>> {
        when (it.status) {
            Status.SUCCESS -> loadSalesRecycler(it.data!!)
            Status.ERROR -> showError(it.message!!)
            Status.LOADING -> Toast.makeText(this.context, "CARREGANDO", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?. title = SALES_TITLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_sales, container, false)
        setHasOptionsMenu(true)
        salesViewModel.booksList.observe(this, observer)
        return binding.root
    }

    private fun loadSalesRecycler(bookList: MutableList<Book>) {
        salesRecycler = binding.salesRecycler
        salesRecycler.layoutManager = GridLayoutManager(context, 3)
        salesRecycler.adapter  = AlphaInAnimationAdapter(SalesBookAdapter(bookList, salesViewModel))
            .apply {
                setDuration(800)
            }
    }

    @SuppressLint("SetTextI18n")
    private fun showError(message: String) {
        Toast.makeText(this.context, "Carregamento falhou: $message", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
        R.id.action_update_books -> loadSalesRecycler(salesViewModel.displayList())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?. title = SALES_TITLE
    }
}
