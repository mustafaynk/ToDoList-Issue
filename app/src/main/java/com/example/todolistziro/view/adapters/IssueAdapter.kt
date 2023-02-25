package com.example.todolistziro.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistziro.R
import com.example.todolistziro.data.issue.Issue
import com.example.todolistziro.viewmodel.NoteViewModel
import java.util.ArrayList

class IssueAdapter(private val context: Context, private var noteViewModel: NoteViewModel) :
    RecyclerView.Adapter<IssueAdapter.NoteHolder>() {
    private var issues: List<Issue> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): NoteHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.note_item, viewGroup, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(noteHolder: NoteHolder, position: Int) {
        val currentNote = issues[position]
        noteHolder.textViewTitle.text = currentNote.name
        noteHolder.textViewTitle.setTextColor(context.resources.getColor(getColor(currentNote.issuePriority.priority)))
        noteHolder.textViewDescription.text = currentNote.description
        noteHolder.type.setBackgroundResource(getColor(currentNote.issuePriority.priority))
        noteHolder.priority.text = getType(currentNote.issuePriority.priority)
        noteHolder.priority.setCompoundDrawablesWithIntrinsicBounds(
            getDrawable(currentNote.issuePriority.priority),
            0,
            0,
            0
        )

    }

    private fun getColor(priority: Int): Int {
        return when (priority) {
            1 -> R.color.turquoise_green
            2 -> R.color.light_beige
            3 -> R.color.sunset
            else -> R.color.warm_blue
        }
    }

    private fun getDrawable(priority: Int): Int {
        return when (priority) {
            1 -> R.drawable.ic_circle_orange
            2 -> R.drawable.ic_circle_purple
            3 -> R.drawable.ic_circle_blue
            else -> R.drawable.ic_circle_orange
        }
    }

    private fun getType(priority: Int): String {
        return when (priority) {
            0 -> "Work"
            1 -> "Personal"
            2 -> "Health"
            else -> "Work"
        }
    }

    fun setIssues(notes: List<Issue>) {
        this.issues = notes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    fun getIssueAt(position: Int): Int {
        return issues[position].id
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        val priority: TextView = itemView.findViewById(R.id.priority)
        val textViewDescription: TextView = itemView.findViewById(R.id.text_view_description)
        val type: View = itemView.findViewById(R.id.type)
        val close: ImageView = itemView.findViewById(R.id.close)
    }
}
