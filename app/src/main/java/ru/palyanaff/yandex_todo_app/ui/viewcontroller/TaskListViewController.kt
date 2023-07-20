package ru.palyanaff.yandex_todo_app.ui.viewcontroller

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskAdapter
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskCallback
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel

class TaskListViewController(
    private val activity: Activity,
    rootView: View,
    private val adapter: TaskAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: TaskListViewModel,
) {
    private val recyclerView: RecyclerView = rootView.findViewById(R.id.task_list_recycler_view)
    private val callback = TaskCallback(viewModel, adapter, 0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    private val itemTouchHelper = ItemTouchHelper(callback)
    private val countText: TextView = rootView.findViewById(R.id.complete_text_view)

    fun setUpTaskList() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(recyclerView)
        viewModel.taskList.observe(lifecycleOwner) { newTask ->
            adapter.submitList(newTask)
            countText.text =
                "Complete - ${viewModel.getCompleteTasks()}" //TODO: change to string recurse
        }
        viewModel.getTaskList()
    }
}