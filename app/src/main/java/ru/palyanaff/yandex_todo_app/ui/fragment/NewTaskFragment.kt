package ru.palyanaff.yandex_todo_app.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import org.w3c.dom.Text
import ru.palyanaff.yandex_todo_app.R
import ru.palyanaff.yandex_todo_app.data.model.PriorityStatus
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [NewTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTaskFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize all views on screen
        val view: View = inflater.inflate(R.layout.fragment_new_task, container, false)
        val saveText = view.findViewById<TextView>(R.id.save_task_text)
        val deleteButton = view.findViewById<ImageButton>(R.id.delete_image_button)
        val deleteText = view.findViewById<TextView>(R.id.delete_text)
        val dateText = view.findViewById<TextView>(R.id.date_text)
        val closeButton  = view.findViewById<ImageButton>(R.id.close_image_button)
        val dateSwitch = view.findViewById<Switch>(R.id.date_switch)
        val prioritySpinner = view.findViewById<Spinner>(R.id.priority_spinner)
        val priorityText = view.findViewById<TextView>(R.id.priority_text)

        // Initialization list of enum elements
        val priorityValues = PriorityStatus.values().toList()
        // Initialization adapter for connection enum elements with Spinner
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, priorityValues)
        // Set layout interface
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = adapter
        // Set Listener to track the user choice
        prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Set text TODO: изменить spinner lable(хз) чтоб на экране остался только TextView
                val selectedEnumValue = priorityValues[position]
                priorityText.text = selectedEnumValue.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Действия при отсутствии выбранного элемента
            }
        }

        saveText.setOnClickListener{ saveTask() }
        deleteButton.setOnClickListener { deleteTask() }
        deleteText.setOnClickListener { deleteTask() }
        closeButton.setOnClickListener { closeTask() }
        // Set switcher listener for setting deadline date
        dateSwitch.setOnCheckedChangeListener  { _, isChecked ->
            if(isChecked){
                chooseDate(view, dateText)

            }else{
                dateText.visibility = View.INVISIBLE
            }
        }

        return view
    }

    /**
     * Open a DatePickerDialog and set date which were chosen
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
                // TODO: set deadline date in TodoItem

                dateText.text = "$dayOfMonth $monthName $year"
                dateText.visibility = View.VISIBLE
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    /**
     * Save [TodoItem] and navigate to [TaskListFragment]
     */
    private fun saveTask(){
        // TODO: save TodoItem
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
    }

    /**
     * Delete current information and navigate to [TaskListFragment]
     */
    private fun deleteTask(){
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
    }

    /**
     * Navigate to [TaskListFragment]
     */
    private fun closeTask(){
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
    }

}