package ru.palyanaff.yandex_todo_app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository

class NewTaskViewModel : ViewModel() {
    private val TAG = "NewTaskViewModel"
    private var _taskItem = MutableLiveData<TodoItem>()
    val taskItem: LiveData<TodoItem> = _taskItem

    private val todoItemRepository = TodoItemRepository(DataSource())

    init {
        _taskItem.value = TodoItem()
        Log.e(TAG, _taskItem.value.toString())
    }

    fun setText(text: String?) {
        if (text != null) {
            _taskItem.value!!.text = text
        }

    }

    fun setDeadline(deadline: String?) {
        _taskItem.value!!.deadlineDate = deadline

    }

    fun setPriority(priority: PriorityStatus) {
        _taskItem.value!!.priority = priority

    }

    fun saveTodoItem() {
        _taskItem.value?.let {
            todoItemRepository.addItem(it)
            Log.e(TAG, todoItemRepository.itemList.value.toString())
        }
    }
}