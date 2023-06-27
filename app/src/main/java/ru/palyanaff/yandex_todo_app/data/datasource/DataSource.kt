package ru.palyanaff.yandex_todo_app.data.datasource

import kotlinx.coroutines.delay
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem

class DataSource {
    private val itemList = listOf(
        TodoItem("0", "sherek", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("1", "kek", PriorityStatus.LOW, complete = true, "10", "10", null),
        TodoItem("2", "start", PriorityStatus.NORMAL, complete = false, "now", "now", null),
        TodoItem("3", "pokushat ooo oooo oo oc ccc ccc ccc ccc hee eee ee eeen mnoga i vkusno",
            PriorityStatus.HIGH, complete = true, null, "now", null),
        TodoItem("4", "pospato oo oooo ooo cc cc ccc cc cccc heeeee eeeeen mnoga i oooo oo ooo cc ccc cc cccc cc heee ee eee een mno ga",
            PriorityStatus.HIGH, complete = false, null, "now", null),
        TodoItem("5", "pospat", PriorityStatus.NORMAL, complete = true, null, "now", null),
        TodoItem("6", "pospat", PriorityStatus.NORMAL, complete = false, null, "now", null),
        TodoItem("7", "pospat", PriorityStatus.NORMAL, complete = true, "tomorrow", "now", null),
        TodoItem("8", "pospat", PriorityStatus.NORMAL, complete = false, null, "now", null),
        TodoItem("9", "pospat", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("10", "pospat", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("11", "pospat", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("12", "pospat", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("13", "pospat", PriorityStatus.LOW, complete = false, null, "now", null)
    )

    fun loadTodoItems(): List<TodoItem> {
        return itemList
    }
}