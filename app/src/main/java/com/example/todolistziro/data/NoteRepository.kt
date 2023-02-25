package com.example.todolistziro.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todolistziro.architecture.App
import com.example.todolistziro.architecture.apis.IssueApi
import com.example.todolistziro.data.issue.Issue
import retrofit2.HttpException
import javax.inject.Inject

class NoteRepository(private val token: String) {

    val allIssue: MutableLiveData<List<Issue>> = MutableLiveData()
    val hasError: MutableLiveData<Boolean> = MutableLiveData(false)

    @Inject
    lateinit var issueApi: IssueApi

    init {
        App.instance.apiComponent.inject(this)
    }

    suspend fun getAllNotes() {
        try {
            val response = issueApi.getAllIssue("Bearer $token")
            allIssue.postValue(response)
        } catch (e:HttpException) {
            Log.e("Issue Api", "${e.message()} Invalid Token: $token")
            hasError.postValue(true)
        }

    }

}
