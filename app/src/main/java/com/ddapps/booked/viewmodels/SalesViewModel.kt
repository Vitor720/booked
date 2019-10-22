package com.ddapps.booked.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ddapps.booked.data.BookRepository
import com.ddapps.booked.data.SharedPrefs
import com.ddapps.booked.data.ShelfRepository
import com.ddapps.booked.data.network.Resource
import com.ddapps.booked.models.Book
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import timber.log.Timber
import java.text.NumberFormat
import java.util.*

val viewModelModule = module {
    viewModel { SalesViewModel(get(), get (), get()) }
}

class SalesViewModel (bookRepo: BookRepository, var sharedPrefs: SharedPrefs, shelfRepo: ShelfRepository) : ViewModel() {

    private val shelfRepository = shelfRepo
    private val bookRepository = bookRepo

    var bookShelfList: LiveData<MutableList<Book>> = liveData {
        emit(shelfRepo.booksOnShelf)
    }

    var booksList: LiveData<Resource<MutableList<Book>>> = liveData {
        emit(Resource.loading(null))
        emit(bookRepo.getBooks())
    }

    init {
        Timber.i("ViewModel was created, ufa!")
    }

    fun buyCurrentBook(book: Book): Boolean{
        val bookPrice: Float = book.price
        val result: Float = sharedPrefs.getBalance() - bookPrice
        val userCanBuy: Boolean = result >= 0

        return if(userCanBuy) {
            sharedPrefs.storeBalance(result)
            shelfRepository.addBook(book)
            userCanBuy
        } else
            userCanBuy
        }

    fun displayBalance(): String{
        val formatedBalance = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(sharedPrefs.getBalance())
        return "Saldo: $formatedBalance"
    }

    fun displayList(): MutableList<Book>{
        return booksList.value?.data!!
    }

    private fun canUserBuy(price: Float, wallet: Float): Boolean{
        val result = wallet - price
        return result >= 0
    }

    fun bookmark(book: Book){
        Timber.e("livro favoritado")
        shelfRepository.bookmarkBook(book)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("SalesViewModel foi destruida!")
    }
}

