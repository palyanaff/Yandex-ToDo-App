package ru.palyanaff.yandex_todo_app

import android.app.Application
import android.content.Context
import ru.palyanaff.yandex_todo_app.ioc.DaggerApplicationComponent
import javax.inject.Scope

@Scope
annotation class ApplicationScope


class App : Application() {
    val applicationComponent = DaggerApplicationComponent.factory().create(this)

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}