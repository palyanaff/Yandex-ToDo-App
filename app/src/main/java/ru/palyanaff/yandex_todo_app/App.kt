package ru.palyanaff.yandex_todo_app

import android.app.Application
import android.content.Context
import ru.palyanaff.yandex_todo_app.ioc.ApplicationComponent
import ru.palyanaff.yandex_todo_app.ioc.DaggerApplicationComponent
import javax.inject.Scope

@Scope
annotation class ApplicationScope


class App : Application() {
    lateinit var applicationComponent: ApplicationComponent
            private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}