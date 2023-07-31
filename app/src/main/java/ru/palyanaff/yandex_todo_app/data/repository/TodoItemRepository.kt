package ru.palyanaff.yandex_todo_app.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import ru.palyanaff.yandex_todo_app.ApplicationScope
import ru.palyanaff.yandex_todo_app.data.database.TodoItemDatabase
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.model.TodoItemDao
import ru.palyanaff.yandex_todo_app.data.model.TodoItemDao_Impl
import javax.inject.Inject

@ApplicationScope
class TodoItemRepository @Inject constructor(
    private val todoItemDao: TodoItemDao,
    private val dataSource: dagger.Lazy<DataSource>,
) {
    private val TAG = "TodoItemRepository"
    private val _itemList = MutableLiveData<List<TodoItem>>()
    val itemList: Flow<List<TodoItem>> = todoItemDao.getAll()

    suspend fun addItem(item: TodoItem) {
        todoItemDao.addItem(item)
    }

    suspend fun completeItem(item: TodoItem) {
        item.complete = !item.complete
        editItem(item)
    }

    suspend fun editItem(item: TodoItem) {
        todoItemDao.editItem(item)
    }

    suspend fun deleteById(id: Int) {
        todoItemDao.deleteById(id)
        _itemList.value?.filter { it.id == id }
    }

    suspend fun deleteItem(item: TodoItem) {
        todoItemDao.deleteItem(item)
        Log.e("Repository", _itemList.value.toString())
    }

    suspend fun findItem(id: Int): TodoItem = todoItemDao.findById(id)

}