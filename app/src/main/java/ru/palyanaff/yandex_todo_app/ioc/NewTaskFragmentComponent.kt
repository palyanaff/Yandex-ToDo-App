package ru.palyanaff.yandex_todo_app.ioc

import dagger.Subcomponent
import ru.palyanaff.yandex_todo_app.ui.fragment.NewTaskFragment

@Subcomponent
@FragmentScope
interface NewTaskFragmentComponent {
    fun inject(fragment: NewTaskFragment)

    fun viewModelFactory(): ViewModelFactory
}