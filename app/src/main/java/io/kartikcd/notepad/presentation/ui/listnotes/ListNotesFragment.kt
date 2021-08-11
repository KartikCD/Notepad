package io.kartikcd.notepad.presentation.ui.listnotes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.kartikcd.notepad.MainActivity
import io.kartikcd.notepad.R
import io.kartikcd.notepad.databinding.FragmentListNotesBinding
import io.kartikcd.notepad.presentation.adapter.NotesListAdapter
import io.kartikcd.notepad.presentation.ui.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

class ListNotesFragment : Fragment() {

    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var noteListAdapter: NotesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        noteListAdapter = (activity as MainActivity).adapter
        initRecyclerView()
        fetchAllNotes()
        binding.buttonAddNotes.setOnClickListener {
            findNavController().navigate(R.id.gotoAddNotesFragment)
        }
    }

    private fun fetchAllNotes() {
        lifecycleScope.launch {
            viewModel.fetchNotesFromLocalDatabase().observe({ lifecycle }) {
                noteListAdapter.submitList(it)
            }
        }
    }

    private fun fetchHighPriorityNotes() {
        lifecycleScope.launch {
            viewModel.fetchHighPriorityNotesFromLocalDatabase().observe({ lifecycle }) {
                noteListAdapter.submitList(it)
            }
        }
    }

    private fun fetchLowPriorityNotes() {
        lifecycleScope.launch {
            viewModel.fetchLowPriorityNotesFromLocalDatabase().observe({ lifecycle }) {
                noteListAdapter.submitList(it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.noteListRecyclerView.apply {
            adapter = noteListAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_note_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_highpriority -> {
                fetchHighPriorityNotes()
            }
            R.id.menu_lowpriority -> {
                fetchLowPriorityNotes()
            }
            R.id.menu_all -> {
                fetchAllNotes()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}