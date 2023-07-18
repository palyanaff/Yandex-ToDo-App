package ru.palyanaff.yandex_todo_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo_items")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "text") var text: String = "",
    @ColumnInfo(name = "priority") var priority: PriorityStatus = PriorityStatus.NORMAL,
    @ColumnInfo(name = "complete") var complete: Boolean = false,
    @ColumnInfo(name = "deadline_date") var deadlineDate: String? = "",  //TODO: изменить на Date
    @ColumnInfo(name = "creation_date") val creationDate: String = "",   //TODO: изменить на Date
    @ColumnInfo(name = "change_date") var changeDate: String? = "",    //TODO: изменить на Date
)

enum class PriorityStatus { LOW, NORMAL, HIGH }
