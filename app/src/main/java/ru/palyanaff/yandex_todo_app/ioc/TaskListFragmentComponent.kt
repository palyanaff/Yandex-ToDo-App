package ru.palyanaff.yandex_todo_app.ioc

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import ru.palyanaff.yandex_todo_app.data.database.DatabaseModule
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskAdapter
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskDiffUtil
import ru.palyanaff.yandex_todo_app.ui.fragment.NewTaskFragment
import ru.palyanaff.yandex_todo_app.ui.fragment.TaskListFragment
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel
import javax.inject.Inject
import javax.inject.Scope

@Scope
annotation class FragmentScope

@Subcomponent
@FragmentScope
interface TaskListFragmentComponent {

    fun inject(fragment: TaskListFragment)
    fun getAdapter(): TaskAdapter

    fun viewModelFactory(): ViewModelFactory
}