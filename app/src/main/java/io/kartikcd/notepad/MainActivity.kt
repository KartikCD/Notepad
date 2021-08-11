package io.kartikcd.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import io.kartikcd.notepad.databinding.ActivityMainBinding
import io.kartikcd.notepad.presentation.adapter.NotesListAdapter
import io.kartikcd.notepad.presentation.ui.viewmodel.MainActivityViewModel
import io.kartikcd.notepad.presentation.ui.viewmodel.MainActivityViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    @Inject
    lateinit var factory: MainActivityViewModelFactory
    @Inject
    lateinit var adapter: NotesListAdapter
    lateinit var viewModel: MainActivityViewModel

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}