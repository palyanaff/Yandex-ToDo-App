package ru.palyanaff.yandex_todo_app.ui.viewholder

import android.graphics.PorterDuff
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel

class TaskViewHolder(
    itemView: View,
    private val viewModel: TaskListViewModel,
) : RecyclerView.ViewHolder(itemView) {
    private val checkBox = itemView.findViewById<CheckBox>(R.id.complete_check_box)
    private val itemText = itemView.findViewById<TextView>(R.id.item_text)
    private val infoButton = itemView.findViewById<ImageButton>(R.id.info_image_button)
    private val dataText = itemView.findViewById<TextView>(R.id.data_text)

    private val colorRed = ContextCompat.getColor(itemView.context, R.color.red)
    private val colorGray = ContextCompat.getColor(itemView.context, R.color.gray)
    private val drawablePriorityHigh =
        ContextCompat.getDrawable(itemView.context, R.drawable.priority_high)
    private val drawablePriorityLow =
        ContextCompat.getDrawable(itemView.context, R.drawable.arrow_downward)

    fun onBind(todoItem: TodoItem) {
        drawablePriorityHigh?.setColorFilter(colorRed, PorterDuff.Mode.SRC_IN)
        drawablePriorityLow?.setColorFilter(colorGray, PorterDuff.Mode.SRC_IN)

        itemText.text = todoItem.text
        todoItem.deadlineDate?.let {
            dataText.text = it
            dataText.visibility = View.VISIBLE
        }

        infoButton.setOnClickListener { openTask(todoItem) }
        when (todoItem.complete) {
            true -> {
                checkBox.isChecked = true
                // TODO: it works
                /*itemText.setTextColor(colorGray)
                itemText.paintFlags = itemText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG*/
            }
            false -> {
                checkBox.isChecked = false
            }
        }

        checkBox.setOnClickListener { checkTask(todoItem) }

        when (todoItem.priority) {
            PriorityStatus.HIGH -> {
                itemText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawablePriorityHigh, null, null, null
                )
            }
            PriorityStatus.LOW -> {
                itemText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawablePriorityLow, null, null, null
                )
            }
            else -> {
                itemText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, null, null
                )
            }
        }
    }

    private fun checkTask(todoItem: TodoItem) {
        viewModel.setTaskComplete(todoItem)
    }

    private fun openTask(todoItem: TodoItem) { // TODO: send TodoItem in fragment
        itemView.findNavController().navigate(R.id.action_taskListFragment_to_newTaskFragment)
    }
}