package ru.palyanaff.yandex_todo_app.data.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    suspend fun completeItem(id: String) {
        withContext(Dispatchers.Default) {
            _itemList.value.orEmpty().map { if (it.id == id) it.complete = true }
        }
    }

    suspend fun deleteItem(id: String) {
        withContext(Dispatchers.Default) {
            _itemList.postValue(_itemList.value.orEmpty().filter { it.id != id }.toMutableList())
        }
    }


}