package ru.palyanaff.yandex_todo_app.data.repository

import ru.palyanaff.yandex_todo_app.data.model.TodoItem

class TodoItemRepository {
    private val itemList = mutableListOf(
        TodoItem("0", "sherek", "nizky", complete = false, null, "now", null),
        TodoItem("1", "kek", "nizky", complete = true, null, "10", null),
        TodoItem("2", "start", "norm", complete = false, null, "now", null),
        TodoItem("3", "pokushat", "visoki", complete = true, null, "now", null),
        TodoItem("4", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("5", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("6", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("7", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("8", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("9", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("10", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("11", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("12", "pospat", "visoki", complete = false, null, "now", null),
        TodoItem("13", "pospat", "visoki", complete = false, null, "now", null)
    )

    fun addItem(item: TodoItem) {
        itemList.add(item)
    }
    fun getList(): List<TodoItem> {
            return itemList
    }


}