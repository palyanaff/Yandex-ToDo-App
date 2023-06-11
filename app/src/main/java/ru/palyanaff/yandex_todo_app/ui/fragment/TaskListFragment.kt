package ru.palyanaff.yandex_todo_app.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {

    lateinit var taskRecyclerView: RecyclerView
    private val taskRepository = TodoItemRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_task_list, container, false)
        taskRecyclerView = view.findViewById(R.id.task_list_recycler_view)
        val taskAdapter = TaskAdapter()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        taskRecyclerView.adapter = taskAdapter
        taskRecyclerView.layoutManager = layoutManager
        taskAdapter.tasks= taskRepository.getList()
        return view

    }

}