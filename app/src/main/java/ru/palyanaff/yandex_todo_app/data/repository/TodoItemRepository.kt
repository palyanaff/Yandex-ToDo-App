package ru.palyanaff.yandex_todo_app.data.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.palyanaff.yandex_todo_app.data.database.TodoItemDatabase
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem

class TodoItemRepository(
    private val dataSource: DataSource
) {
    private val TAG = "TodoItemRepository"
    private val _itemList = MutableLiveData<MutableList<TodoItem>>()
    val itemList: LiveData<MutableList<TodoItem>> = _itemList

    @MainThread
    suspend fun updateTodoList() {
        _itemList.value = dataSource.loadTodoItems()
    }

    fun addItem(item: TodoItem) {
        _itemList.value?.add(item)
    }

    fun completeItem(id: String) {
        _itemList.value.orEmpty().map { if (it.id == id) it.complete = !it.complete }
    }

    fun deleteItem(id: Int) {
        _itemList.value?.removeAt(id)
        //_itemList.postValue(_itemList.value.orEmpty().filter { it.id != id }.toMutableList())
    }


}