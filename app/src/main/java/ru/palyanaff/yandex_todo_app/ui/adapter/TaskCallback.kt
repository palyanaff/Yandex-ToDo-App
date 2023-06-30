package ru.palyanaff.yandex_todo_app.ui.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import ru.palyanaff.yandex_todo_app.ui.activity.MainActivity
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel

class TaskCallback(
    private val viewModel: TaskListViewModel,
    private val adapter: TaskAdapter,
    dragDirs: Int, swipeDirs: Int
) :
    ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        when (direction) {
            ItemTouchHelper.LEFT -> {
                viewModel.deleteItem(position)
                adapter.notifyItemRemoved(position)
            }
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        // set background color (from youtube tutorial) TODO: need to be fixed
        val itemView: View = viewHolder.itemView
        val bg = ColorDrawable()
        bg.color = Color.RED
        bg.setBounds(itemView.left, itemView.top, itemView.right, itemView.bottom)
        bg.draw(c)

        // set icon
        val icon = ActivityCompat.getDrawable(itemView.context, R.drawable.delete)
        val top = ((itemView.height / 2) - ((icon!!.intrinsicHeight) / 2)) + itemView.top
        icon.setBounds(
            itemView.right - icon.intrinsicWidth - 45,
            top,
            itemView.right - 45,
            top + icon.intrinsicHeight
        )
        icon.draw(c)
    }
}