package com.example.todolistziro.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistziro.view.adapters.NoteAdapter
import com.example.todolistziro.viewmodel.NoteViewModel
import com.example.todolistziro.R
import com.example.todolistziro.architecture.App
import com.example.todolistziro.architecture.viewBinding
import com.example.todolistziro.architecture.viewModel
import com.example.todolistziro.databinding.FragmentAllNotesBinding


class AllNotes : BaseFragment() {

    private val noteViewModel by viewModel(::initViewModel)

    private val binding by viewBinding(FragmentAllNotesBinding::bind)
    lateinit var recyclerView: RecyclerView
    private lateinit var emptyLayout: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_all_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        emptyLayout = binding.emptyLayout
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        val adapter = NoteAdapter(requireActivity(), noteViewModel)
        recyclerView.adapter = adapter

        noteViewModel.allNotes.observe(
            viewLifecycleOwner
        ) { notes -> // Update recycler view
            if (notes.isEmpty()) {
                recyclerView.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                emptyLayout.visibility = View.GONE
                adapter.setNotes(notes)
            }
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) =
                noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))

        }).attachToRecyclerView(recyclerView)
    }

    private fun initViewModel() = NoteViewModel(App())

}