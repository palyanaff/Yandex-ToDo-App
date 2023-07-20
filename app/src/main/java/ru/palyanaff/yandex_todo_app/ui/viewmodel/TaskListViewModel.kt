package ru.palyanaff.yandex_todo_app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository

class TaskListViewModel(
    private val todoItemRepository: TodoItemRepository
) : ViewModel() {
    val taskList = todoItemRepository.itemList.map { list ->
        list.map { it }
    }

    init {
        getTaskList()
    }

    fun getTaskList() {
        viewModelScope.launch {
            todoItemRepository.updateTodoList()
        }
    }

    fun getCompleteTasks() = todoItemRepository.itemList.value?.count { it.complete }

    fun deleteItem(id: Int) = viewModelScope.launch {
        todoItemRepository.deleteById(id)
    }

    fun setTaskComplete(todoItem: TodoItem) {
        // TODO: refresh view layout (complete textView)
        todoItemRepository.completeItem(todoItem.id)
    }
}