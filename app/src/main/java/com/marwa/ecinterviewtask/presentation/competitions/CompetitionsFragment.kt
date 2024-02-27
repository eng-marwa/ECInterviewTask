package com.marwa.ecinterviewtask.presentation.competitions

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwa.ecinterviewtask.R
import com.marwa.ecinterviewtask.base.BaseException
import com.marwa.ecinterviewtask.base.BaseViewModel
import com.marwa.ecinterviewtask.data.model.Competitions
import com.marwa.ecinterviewtask.databinding.FragmentCompetitionsBinding
import com.marwa.ecinterviewtask.presentation.ItemActivity
import com.marwa.ecinterviewtask.utils.extensions.showToast
import com.marwa.ecinterviewtask.utils.network.NetworkUtils
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that displays a list of competitions.
 */
class CompetitionsFragment : Fragment(), CompetitionsAdapter.OnItemClickListener {
    private lateinit var competitionsAdapter: CompetitionsAdapter

    // Binding for the fragment's view
    private var _binding: FragmentCompetitionsBinding? = null

    // Non-null getter for the binding
    private val binding get() = _binding!!

    // ViewModel for the fragment
    private val viewModel: CompetitionViewModel by viewModel<CompetitionViewModel>()

    /**
     * Called to have the fragment instantiate its user interface view.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCompetitionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Called immediately after onCreateView() has returned, but before any saved state has been restored in to the view.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    /**
     * Observes the ViewModel for changes in the fetched competitions state.
     */
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.fetchedCompetitionsState.collect {
                when (it) {
                    is BaseViewModel.ViewState.Loaded -> handleResponseSuccess(it.data?.competitions)
                    is BaseViewModel.ViewState.Failed -> handleResponseError(it.throwable)
                    else -> {}
                }
            }
        }
    }

    /**
     * Handles a successful response from the ViewModel by submitting the list of competitions to the RecyclerView's adapter.
     */
    private fun handleResponseSuccess(competitions: ArrayList<Competitions>?) {
        binding.progressBar.hide()
        competitions?.let {
            if (::competitionsAdapter.isInitialized)
                competitionsAdapter.submitList(
                    competitions
                )
        }
    }

    /**
     * Handles an error response from the ViewModel by showing a toast with the error message.
     */
    private fun handleResponseError(throwable: BaseException?) {
        binding.progressBar.hide()
        showToast(throwable?.getMessage() ?: "failed to fetch competitions")
    }

    /**
     * Initializes the views in the fragment.
     */
    private fun initViews() {
        // Set up the RecyclerView
        setupRecyclerView()
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            // Fetch the competitions
            viewModel.fetchCompetition()
        } else {
            showToast(getString(R.string.no_internet_connection))
            // Show cached competitions if available
            viewModel.getCachedCompetitionsResponse()?.let {
                handleResponseSuccess(it.competitions)
            }
        }


    }

    /**
     * Sets up the RecyclerView by setting its layout manager and adapter.
     */
    private fun setupRecyclerView() {
        binding.competitionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        competitionsAdapter = CompetitionsAdapter(this)
        binding.competitionsRecyclerView.adapter = competitionsAdapter
    }

    /**
     * Called when the fragment is no longer in use. This is the place to de-reference all of the expensive resources.
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Handles the click event on a competition item in the RecyclerView.
     * This method is part of the OnItemClickListener interface that the fragment implements.
     *
     * @param competition The competition object associated with the clicked item.
     */
    override fun onItemClick(competition: Competitions) {
        startActivity(Intent(requireContext(), ItemActivity::class.java).apply {
            putExtra("fragment", "CompetitionsDetailsFragment")
            putExtras(bundleOf("competition" to competition))
        })
    }
}