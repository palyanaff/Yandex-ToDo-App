package ru.palyanaff.yandex_todo_app.data.model

import java.util.Date

data class TodoItem(
    val id: String = "",
    val text: String = "",
    val priority: PriorityStatus = PriorityStatus.NORMAL,
    val complete: Boolean = false,
    val deadlineDate: String? = "",  //TODO: изменить на Date
    val creationDate: String = "",   //TODO: изменить на Date
    val changeDate: String? = "",    //TODO: изменить на Date
    )

enum class PriorityStatus { LOW, NORMAL, HIGH }
