package ru.palyanaff.yandex_todo_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.model.TodoItemDao

@Module
interface DatabaseModule {
    @Binds
    fun todoItemDao(impl: TodoItemDatabase_Impl): TodoItemDao
}

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoItemDatabase : RoomDatabase() {

    abstract fun todoItemDao(): TodoItemDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TodoItemDatabase? = null

        fun getDatabase(context: Context): TodoItemDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoItemDatabase::class.java,
                    "todo_item.db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}