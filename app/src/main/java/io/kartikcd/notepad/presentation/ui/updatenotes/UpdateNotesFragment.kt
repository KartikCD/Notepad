package io.kartikcd.notepad.presentation.ui.updatenotes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import io.kartikcd.notepad.MainActivity
import io.kartikcd.notepad.R
import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.databinding.FragmentUpdateNotesBinding
import io.kartikcd.notepad.presentation.ui.listnotes.ListNotesFragment
import io.kartikcd.notepad.presentation.ui.listnotes.ListNotesFragmentDirections
import io.kartikcd.notepad.presentation.ui.viewmodel.MainActivityViewModel

class UpdateNotesFragment : Fragment() {

    private var _binding: FragmentUpdateNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainActivityViewModel

    private val args by navArgs<UpdateNotesFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("Debug: ${args.currentItemId.toString()}")
        viewModel = (activity as MainActivity).viewModel
        viewModel.getNoteByIdFromLocalDatabase(args.currentItemId)

        viewModel.note.observe({ lifecycle }) {
            binding.apply {
                titleText.setText(it.name.toString())
                bodyText.setText(it.body.toString())
                if (it.priority) {
                    prioritySpinner.setSelection(0)
                } else {
                    prioritySpinner.setSelection(1)
                }
            }
        }
    }

    private fun saveNoteToLocalDatabase() {
        binding.apply {
            val priorityBool = prioritySpinner.selectedItem.toString().equals("High Priority")
            val note = Note(
                id = args.currentItemId,
                name = titleText.text.toString(),
                priority = priorityBool,
                body = bodyText.text.toString()
            )
            viewModel.updateNoteToLocalDatabase(note)
            Snackbar.make(
                requireView(),
                "Notes Updated Successfully.",
                Snackbar.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.gotoListNotesFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_note_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            saveNoteToLocalDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}