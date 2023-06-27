package ru.palyanaff.yandex_todo_app.ioc

import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository

class ApplicationComponent {
    private val dataSource = DataSource()
    private val todoItemRepository = TodoItemRepository(dataSource)

    val viewModelFactory = ViewModelFactory(todoItemRepository)
}