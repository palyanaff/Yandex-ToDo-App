package ru.palyanaff.yandex_todo_app.ui.viewcontroller

import android.app.Activity
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskAdapter
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel

class TaskListViewController(
    private val activity: Activity,
    rootView: View,
    private val adapter: TaskAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: TaskListViewModel,
) {
    private val recyclerView: RecyclerView = rootView.findViewById(R.id.task_list_recycler_view)

    fun setUpTaskList(){
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        viewModel.taskList.observe(lifecycleOwner) { newTask ->
            adapter.submitList(newTask)
        }
        viewModel.getTaskList()
    }
}