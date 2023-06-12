package ru.palyanaff.yandex_todo_app.ui.view_holder

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val checkBox: CheckBox = itemView.findViewById(R.id.complete_check_box)
    private val itemText: TextView = itemView.findViewById(R.id.item_text)
    private val infoButton: ImageButton = itemView.findViewById(R.id.info_image_button)
    private val colorRed = ContextCompat.getColor(itemView.context, R.color.red)
    private val colorGray = ContextCompat.getColor(itemView.context, R.color.gray)
    private val drawablePriorityHigh = ContextCompat.getDrawable(itemView.context, R.drawable.priority_high)
    private val drawablePriorityLow = ContextCompat.getDrawable(itemView.context, R.drawable.arrow_downward)

    fun onBind(todoItem: TodoItem) {
        drawablePriorityHigh?.setColorFilter(colorRed, PorterDuff.Mode.SRC_IN)
        drawablePriorityLow?.setColorFilter(colorGray, PorterDuff.Mode.SRC_IN)

        itemText.text = todoItem.text
        when (todoItem.priority) {
            PriorityStatus.HIGH -> {
                itemText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawablePriorityHigh,
                    null,
                    null,
                    null
                )
            }
            PriorityStatus.LOW -> {
                itemText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawablePriorityLow,
                    null,
                    null,
                    null
                )
            }
            else -> {
                itemText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, null, null
                )
            }
        }
    }
}