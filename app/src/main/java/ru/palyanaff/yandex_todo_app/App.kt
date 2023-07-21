package ru.palyanaff.yandex_todo_app

import android.app.Application
import android.content.Context
import dagger.Component
import ru.palyanaff.yandex_todo_app.data.database.DatabaseModule
import ru.palyanaff.yandex_todo_app.data.database.TodoItemDatabase
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import ru.palyanaff.yandex_todo_app.ioc.ApplicationComponent
import javax.inject.Scope

@Scope
annotation class ApplicationScope

@ApplicationScope
@Component(modules = [DatabaseModule::class])
interface ApplicationComponent {
    fun todoItemRepository(): TodoItemRepository
}

class App : Application() {
    val applicationComponent by lazy { ApplicationComponent(this) }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}