package ru.palyanaff.yandex_todo_app.data.repository

import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem

class TodoItemRepository {
    private val itemList = mutableListOf(
        TodoItem("0", "sherek", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("1", "kek", PriorityStatus.LOW, complete = true, null, "10", null),
        TodoItem("2", "start", PriorityStatus.NORMAL, complete = false, null, "now", null),
        TodoItem("3", "pokushat ooo oooo oo oc ccc ccc ccc ccc hee eee ee eeen mnoga i vkusno",
            PriorityStatus.HIGH, complete = true, null, "now", null),
        TodoItem("4", "pospato oooooo ooocc ccccc cccccc heeeee eeeeen mnoga i oooooo ooocc ccccc cccccc heeeee eeeeen mnoga",
            PriorityStatus.HIGH, complete = false, null, "now", null),
        TodoItem("5", "pospat", PriorityStatus.NORMAL, complete = false, null, "now", null),
        TodoItem("6", "pospat", PriorityStatus.NORMAL, complete = false, null, "now", null),
        TodoItem("7", "pospat", PriorityStatus.NORMAL, complete = false, null, "now", null),
        TodoItem("8", "pospat", PriorityStatus.NORMAL, complete = false, null, "now", null),
        TodoItem("9", "pospat", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("10", "pospat", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("11", "pospat", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("12", "pospat", PriorityStatus.LOW, complete = false, null, "now", null),
        TodoItem("13", "pospat", PriorityStatus.LOW, complete = false, null, "now", null)
    )

    fun addItem(item: TodoItem) {
        itemList.add(item)
    }
    fun getList(): List<TodoItem> {
            return itemList
    }


}