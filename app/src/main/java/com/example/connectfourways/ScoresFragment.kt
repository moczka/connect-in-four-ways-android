/*
    Author:     Javier Steven Guerrero
    Course:     CSI 357 Android Development
    Date:       Dec 16, 2025

 */
package com.example.connectfourways

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.connectfourways.databinding.FragmentScoresBinding
import kotlinx.coroutines.launch

class ScoresFragment : Fragment() {
    private var _binding: FragmentScoresBinding? = null

    private val scoresViewModel: ScoresViewModel by viewModels()

    private val binding
        get() = checkNotNull(_binding) {
            "binding should not be null"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoresBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                scoresViewModel.gameList.collect { games ->
                    // Update the UI
                    binding.recyclerView.adapter = GameAdapter(games)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}