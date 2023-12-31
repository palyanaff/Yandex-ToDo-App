package ru.palyanaff.yandex_todo_app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import javax.inject.Inject

class NewTaskViewModel @Inject constructor(
    private val todoItemRepository: TodoItemRepository
) : ViewModel() {
    private val TAG = "NewTaskViewModel"
    private var refactorStatus = false

    private var _taskItem = MutableLiveData<TodoItem>()
    val taskItem: LiveData<TodoItem> = _taskItem

    init {
        _taskItem.value = TodoItem(0)
    }

    fun setRefactorStatus(id: Int) {
        if (id != -1) {
            viewModelScope.launch {
                refactorStatus = true
                _taskItem.value = todoItemRepository.findItem(id)
            }
        }
    }

    fun setText(text: String?) {
        if (text != null) {
            _taskItem.value!!.text = text.trim()
        }
    }

    fun setDeadline(deadline: String?) {
        _taskItem.value!!.deadlineDate = deadline
    }

    fun setPriority(priority: PriorityStatus) {
        _taskItem.value!!.priority = priority
    }

    /**
     * If item is refactoring, edit it in the db
     * Else add it in the db
     */
    fun saveTodoItem() = viewModelScope.launch {
        _taskItem.value?.let {
            if (!refactorStatus) {
                todoItemRepository.addItem(it)
            } else {
                todoItemRepository.editItem(it)
            }
        }
    }

    /**
     * If item is refactoring, delete it from the db
     */
    fun deleteTodoItem() = viewModelScope.launch {
        if (refactorStatus) {
            _taskItem.value?.let { todoItemRepository.deleteItem(it) }
        }
    }
}