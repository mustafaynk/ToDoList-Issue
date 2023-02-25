package com.example.todolistziro.data.issue

import java.util.*

data class Issue(
    val id: Int = 0,
    val name: String = String(),
    val description: String = String(),
    val issueType: IssueType = IssueType.BUG,
    val projectId: Long = 0L,
    val processId: Long = 0L,
    val stateId: Long = 0L,
    val issuePriority: IssuePriority = IssuePriority.LOW,
    val assignedPersonId: Long = 0L,
    val deleted: Boolean = false,
    val deadline: Date = Date(),
    val creatorPersonId: Long = 0L,
    val organizationId: Long = 0L
)
