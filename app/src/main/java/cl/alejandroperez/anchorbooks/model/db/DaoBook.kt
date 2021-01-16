package cl.alejandroperez.anchorbooks.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.alejandroperez.anchorbooks.model.api.Books
import cl.alejandroperez.anchorbooks.model.api.BookDetail

@Dao
interface DaoBook {

    @Query("SELECT * from book_table")
    fun getAllBook(): LiveData<List<Books>>

    @Query("SELECT *  from detail_table where id = :id ")
    fun getDetail(id: Int): LiveData<BookDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(booklist: List<EntityBooks>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detallist: EntityBookDetail)


}