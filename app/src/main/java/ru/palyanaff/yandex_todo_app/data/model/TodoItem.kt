package ru.palyanaff.yandex_todo_app.data.model

import java.util.Date

data class TodoItem(
    val id: String,
    val text: String,
    val priority: String,       //TODO: изменить на enum (“низкая”, “обычная”, “срочная»)
    val complete: Boolean,
    val deadlineDate: String?,  //TODO: изменить на Date
    val creationDate: String,   //TODO: изменить на Date
    val changeDate: String?,    //TODO: изменить на Date
    )
