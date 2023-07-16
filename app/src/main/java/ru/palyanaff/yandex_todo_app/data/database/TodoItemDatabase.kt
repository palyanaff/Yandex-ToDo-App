package ru.palyanaff.yandex_todo_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.model.TodoItemDao

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoItemDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao
}