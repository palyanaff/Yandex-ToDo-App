package ru.palyanaff.yandex_todo_app.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskAdapter
import ru.palyanaff.yandex_todo_app.ui.view_model.TaskListViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {

    // Initialize ViewModel
    private lateinit var taskListViewModel: TaskListViewModel
    lateinit var taskRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_task_list, container, false)
        taskListViewModel = ViewModelProvider(requireActivity())[TaskListViewModel::class.java]
        taskRecyclerView = view.findViewById(R.id.task_list_recycler_view)
        val taskAdapter = TaskAdapter()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        taskRecyclerView.adapter = taskAdapter
        taskRecyclerView.layoutManager = layoutManager
        taskAdapter.tasks = taskListViewModel.taskList.value!!

        val fab = view.findViewById<FloatingActionButton>(R.id.add_task_button)
        fab.setOnClickListener { createNewTask() }
        return view

    }

    private fun createNewTask() {
        findNavController().navigate(R.id.action_taskListFragment_to_newTaskFragment)
    }

}