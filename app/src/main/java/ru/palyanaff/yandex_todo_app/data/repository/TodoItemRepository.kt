package ru.palyanaff.yandex_todo_app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.palyanaff.yandex_todo_app.data.database.TodoItemDatabase
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.model.TodoItemDao
import ru.palyanaff.yandex_todo_app.data.model.TodoItemDao_Impl
import javax.inject.Inject

//@ApplicationScope
class TodoItemRepository @Inject constructor(
    private val todoItemDao: TodoItemDao,
    private val dataSource: dagger.Lazy<DataSource>,
) {
    private val TAG = "TodoItemRepository"
    private val _itemList = MutableLiveData<List<TodoItem>>()
    val itemList: LiveData<List<TodoItem>> = _itemList

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

    suspend fun deleteById(id: Int) {
        todoItemDao.deleteById(id)
        _itemList.value?.filter { it.id == id }
        updateTodoList()
    }

    suspend fun deleteItem(item: TodoItem) {
        todoItemDao.deleteItem(item)
        updateTodoList()
        //_itemList.postValue(_itemList.value.orEmpty().filter { it.id != id }.toMutableList())
    }

    suspend fun findItem(id: Int): TodoItem = todoItemDao.findById(id)

}