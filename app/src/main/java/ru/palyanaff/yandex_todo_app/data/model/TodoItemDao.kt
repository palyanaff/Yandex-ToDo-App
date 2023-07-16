package ru.palyanaff.yandex_todo_app.data.model

import androidx.room.*

@Dao
interface TodoItemDao {
    @Query ("SELECT * FROM todo_items")
    fun getAll(): List<TodoItem>

    @Query("SELECT * FROM todo_items WHERE id == :itemId")
    fun findById(itemId: String): TodoItem

    @Insert fun addItem(item: TodoItem)
    @Update fun editItem(item: TodoItem)
    @Delete fun deleteItem(item: TodoItem)
}