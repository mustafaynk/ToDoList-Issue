package com.example.todolistziro.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistziro.view.adapters.IssueAdapter
import com.example.todolistziro.viewmodel.NoteViewModel
import com.example.todolistziro.R
import com.example.todolistziro.architecture.extensions.getStringDataToLocal
import com.example.todolistziro.architecture.network.Utils
import com.example.todolistziro.architecture.viewBinding
import com.example.todolistziro.architecture.viewModel
import com.example.todolistziro.databinding.FragmentAllNotesBinding
import com.example.todolistziro.view.activities.LoginActivity


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
        val issueAdapter = IssueAdapter(requireActivity(), noteViewModel)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = issueAdapter
        }

        noteViewModel.allIssue.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                    issueAdapter.setIssues(it)
                }
            }
        }

        noteViewModel.hasError.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    requireActivity().startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
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

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        }).attachToRecyclerView(recyclerView)
    }

    private fun initViewModel() = NoteViewModel(getStringDataToLocal(Utils.LocalDataKeys.TOKEN))

}