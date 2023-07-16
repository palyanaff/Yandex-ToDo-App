package ru.palyanaff.yandex_todo_app

import android.app.Application
import android.content.Context
import dagger.Component
import ru.palyanaff.yandex_todo_app.ioc.ApplicationComponent

class App : Application() {
    val applicationComponent by lazy { ApplicationComponent(this) }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}