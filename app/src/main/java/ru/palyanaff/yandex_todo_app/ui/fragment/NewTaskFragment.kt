package ru.palyanaff.yandex_todo_app.ui.fragment

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.palyanaff.yandex_todo_app.App
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.database.TodoItemDatabase
import ru.palyanaff.yandex_todo_app.data.datasource.DataSource
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import ru.palyanaff.yandex_todo_app.data.model.TodoItem
import ru.palyanaff.yandex_todo_app.data.repository.TodoItemRepository
import ru.palyanaff.yandex_todo_app.ioc.ApplicationComponent
import ru.palyanaff.yandex_todo_app.ioc.NewTaskFragmentComponent
import ru.palyanaff.yandex_todo_app.ui.viewmodel.NewTaskViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [NewTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTaskFragment : Fragment() {
    private val TAG = "NewTaskFragment"

    private lateinit var component: NewTaskFragmentComponent

    private val viewModel: NewTaskViewModel by viewModels { component.viewModelFactory() }

    private lateinit var editText: EditText //TODO: reform edit text
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = (requireActivity().applicationContext as App)
            .applicationComponent
            .newTaskFragmentComponent()
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize all views on screen
        val view: View = inflater.inflate(R.layout.fragment_new_task, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveText = view.findViewById<TextView>(R.id.save_task_text)
        val deleteButton = view.findViewById<ImageButton>(R.id.delete_image_button)
        val deleteText = view.findViewById<TextView>(R.id.delete_text)
        val dateText = view.findViewById<TextView>(R.id.date_set_text)
        val closeButton = view.findViewById<ImageButton>(R.id.close_image_button)
        val dateSwitch = view.findViewById<Switch>(R.id.date_switch)
        val priorityText = view.findViewById<TextView>(R.id.priority_text)
        val priorityLabelText = view.findViewById<TextView>(R.id.priority_label_text)
        editText = view.findViewById<EditText>(R.id.new_edit_text)

        val args: NewTaskFragmentArgs by navArgs()
        viewModel.setRefactorStatus(args.id)

        // TODO: change requireActivity to lifecycleOwner
        viewModel.taskItem.observe(requireActivity()) { newItem ->
            newItem.let {
                dateText.text = it.deadlineDate
                when (it.priority) {
                    PriorityStatus.HIGH -> priorityText.text = getString(R.string.high)
                    PriorityStatus.NORMAL -> priorityText.text = getString(R.string.normal)
                    PriorityStatus.LOW -> priorityText.text = getString(R.string.low)
                }
                editText.setText(it.text)
            }
        }

        val popupMenu = PopupMenu(view.context, priorityLabelText)
        popupMenu.menuInflater.inflate(R.menu.menu_priority, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.priority_high -> {
                    viewModel.setPriority(PriorityStatus.HIGH)
                    priorityText.text = getString(R.string.high)
                    true
                }
                R.id.priority_normal -> {
                    viewModel.setPriority(PriorityStatus.NORMAL)
                    priorityText.text = getString(R.string.normal)
                    true
                }
                R.id.priority_low -> {
                    viewModel.setPriority(PriorityStatus.LOW)
                    priorityText.text = getString(R.string.low)
                    true
                }
                else -> false
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        priorityLabelText.setOnClickListener {
            popupMenu.show()
            Log.e(TAG, viewModel.taskItem.value?.priority.toString())
        }
        saveText.setOnClickListener { saveTask() }
        deleteButton.setOnClickListener { deleteTask() }
        deleteText.setOnClickListener { deleteTask() }
        closeButton.setOnClickListener { closeTask() }
        // Set switcher listener for setting deadline date
        dateSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.e(TAG, viewModel.taskItem.value?.deadlineDate.toString())
                if (viewModel.taskItem.value?.deadlineDate?.isEmpty() == true) {
                    chooseDate(view, dateText)
                }

            } else {
                dateText.visibility = View.INVISIBLE
                viewModel.taskItem.value?.deadlineDate = ""
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (editText != null) {
            viewModel.setText(editText.text.toString().trim())
        }
    }

    /**
     * Open a [DatePickerDialog] and set date which were chosen
     */
    private fun chooseDate(view: View, dateText: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            view.context,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())
                val monthName = dateFormat.format(selectedDate.time).lowercase()
                viewModel.setDeadline("$dayOfMonth $monthName $year")
                dateText.visibility = View.VISIBLE
                dateText.text = "$dayOfMonth $monthName $year"
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    /**
     * Save [TodoItem] and navigate to [TaskListFragment]
     */
    private fun saveTask() {
        // Add text from editText to todoItem
        viewModel.setText(editText.text.toString())
        viewModel.saveTodoItem()
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
    }

    /**
     * Delete current information and navigate to [TaskListFragment]
     */
    private fun deleteTask() {
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
        viewModel.deleteTodoItem()
    }

    /**
     * Navigate to [TaskListFragment]
     */
    private fun closeTask() {
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
    }

}