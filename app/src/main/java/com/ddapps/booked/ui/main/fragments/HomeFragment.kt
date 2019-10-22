package com.ddapps.booked.ui.main.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddapps.booked.R
import com.ddapps.booked.adapters.HomeAdapter
import com.ddapps.booked.databinding.HomeFragmentBinding
import com.ddapps.booked.models.Book
import com.ddapps.booked.util.HOME_TITLE
import com.ddapps.booked.viewmodels.SalesViewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var goBuyButton: Button
    private val viewModel: SalesViewModel by sharedViewModel()

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = HOME_TITLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate<HomeFragmentBinding>(inflater, R.layout.home_fragment, container, false)
        goBuyButton = binding.bookWormButton

        val observer = Observer<MutableList<Book>> {
            loadRecycler(it)
        }

        viewModel.bookShelfList.observe(this, observer)

        goBuyButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bookSalesFragment)
        }
        return binding.root
    }

    private fun loadRecycler(bookList: MutableList<Book>) {
        homeRecycler.layoutManager = LinearLayoutManager(context)
        homeRecycler.adapter  = AlphaInAnimationAdapter(HomeAdapter(bookList, viewModel))
            .apply {
                setDuration(890)
            }
    }

    @SuppressLint("SetTextI18n")
    private fun showError(message: String) {
        Toast.makeText(this.context, "Carregamento falhou: $message", Toast.LENGTH_LONG).show()
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = HOME_TITLE
    }

}

