package ru.palyanaff.yandex_todo_app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import javax.inject.Inject

class TaskListViewModel @Inject constructor(
    private val todoItemRepository: TodoItemRepository
) : ViewModel() {

    private var _taskList = MutableLiveData<List<TodoItem>>()
    val taskList: LiveData<List<TodoItem>> = _taskList


    init {
        getTaskList()
    }

    fun getTaskList() {
        viewModelScope.launch {
            todoItemRepository.updateTodoList()
            _taskList = todoItemRepository.itemList as MutableLiveData<List<TodoItem>>
        }
    }

    fun getCompleteTasks() = _taskList.value?.count { it.complete }

    fun deleteItem(id: Int) = viewModelScope.launch {
        todoItemRepository.deleteById(id)
    }

    fun setTaskComplete(todoItem: TodoItem) {
        // TODO: refresh view layout (complete textView)
        todoItemRepository.completeItem(todoItem.id)
    }
}