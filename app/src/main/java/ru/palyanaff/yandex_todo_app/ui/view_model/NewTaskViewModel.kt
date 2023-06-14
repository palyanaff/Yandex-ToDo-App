package ru.palyanaff.yandex_todo_app.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem

class NewTaskViewModel(todoItem: TodoItem? = null) : ViewModel() {
    private var _taskItem = MutableLiveData<TodoItem>(todoItem)
    val taskItem : LiveData<TodoItem> = _taskItem

    private fun setTodoItem(
        id: String = "",
        text: String,
        priority: PriorityStatus,
        complete: Boolean,
        deadlineDate: String?,
        creationDate: String,
        changeDate: String?,
    ){

    }
}