package cl.alejandroperez.anchorbooks.viewmodel

import android.app.Application
import android.util.Log

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.alejandroperez.anchorbooks.model.Repository
import cl.alejandroperez.anchorbooks.model.api.BookDetail
import cl.alejandroperez.anchorbooks.model.api.Books

class BookViewModel (application: Application) : AndroidViewModel(application){

    private var repository: Repository = Repository(application)

    var listBook = repository.listBook
    lateinit var result: LiveData<BookDetail>

    init {
        repository = Repository(application)
        repository.loadApiBook()
        Log.d("load", "${repository.loadApiBook()}")
    }

    private val deselect = MutableLiveData<Books>()

    fun selecionado(books: Books){

        deselect.value = books
        repository.loadDetail(books.id)
        result = repository.getDetail(books.id)

    }





}