package ru.palyanaff.yandex_todo_app.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.palyanaff.yandex_todo_app.App
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.ioc.TaskListFragmentComponent
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskAdapter
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskCallback
import ru.palyanaff.yandex_todo_app.ui.viewmodel.NewTaskViewModel
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {
    private val TAG = "TaskListFragment"
    private lateinit var recyclerView: RecyclerView
    private lateinit var callback: TaskCallback
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var countText: TextView
    private lateinit var component: TaskListFragmentComponent
    private lateinit var adapter: TaskAdapter

    private val viewModel: TaskListViewModel by viewModels { component.viewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        component = (requireActivity().applicationContext as App)
            .applicationComponent
            .taskListFragmentComponent()

        component.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_task_list, container, false)
        recyclerView = view.findViewById(R.id.task_list_recycler_view)
        adapter = component.getAdapter()
        callback = TaskCallback(
            viewModel,
            adapter,
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
        itemTouchHelper = ItemTouchHelper(callback)
        countText = view.findViewById(R.id.complete_text_view)
        setUpTaskList()

        val fab = view.findViewById<FloatingActionButton>(R.id.add_task_button)
        fab.setOnClickListener { createNewTask() }

        return view
    }


    private fun createNewTask() {
        findNavController().navigate(R.id.action_taskListFragment_to_newTaskFragment)
    }

    private fun setUpTaskList() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(recyclerView)
        viewModel.taskList.observe(requireActivity()) { tasks ->
            tasks.let {
                adapter.submitList(tasks)
                countText.text =
                    "Complete - ${viewModel.getCompleteTasks()}" //TODO: change to string recurse
            }
        }
    }

}