package ru.palyanaff.yandex_todo_app.ioc

import android.content.Context
import android.util.Log
import dagger.BindsInstance
import dagger.Component
import ru.palyanaff.yandex_todo_app.ApplicationScope
import ru.palyanaff.yandex_todo_app.data.database.DatabaseModule
import ru.palyanaff.yandex_todo_app.data.database.TodoItemDatabase
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository

@ApplicationScope
@Component(modules = [DatabaseModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }

    fun taskListFragmentComponent(): TaskListFragmentComponent
    fun newTaskFragmentComponent(): NewTaskFragmentComponent

    fun getTodoItemRepository(): TodoItemRepository

    fun getDataSource(): DataSource

}