package ru.palyanaff.yandex_todo_app.ioc

import android.content.Context
import android.util.Log
import dagger.BindsInstance
import dagger.Component
import ru.palyanaff.yandex_todo_app.ApplicationScope
import ru.palyanaff.yandex_todo_app.data.database.TodoItemDatabase
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import ru.palyanaff.yandex_todo_app.ui.fragment.NewTaskFragment
import ru.palyanaff.yandex_todo_app.ui.fragment.TaskListFragment

@ApplicationScope
@Component(modules = [TodoItemDatabase::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }

    fun inject(newTaskFragment: NewTaskFragment)
    fun inject(taskListFragment: TaskListFragment)

    fun getTodoItemRepository(): TodoItemRepository

    fun getDataSource(): DataSource

    fun viewModelFactory(): ViewModelFactory

}