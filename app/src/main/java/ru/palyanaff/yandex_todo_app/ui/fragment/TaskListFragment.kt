package ru.palyanaff.yandex_todo_app.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.palyanaff.yandex_todo_app.App
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.ioc.TaskListFragmentComponent
import ru.palyanaff.yandex_todo_app.ioc.TaskListFragmentViewComponent
import ru.palyanaff.yandex_todo_app.ui.adapter.TaskAdapter
import ru.palyanaff.yandex_todo_app.ui.viewmodel.TaskListViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {
    private val TAG = "TaskListFragment"

    // TODO: understand
    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: TaskListFragmentComponent
    private var fragmentViewComponent: TaskListFragmentViewComponent? = null

    // Initialize ViewModel
    private val viewModel: TaskListViewModel by viewModels { applicationComponent.taskListViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = TaskListFragmentComponent(
            applicationComponent, //TODO: понять почему так
            fragment = this,
            viewModel = viewModel
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_task_list, container, false)
        fragmentViewComponent = TaskListFragmentViewComponent(
            fragmentComponent,
            root = view,
            lifecycleOwner = viewLifecycleOwner
        ).apply { taskListViewController.setUpTaskList() }

        val fab = view.findViewById<FloatingActionButton>(R.id.add_task_button)
        fab.setOnClickListener { createNewTask() }

        return view
    }


    private fun createNewTask() {
        findNavController().navigate(R.id.action_taskListFragment_to_newTaskFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentViewComponent = null
    }

}