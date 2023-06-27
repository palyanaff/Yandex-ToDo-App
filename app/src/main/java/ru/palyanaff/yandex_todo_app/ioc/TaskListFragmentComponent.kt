package ru.palyanaff.yandex_todo_app.ioc

import androidx.fragment.app.Fragment
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskAdapter
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskDiffUtil
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel

//TODO: understand
class TaskListFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: Fragment,
    val viewModel:TaskListViewModel
) {
    val adapter = TaskAdapter(viewModel, TaskDiffUtil())
}