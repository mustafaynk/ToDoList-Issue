package com.example.todolistziro.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.todolistziro.data.NoteRepository
import kotlinx.coroutines.launch


class NoteViewModel(token: String) : BaseViewModel() {

    private val repository: NoteRepository = NoteRepository(token = token)
    val allIssue = repository.allIssue
    val hasError = repository.hasError

    fun getAllIssue() {
        viewModelScope.launch {
            repository.getAllNotes()
        }
    }


}