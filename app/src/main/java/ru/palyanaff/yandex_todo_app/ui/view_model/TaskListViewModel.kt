package ru.palyanaff.yandex_todo_app.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository

class TaskListViewModel : ViewModel() {
    private var _taskList = MutableLiveData<List<TodoItem>>()
    val taskList: LiveData<List<TodoItem>> = _taskList

    private val taskRepository = TodoItemRepository()

    init {
        getTaskList()
    }

    private fun getTaskList() {
        viewModelScope.launch {
            _taskList.value = taskRepository.getList()
        }
    }
}