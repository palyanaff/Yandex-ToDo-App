package ru.palyanaff.yandex_todo_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.ioc.FragmentScope
import ru.palyanaff.yandex_todo_app.ui.viewholder.TaskViewHolder
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel
import javax.inject.Inject

@FragmentScope
class TaskAdapter @Inject constructor(
    taskDiffUtil: TaskDiffUtil
) : ListAdapter<TodoItem, TaskViewHolder>(taskDiffUtil) {

    @Inject lateinit var viewModel: TaskListViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_item,
            parent,
            false
        )
        return TaskViewHolder(itemView, viewModel)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}



