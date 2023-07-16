package ru.palyanaff.yandex_todo_app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.model.TodoItemDao

class TodoItemRepository(
    private val todoItemDao: TodoItemDao,
    dataSource: DataSource,
) {
    private val TAG = "TodoItemRepository"
    private val _itemList = MutableLiveData<List<TodoItem>>()
    val itemList: LiveData<List<TodoItem>> = _itemList

    // TODO: add initialization of TodoItemDatabase with context(?)
    suspend fun updateTodoList() {
        _itemList.value = todoItemDao.getAll()
    }

    suspend fun addItem(item: TodoItem) {
        todoItemDao.addItem(item)
        updateTodoList()
    }

    fun completeItem(id: Int) {
        _itemList.value.orEmpty().map { if (it.id == id) it.complete = !it.complete }
    }

    suspend fun deleteItem(item: TodoItem) {
        todoItemDao.deleteItem(item)
        updateTodoList()
        //_itemList.postValue(_itemList.value.orEmpty().filter { it.id != id }.toMutableList())
    }

    fun findItem(id: Int): TodoItem = todoItemDao.findById(id)

}