package io.kartikcd.notepad.presentation.ui.addnotes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import io.kartikcd.notepad.R
import io.kartikcd.notepad.databinding.FragmentAddNotesBinding

class AddNotesFragment : Fragment() {

    private var _binding: FragmentAddNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextWatcherListener()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_note_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            println("Debug: Button clicked.")
            saveButtonClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveButtonClicked() {
        if (isValidate()) {
            println("Debug: Button clicked.")
        }
    }

    private fun setupTextWatcherListener() {
        binding.apply {
            titleText.addTextChangedListener(AddNotesFieldValidation(binding.titleText))
            bodyText.addTextChangedListener(AddNotesFieldValidation(binding.bodyText))
        }
    }

    private fun isValidate(): Boolean =
        titleValidation() && bodyValidation()

    inner class AddNotesFieldValidation(private val view: View): TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            when (view.id) {
                R.id.titleText -> {
                    titleValidation()
                }
                R.id.bodyText -> {
                    bodyValidation()
                }
            }
        }
    }

    private fun titleValidation(): Boolean {
        if (binding.titleText.text.toString().trim().isEmpty()) {
            binding.titleTextInputLayout.error = "Title should not be empty!"
            binding.titleText.requestFocus()
            return false
        }
        binding.titleTextInputLayout.isErrorEnabled = false
        return true
    }

    private fun bodyValidation(): Boolean {
        if (binding.bodyText.text.toString().trim().isEmpty()) {
            binding.bodyTextInputLayout.error = "Title should not be empty!"
            binding.bodyText.requestFocus()
            return false
        }
        binding.bodyTextInputLayout.isErrorEnabled = false
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}