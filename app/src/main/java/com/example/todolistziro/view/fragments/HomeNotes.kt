package com.example.todolistziro.view.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import com.example.todolistziro.R
import com.example.todolistziro.architecture.App
import com.example.todolistziro.architecture.viewBinding
import com.example.todolistziro.architecture.viewModel
import com.example.todolistziro.databinding.FragmentHomeNotesBinding
import com.example.todolistziro.viewmodel.NoteViewModel


class HomeNotes : BaseFragment() {

    private val noteViewModel by viewModel(::initViewModel)
    private val binding by viewBinding(FragmentHomeNotesBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home_notes, container, false)

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animation()
        getData()
    }

    private fun getData() {
        noteViewModel.allNotes.observe(viewLifecycleOwner
        ) { notes ->
            var personalCounter = 0
            var workCounter = 0
            var healthCounter = 0

            for (i in notes) {
                when (i.priority) {
                    0 -> workCounter++
                    1 -> personalCounter++
                    2 -> healthCounter++
                }
            }
            binding.personalValueTextview.text = personalCounter.toString()
            binding.workValueTextview.text = workCounter.toString()
            binding.healthValueTextview.text = healthCounter.toString()
        }
    }

    private fun animation() {
        val animation = ObjectAnimator.ofInt(
           binding.onboardingActivityProgressBar,
            "progress",
            0,
            70
        ) // see this max value coming back here, we animate towards that value
        animation.duration = 3000 // in milliseconds
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }

    private fun initViewModel() = NoteViewModel(App())

}