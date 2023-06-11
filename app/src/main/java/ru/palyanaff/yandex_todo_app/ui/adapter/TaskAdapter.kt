package ru.palyanaff.yandex_todo_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.model.TodoItem

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkBox: CheckBox = itemView.findViewById(R.id.complete_check_box)
        private val itemText: TextView = itemView.findViewById(R.id.item_text)
        private val infoButton: ImageButton = itemView.findViewById(R.id.info_image_button)

        fun onBind(todoItem: TodoItem){
            itemText.text = todoItem.text
        }
    }

    var tasks = listOf<TodoItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(
            layoutInflater.inflate(R.layout.todo_item, parent, false)
        )
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(tasks[position])
    }
}

