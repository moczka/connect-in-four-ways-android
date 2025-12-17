package com.example.connectfourways

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.findNavController
import com.example.connectfourways.databinding.FragmentHomepageBinding
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null
    private var param1: String? = null
    private var param2: String? = null

    private val binding
        get() = checkNotNull(_binding) {
            "binding should not be null"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomepageBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add click handlers for the navigation buttons
        binding.apply {
            homepagePlayBtn.setOnClickListener { view ->
                viewLifecycleOwner.lifecycleScope.launch {
                    findNavController().navigate(
                        HomepageFragmentDirections.startGameplay()
                    )
                }
            }
            homepageScoresBtn.setOnClickListener { view ->
                viewLifecycleOwner.lifecycleScope.launch {
                    findNavController().navigate(
                        HomepageFragmentDirections.viewScores()
                    )
                }
            }
            homepageSettingsBtn.setOnClickListener { view ->
                viewLifecycleOwner.lifecycleScope.launch {
                    findNavController().navigate(
                        HomepageFragmentDirections.configureApp()
                    )
                }
            }
        }
    }
}