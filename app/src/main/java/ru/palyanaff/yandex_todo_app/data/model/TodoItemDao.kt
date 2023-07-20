package ru.palyanaff.yandex_todo_app.data.model

import androidx.room.*

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM todo_items")
    suspend fun getAll(): List<TodoItem>

    @Query("SELECT * FROM todo_items WHERE id = :itemId")
    suspend fun findById(itemId: Int): TodoItem

    @Query("DELETE FROM todo_items WHERE id = :itemId")
    suspend fun deleteById(itemId: Int)

    @Insert
    suspend fun addItem(item: TodoItem)
    @Update
    suspend fun editItem(item: TodoItem)
    @Delete
    suspend fun deleteItem(item: TodoItem)
}