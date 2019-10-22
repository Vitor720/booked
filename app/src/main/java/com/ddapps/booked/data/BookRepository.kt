package com.ddapps.booked.data

import com.ddapps.booked.data.network.ApiServiceInterface
import com.ddapps.booked.data.network.Resource
import com.ddapps.booked.data.network.ResponseHandler
import com.ddapps.booked.models.Book
import org.koin.dsl.module
import timber.log.Timber

val shelfModule = module {
    factory { BookRepository(get(), get()) }
    single { ShelfRepository() }
}

class BookRepository(private val bookApi: ApiServiceInterface, private val responseHandler: ResponseHandler) {

    suspend fun getBooks(): Resource<MutableList<Book>> {
        return try {
            val response = bookApi.loadBooks()
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e.localizedMessage)
        }
    }
}

class ShelfRepository {
    var booksOnShelf = mutableListOf<Book>()
    var bookmarkedBooks = mutableListOf<Book>()

    fun addBook(book: Book){
        Timber.e("Livro adicionado a biblioteca")
        booksOnShelf.add(book)
    }

    fun bookmarkBook(book: Book){
        Timber.e("Livro adicionado aos favoritos")
        bookmarkedBooks.add(book)
    }

}