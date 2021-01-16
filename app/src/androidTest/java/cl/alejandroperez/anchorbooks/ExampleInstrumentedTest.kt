package cl.alejandroperez.anchorbooks

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import cl.alejandroperez.anchorbooks.model.db.DaoBook
import cl.alejandroperez.anchorbooks.model.db.DataBaseBook
import cl.alejandroperez.anchorbooks.model.db.EntityBooks
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BooksDatabaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var DaoBook: DaoBook
    private lateinit var bookDB: DataBaseBook
    @Before
    fun setup(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        bookDB= Room.inMemoryDatabaseBuilder(context, DataBaseBook::class.java).build()
        DaoBook = bookDB.getDaoBook()
    }
    @After
    fun tearDown(){
        bookDB.close()
    }
    @Test
    fun insertBook() = runBlocking {
        //Given
        val bookList = listOf(EntityBooks(1, "Profe Maza", "Chile", "https://grandeprofe.cl/", "Chileno", ""))

        //When
        DaoBook.insertBook(bookList)

        //Then
        DaoBook.getAllBook().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("cl.alejandroperez.anchorbooks", appContext.packageName)
    }
}

