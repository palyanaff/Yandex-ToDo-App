package ru.palyanaff.yandex_todo_app.ioc

import android.view.View
import androidx.lifecycle.LifecycleOwner
import ru.palyanaff.yandex_todo_app.ui.viewcontroller.TaskListViewController

class TaskListFragmentViewComponent(
    fragmentComponent: TaskListFragmentComponent,
    root: View,
    lifecycleOwner: LifecycleOwner,
) {

    val taskListViewController = TaskListViewController(
        fragmentComponent.fragment.requireActivity(),
        root,
        fragmentComponent.adapter,
        lifecycleOwner,
        fragmentComponent.viewModel,
    )
}