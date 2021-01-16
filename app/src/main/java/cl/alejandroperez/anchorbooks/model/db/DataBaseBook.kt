package cl.alejandroperez.anchorbooks.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityBooks::class, EntityBookDetail::class], version = 1)
abstract class DataBaseBook : RoomDatabase() {

    abstract fun getDaoBook(): DaoBook

    companion object {

        @Volatile
        private var INSTANCE: DataBaseBook? = null

        fun getDatabase(context: Context): DataBaseBook {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseBook::class.java,
                    "book_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}