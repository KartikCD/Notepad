package io.kartikcd.notepad.presentation.ui.listnotes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.kartikcd.notepad.R
import io.kartikcd.notepad.databinding.FragmentListNotesBinding

class ListNotesFragment : Fragment() {

    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!

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
        binding.buttonAddNotes.setOnClickListener {
            findNavController().navigate(R.id.gotoAddNotesFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_note_list, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}