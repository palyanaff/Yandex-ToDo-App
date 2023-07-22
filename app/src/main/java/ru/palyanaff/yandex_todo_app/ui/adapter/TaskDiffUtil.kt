package ru.palyanaff.yandex_todo_app.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import javax.inject.Inject

class TaskDiffUtil @Inject constructor() : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }

}