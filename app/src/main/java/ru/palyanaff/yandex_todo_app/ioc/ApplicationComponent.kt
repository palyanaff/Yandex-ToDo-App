package ru.palyanaff.yandex_todo_app.ioc

import android.util.Log
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository

class ApplicationComponent {
    private val dataSource = DataSource()
    private val todoItemRepository = TodoItemRepository(dataSource)

    val taskListViewModelFactory = TaskListViewModelFactory(todoItemRepository)
    val newTaskViewModelFactory = NewTaskViewModelFactory(todoItemRepository)
    init {
        Log.i("ApplicationComponent", todoItemRepository.itemList.value.toString())
    }
}