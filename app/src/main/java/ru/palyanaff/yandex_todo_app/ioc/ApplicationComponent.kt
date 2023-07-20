package ru.palyanaff.yandex_todo_app.ioc

import android.content.Context
import android.util.Log
import ru.palyanaff.yandex_todo_app.data.database.TodoItemDatabase
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository

class ApplicationComponent(
    context: Context
) {
    private val dataSource by lazy { DataSource() }
    private val database by lazy { TodoItemDatabase.getDatabase(context = context) }
    private val todoItemRepository by lazy {
        TodoItemRepository(
            todoItemDao = database.todoItemDao(),
            dataSource = dataSource,
        )
    }

    val viewModelFactory = ViewModelFactory(todoItemRepository)

    init {
        Log.i("ApplicationComponent", todoItemRepository.itemList.value.toString())
    }
}