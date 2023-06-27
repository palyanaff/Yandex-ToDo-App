package ru.palyanaff.yandex_todo_app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository

class TaskListViewModel(
    private val todoItemRepository: TodoItemRepository
) : ViewModel() {
    private var _taskList = MutableLiveData<List<TodoItem>>()
    val taskList: LiveData<List<TodoItem>> = _taskList

    init {
        getTaskList()
    }

    private fun getTaskList() {
        viewModelScope.launch {
            _taskList.value = todoItemRepository.itemList.value
        }
    }
}