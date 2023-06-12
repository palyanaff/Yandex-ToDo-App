package ru.palyanaff.yandex_todo_app.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import ru.palyanaff.yandex_todo_app.R

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
        val closeButton  = view.findViewById<ImageButton>(R.id.close_image_button)

        saveText.setOnClickListener{ saveTask() }
        deleteButton.setOnClickListener { deleteTask() }
        deleteText.setOnClickListener { deleteTask() }
        closeButton.setOnClickListener { closeTask() }

        return view
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