package ru.palyanaff.yandex_todo_app.ioc

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.palyanaff.yandex_todo_app.ApplicationScope
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import ru.palyanaff.yandex_todo_app.ui.viewmodel.NewTaskViewModel
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel
import javax.inject.Inject

//@ApplicationScope
class ViewModelFactory @Inject constructor(
    private val todoItemRepository: TodoItemRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        TaskListViewModel::class.java -> TaskListViewModel(
            todoItemRepository
        )
        NewTaskViewModel::class.java -> NewTaskViewModel(
            todoItemRepository
        )
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}