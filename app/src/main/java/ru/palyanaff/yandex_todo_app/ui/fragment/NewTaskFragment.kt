package ru.palyanaff.yandex_todo_app.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import ru.palyanaff.yandex_todo_app.R
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
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_new_task, container, false)
        val saveText = view.findViewById<TextView>(R.id.save_task_text)
        val deleteButton = view.findViewById<ImageButton>(R.id.delete_image_button)
        val deleteText = view.findViewById<TextView>(R.id.delete_text)
        val dateText = view.findViewById<TextView>(R.id.date_text)
        val closeButton  = view.findViewById<ImageButton>(R.id.close_image_button)
        val dateSwitch = view.findViewById<Switch>(R.id.date_switch)

        saveText.setOnClickListener{ saveTask() }
        deleteButton.setOnClickListener { deleteTask() }
        deleteText.setOnClickListener { deleteTask() }
        closeButton.setOnClickListener { closeTask() }
        dateSwitch.setOnCheckedChangeListener  { _, isChecked ->
            if(isChecked){
                chooseDate(view, dateText)

            }else{
                dateText.visibility = View.INVISIBLE
            }
        }

        return view
    }

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

    private fun saveTask(){
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
    }

    private fun deleteTask(){
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
    }

    private fun closeTask(){
        findNavController().navigate(R.id.action_newTaskFragment_to_taskListFragment)
    }

}