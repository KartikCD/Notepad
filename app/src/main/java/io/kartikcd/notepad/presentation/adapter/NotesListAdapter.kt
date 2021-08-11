package io.kartikcd.notepad.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.kartikcd.notepad.R
import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.databinding.LayoutListNotesBinding

class NotesListAdapter: ListAdapter<Note, NotesListAdapter.NoteListViewHolder>(NoteDiffCallback) {

    inner class NoteListViewHolder(
        val binding: LayoutListNotesBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.apply {
                titleText.text = note.name
                bodyText.text = note.body
                if (note.priority) {
                    priorityIndicator.setCardBackgroundColor(Color.RED)
                } else {
                    priorityIndicator.setCardBackgroundColor(Color.GREEN)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding = LayoutListNotesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

object NoteDiffCallback: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

}