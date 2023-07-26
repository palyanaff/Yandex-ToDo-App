package ru.palyanaff.yandex_todo_app.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.palyanaff.yandex_todo_app.App
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import ru.palyanaff.yandex_todo_app.ioc.ApplicationComponent
import javax.inject.Inject

class TaskListViewModel @Inject constructor(
    private val todoItemRepository: TodoItemRepository,
) : ViewModel() {

    private var _taskList = MutableLiveData<List<TodoItem>>()
    val taskList: LiveData<List<TodoItem>> = _taskList

    init {
        viewModelScope.launch {
            todoItemRepository.updateTodoList()
        }
        todoItemRepository.itemList.observeForever { tasks ->
            _taskList.value = tasks
        }
    }

    fun getCompleteTasks() = _taskList.value?.count { it.complete }

    fun deleteItem(index: Int) = viewModelScope.launch {
        val item = _taskList.value?.get(index)
        if (item != null) {
            todoItemRepository.deleteItem(item)
            Log.e("TaskListViewModel", _taskList.value.toString())
            //getTaskList() // TODO change to observer
        }
    }

    fun setTaskComplete(index: Int) = viewModelScope.launch {
        val item = _taskList.value?.get(index)
        if (item != null) {
            todoItemRepository.completeItem(item)
            //todoItemRepository.updateTodoList()
            //getTaskList() // TODO change to observer
        }
    }

    fun setTaskComplete(item: TodoItem) = viewModelScope.launch {
            todoItemRepository.completeItem(item)
            //todoItemRepository.updateTodoList()
            //getTaskList() // TODO change to observer
    }

    override fun onCleared() {
        todoItemRepository.itemList.removeObserver { tasks ->
            _taskList.value = tasks
        }
        super.onCleared()
    }
}