package com.example.todolistziro.architecture.apis

import com.example.todolistziro.data.issue.Issue
import retrofit2.http.GET
import retrofit2.http.Header

interface IssueApi {

    @GET("issue")
    suspend fun getAllIssue(@Header("Authorization") token: String): List<Issue>

}
