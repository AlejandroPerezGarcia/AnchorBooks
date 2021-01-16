package cl.alejandroperez.anchorbooks.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import cl.alejandroperez.anchorbooks.model.api.BookDetail
import cl.alejandroperez.anchorbooks.model.api.Books
import cl.alejandroperez.anchorbooks.model.api.RetrofitBook
import cl.alejandroperez.anchorbooks.model.db.DataBaseBook
import cl.alejandroperez.anchorbooks.model.db.EntityBookDetail
import cl.alejandroperez.anchorbooks.model.db.EntityBooks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(context: Context) {

    private var dataBaseBook = DataBaseBook.getDatabase(context)
    var listBook = dataBaseBook.getDaoBook().getAllBook()

    fun loadApiBook() {

        val call = RetrofitBook.retrofitInstance().getAllBook()

        call.enqueue(object : Callback<List<Books>> {
            override fun onResponse(call: Call<List<Books>>, response: Response<List<Books>>) {
                Log.d("Adapter", "{${response.body()}}")
                saveDatabase(productConvert(response.body()!!))
            }

            override fun onFailure(call: Call<List<Books>>, t: Throwable) {
                Log.d("Adapter", "Error al cargar")
            }
        })
    }


    fun saveDatabase(listBookEntity: List<EntityBooks>) {
        CoroutineScope(Dispatchers.IO).launch {
            dataBaseBook.getDaoBook().insertBook(listBookEntity)
        }
        Log.d("sabe", "$listBookEntity")
    }

    fun saveDataBaseDetail(listDetailEntity: EntityBookDetail) {
        CoroutineScope(Dispatchers.IO).launch {
            dataBaseBook.getDaoBook().insertDetail(listDetailEntity)
        }
    }

    fun loadDetail(id: Int) {
        val call = RetrofitBook.retrofitInstance().getAllDetail(id)

        call.enqueue(object : Callback<BookDetail> {
            override fun onResponse(call: Call<BookDetail>, response: Response<BookDetail>) {
                Log.d("TAGREPO1", "${response.body()}")
                saveDataBaseDetail(detailConvert(response.body()!!))
                Log.d("TAGREPO2", "${response.body()}")
            }

            override fun onFailure(call: Call<BookDetail>, t: Throwable) {
                Log.d("TAGREPO#", "$t NO LLEGA NADA")
            }
        })
    }

    fun getDetail(id: Int): LiveData<BookDetail> {
        return dataBaseBook.getDaoBook().getDetail(id)
    }
}