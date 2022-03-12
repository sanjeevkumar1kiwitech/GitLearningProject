
package com.example.movieapp.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.movieapp.database.entaties.Movie
import com.example.mvvm_skeleton_demo.database.convertor.TypeConvertor
import com.example.mvvm_skeleton_demo.database.dao.MovieDAO

@Database(entities = [Movie::class], version = 1)
@TypeConverters(TypeConvertor::class)
abstract class AppDataBase : RoomDatabase() {



    companion object{
        const val DB_NAME :String = "movie_db"

        val migrate_1_2 = object: Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }
        }

        @Volatile
        private var INSTANCE : AppDataBase? = null

        fun getDatabase(context: Context) : AppDataBase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE =  Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java,
                        DB_NAME)
                        .addMigrations(migrate_1_2)
                        .build()
                }
            }
            return INSTANCE !!;
        }
    }


    abstract fun movieDao() :MovieDAO

    // if we have multiple DAO class here define like contactDao
}