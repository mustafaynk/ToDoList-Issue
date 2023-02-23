package com.example.todolistziro.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.todolistziro.R
import com.example.todolistziro.architecture.App
import com.example.todolistziro.architecture.viewBinding
import com.example.todolistziro.architecture.viewModel
import com.example.todolistziro.data.Note
import com.example.todolistziro.databinding.FragmentAddNotesBinding
import com.example.todolistziro.utils.Constants
import com.example.todolistziro.viewmodel.NoteViewModel

class AddNotes : BaseFragment() {

    var priority: Int = 0
    private val noteViewModel by viewModel(::initViewModel)
    private val binding by viewBinding(FragmentAddNotesBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.work.setOnClickListener { priority = 0 }
        binding.personal.setOnClickListener { priority = 1 }
        binding.health.setOnClickListener { priority = 2 }
        binding.addTask.setOnClickListener { saveNote() }
    }

    private fun saveNote() {
        val title = binding.editTextTitle.text.toString().trim { it <= ' ' }
        val description = binding.editTextDescription.text.toString().trim { it <= ' ' }

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(
                this.activity, "Please inssert a title and description",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val data = Intent()
        data.putExtra(Constants.EXTRA_TITLE, title)
        data.putExtra(Constants.EXTRA_DESCRIPTION, description)
        data.putExtra(Constants.EXTRA_PRIORITY, priority)

        val note = Note(title, description, priority)
        noteViewModel.insert(note)

        findNavController().navigate(R.id.destination_home)
    }

    private fun initViewModel() = NoteViewModel(App())

}