package com.marwa.ecinterviewtask.presentation.competitions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.marwa.ecinterviewtask.R
import com.marwa.ecinterviewtask.data.model.Area
import com.marwa.ecinterviewtask.data.model.Competitions
import com.marwa.ecinterviewtask.data.model.CurrentSeason
import com.marwa.ecinterviewtask.data.model.Winner
import com.marwa.ecinterviewtask.databinding.FragmentCompetitionDetailsBinding
import com.marwa.ecinterviewtask.utils.glide.GlideApp

/**
 * This is a Fragment class that displays the details of a competition.
 *
 * @property TAG A string used for logging.
 * @property competition The competition data to be displayed.
 * @property _binding The binding for this fragment's view.
 * @property binding A non-null getter for the binding.
 */
class CompetitionDetailsFragment : Fragment() {
    private val TAG = "CompetitionDetailsFragment"

    private var competition: Competitions? = null

    // Binding for the fragment's view
    private var _binding: FragmentCompetitionDetailsBinding? = null

    // Non-null getter for the binding
    private val binding get() = _binding!!

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCompetitionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle) has returned, but before any saved state has been restored in to the view.
     *
     * @param view The View returned by onCreateView(LayoutInflater, ViewGroup, Bundle).
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            competition = it.getParcelable<Competitions>("competition")
        }
        initViews()
    }

    /**
     * Initializes the views with the competition data.
     */
    private fun initViews() {
        competition?.let {
            viewDescriptionSection(it)
            viewAreaSection(it.area)
            viewCurrentSeasonSection(it.currentSeason)
            viewWinner(it.currentSeason?.winner)
            binding.tvNumberOfAvailableSeasonsData.text = "${it.numberOfAvailableSeasons}"
            binding.tvLastUpdatedDate.text = it.lastUpdated
        }
    }

    /**
     * Called when the fragment is no longer in use. This is called after onStop() and before onDetach().
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * This function is responsible for setting up the view for the competition description section.
     * It sets the competition name, type, and plan. If an emblem is available, it loads the emblem
     * into an ImageView using Glide. If the emblem is not available, it hides the ImageView.
     *
     * @param competitions The competition data to be displayed.
     */
    private fun viewDescriptionSection(competitions: Competitions) {
        binding.tvCompetitionName.text = competitions.name
        binding.competitionDescriptionLayout.tvCompetitionType.text =
            "${getString(R.string.competition_type)} ${competitions.type}"
        binding.competitionDescriptionLayout.tvCompetitionPlan.text =
            "${getString(R.string.competition_plan)} ${competitions.plan}"
        if (competitions.emblem != null) {
            try {
                Glide.with(requireContext()).load(competitions.emblem)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.competitionDescriptionLayout.ivCompetitionEmblem)
            } catch (e: Exception) {
                Log.e(TAG, "initViews: ${e.message}")
            }
        } else {
            binding.competitionDescriptionLayout.ivCompetitionEmblem.visibility = View.GONE
        }
    }

    /**
     * This function is responsible for setting up the view for the competition area section.
     * It sets the area name and code. If a flag is available, it loads the flag into an ImageView
     * using Glide. If the flag is not available, it hides the ImageView.
     *
     * @param area The area data to be displayed.
     */
    private fun viewAreaSection(area: Area?) {
        binding.competitionAreaLayout.tvCompetitionAreaName.text =
            "${getString(R.string.competition_area)} ${area?.name}"
        binding.competitionAreaLayout.tvCompetitionCode.text =
            "${getString(R.string.competition_code)} ${area?.code}"
        if (area?.flag != null) {
            try {
                GlideApp.with(this)
                    .load(area.flag)
                    .into(binding.competitionAreaLayout.ivCompetitionAreaFlag)
            } catch (e: Exception) {
                Log.e(TAG, "initViews: ${e.message}")
            }
        } else {
            binding.competitionAreaLayout.ivCompetitionAreaFlag.visibility = View.GONE
        }
    }

    /**
     * This function is responsible for setting up the view for the current season section.
     * It sets the start date, end date, and current match day.
     *
     * @param currentSeason The current season data to be displayed.
     */
    private fun viewCurrentSeasonSection(currentSeason: CurrentSeason?) {
        binding.competitionCurrentSeasonLayout.tvCurrentSeasonStartDate.text =
            "${getString(R.string.current_season_start_date)} ${currentSeason?.startDate}"
        binding.competitionCurrentSeasonLayout.tvCurrentSeasonEndDate.text =
            "${getString(R.string.current_season_end_date)} ${currentSeason?.endDate}"
        binding.competitionCurrentSeasonLayout.tvCurrentMatchDay.text =
            "${getString(R.string.current_match_day)} ${currentSeason?.currentMatchday}"

    }

    /**
     * This function is responsible for setting up the view for the winner section.
     * It sets the winner name and website. If a crest is available, it loads the crest into an ImageView
     * using Glide. If the crest is not available, it hides the ImageView. If there is no winner,
     * it hides the entire winner section.
     *
     * @param winner The winner data to be displayed.
     */
    private fun viewWinner(winner: Winner?) {
        if (winner != null) {
            binding.competitionWinnerLayout.tvWinnerName.text = winner?.name
            binding.competitionWinnerLayout.tvWinnerWebSite.text =
                winner?.website
            if (winner?.crest != null) {
                try {
                    GlideApp.with(this)
                        .load(winner?.crest)
                        .into(binding.competitionWinnerLayout.ivWinnerCrest)
                } catch (e: Exception) {
                    Log.e(TAG, "initViews: ${e.message}")
                }
            } else {
                binding.competitionWinnerLayout.ivWinnerCrest.visibility = View.GONE
            }
        } else {
            binding.competitionWinnerLayout.root.visibility = View.GONE
            binding.tvWinner.visibility = View.GONE
        }
    }
}