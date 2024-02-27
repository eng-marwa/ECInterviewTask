package com.marwa.ecinterviewtask.presentation.competitions

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marwa.ecinterviewtask.R
import com.marwa.ecinterviewtask.data.model.Competitions
import com.marwa.ecinterviewtask.databinding.CompetitionRowBinding

/**
 * Adapter for the RecyclerView that displays a list of competitions.
 */
class CompetitionsAdapter(private var itemClicked: OnItemClickListener? = null) :
    RecyclerView.Adapter<CompetitionsAdapter.CompetitionsViewHolder>() {
    private val TAG = "CompetitionsAdapter"
    var currentList: ArrayList<Competitions> = arrayListOf()


    /**
     * ViewHolder for a single competition item in the RecyclerView.
     */
    inner class CompetitionsViewHolder(private var binding: CompetitionRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(competition: Competitions) {
            // Bind the data with the view
            binding.tvCompetitionArea.text = competition.area?.name
            binding.tvCompetitionName.text = competition.name
            try {
                Glide.with(binding.root.context)
                    .load(competition.emblem)
                    .placeholder(R.drawable.ic_launcher_background)
                    .circleCrop()
                    .into(binding.ivCompetitionEmblem)
            } catch (e: Exception) {
                Log.e(TAG, "bindView: ${e.message}")
            }
        }

        fun onEvent(competition: Competitions) {
            binding.root.setOnClickListener {
                // Handle click event
                itemClicked?.onItemClick(competition)
            }
        }
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompetitionsAdapter.CompetitionsViewHolder {
        val binding =
            CompetitionRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetitionsViewHolder(binding)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(
        holder: CompetitionsAdapter.CompetitionsViewHolder,
        position: Int
    ) {
        val competition = currentList[position]
        // Bind the data with the view holder
        holder.bindView(competition)
        // Set the event listener for the view holder
        holder.onEvent(competition)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int {
        return currentList.size
    }

    fun submitList(data: ArrayList<Competitions>?) {
        val oldList = mutableListOf<Competitions>()
        oldList.addAll(currentList)
        val newList = data ?: emptyList()
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallback(oldList, newList))
        currentList.clear()
        currentList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * Callback for calculating the diff between two non-null items in a list.
     */
    class MyDiffUtilCallback(
        private val oldList: List<Competitions>,
        private val newList: List<Competitions>
    ) :
        DiffUtil.Callback() {
        /**
         * Returns the size of the old list.
         */
        override fun getOldListSize(): Int = oldList.size

        /**
         * Returns the size of the new list.
         */
        override fun getNewListSize(): Int = newList.size

        /**
         * Called by the DiffUtil to decide whether two object represent the same Item.
         */
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        /**
         * Called by the DiffUtil to check whether two items have the same data.
         */
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    /**
     * Interface for handling item click events in the RecyclerView.
     */
    interface OnItemClickListener {
        /**
         * Called when an item in the RecyclerView is clicked.
         * @param competition The competition object associated with the clicked item.
         */
        fun onItemClick(competition: Competitions)
    }
}