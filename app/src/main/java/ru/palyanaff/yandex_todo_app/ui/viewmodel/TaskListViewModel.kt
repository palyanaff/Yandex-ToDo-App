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

    val taskList: LiveData<List<TodoItem>> = todoItemRepository.itemList.asLiveData()

    fun getCompleteTasks() = taskList.value?.count { it.complete }

    fun deleteItem(index: Int) = viewModelScope.launch {
        val item = taskList.value?.get(index)
        if (item != null) {
            todoItemRepository.deleteItem(item)
            Log.e("TaskListViewModel", taskList.value.toString())
        }
    }

    fun setTaskComplete(index: Int) = viewModelScope.launch {
        val item = taskList.value?.get(index)
        if (item != null) {
            todoItemRepository.completeItem(item)
        }
    }

    fun setTaskComplete(item: TodoItem) = viewModelScope.launch {
            todoItemRepository.completeItem(item)
    }
}