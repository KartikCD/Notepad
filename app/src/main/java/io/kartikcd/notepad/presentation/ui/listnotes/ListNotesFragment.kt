package io.kartikcd.notepad.presentation.ui.listnotes

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.kartikcd.notepad.MainActivity
import io.kartikcd.notepad.R
import io.kartikcd.notepad.databinding.FragmentListNotesBinding
import io.kartikcd.notepad.presentation.adapter.NotesListAdapter
import io.kartikcd.notepad.presentation.ui.viewmodel.MainActivityViewModel
import io.kartikcd.notepad.presentation.util.observeOnce
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class ListNotesFragment : Fragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var noteListAdapter: NotesListAdapter
    private var listNum: Int = 0

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

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Yet to Implement!!")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = noteListAdapter.currentList[position]
                viewModel.deleteNoteFromLocalDatabase(note)
                Snackbar.make(view, "Delete successfully.", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo") {
                            viewModel.saveNotesToLocalDatabase(note)
                        }
                    }
                    .show()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.noteListRecyclerView)
        }

        fetchAllNotes()
        binding.buttonAddNotes.setOnClickListener {
            findNavController().navigate(R.id.gotoAddNotesFragment)
        }
    }

    private fun fetchAllNotes() {
        lifecycleScope.launch {
            viewModel.fetchNotesFromLocalDatabase().observe({ lifecycle }) {
                listNum = 1
                noteListAdapter.submitList(it)
            }
        }
    }

    private fun fetchHighPriorityNotes() {
        lifecycleScope.launch {
            viewModel.fetchHighPriorityNotesFromLocalDatabase().observe({ lifecycle }) {
                listNum = 2
                noteListAdapter.submitList(it)
            }
        }
    }

    private fun fetchLowPriorityNotes() {
        lifecycleScope.launch {
            viewModel.fetchLowPriorityNotesFromLocalDatabase().observe({ lifecycle }) {
                listNum = 3
                noteListAdapter.submitList(it)
            }
        }
    }

    private fun fetchSearchNotes(query: String) {
        val searchQuery = "%$query%"
        viewModel.fetchSearchNotesFromLocalDatabase(searchQuery).observeOnce({ lifecycle}) {
            it?.let {
                noteListAdapter.submitList(it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.noteListRecyclerView.apply {
            adapter = noteListAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            scheduleLayoutAnimation()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_note_list, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        println("Debug: here...")
        if (query != null) {
            fetchSearchNotes(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        println("Debug: here...")
        if (newText != null) {
            fetchSearchNotes(newText)
        }
        return true
    }

    override fun onClose(): Boolean {
        if (listNum == 1) {
            fetchAllNotes()
        } else if (listNum == 2) {
            fetchHighPriorityNotes()
        } else if (listNum == 3) {
            fetchLowPriorityNotes()
        }
        return true
    }

}