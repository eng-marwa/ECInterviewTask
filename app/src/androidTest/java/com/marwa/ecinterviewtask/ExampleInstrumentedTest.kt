package com.marwa.ecinterviewtask

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.marwa.ecinterviewtask.data.model.Area
import com.marwa.ecinterviewtask.data.model.Competitions
import com.marwa.ecinterviewtask.data.model.CurrentSeason
import com.marwa.ecinterviewtask.presentation.competitions.CompetitionsAdapter
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.marwa.ecinterviewtask", appContext.packageName)
    }
    /**
     * Test the item count of the adapter
     */
    @Test
    fun testItemCount() {
        val competitions = arrayListOf<Competitions>(
            Competitions(
                name = "Africa",
                currentSeason = CurrentSeason(startDate = "2024-02-26")
            ),
            Competitions(name = "Europa"),
            Competitions(name = "Asia", area = Area(name = "Qatar"))
        )
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val recyclerView: RecyclerView = RecyclerView(appContext)
        val adapter = CompetitionsAdapter()
        recyclerView.adapter = adapter
        adapter.submitList(arrayListOf())
        adapter.submitList(competitions)
        assertEquals(competitions.size, adapter.itemCount)
    }
    /**
     * Test the view holder binding
     */

    @Test
    fun testViewHolderBinding() {
        val expectedCompetitions = arrayListOf<Competitions>(Competitions(
            id = 2013,
            area = Area(
                id = 2032,
                name = "Brazil",
                code = "BRA",
                flag = "https://crests.football-data.org/764.svg"
            ),
            name = "Campeonato Brasileiro Série A",
            code = "BSA",
            type = "LEAGUE",
            emblem = "https://crests.football-data.org/764.svg",
            plan = "TIER_ONE",
            currentSeason = CurrentSeason(
                id = 1557,
                startDate = "2023-04-15",
                endDate = "2023-12-03",
                currentMatchday = 37,
                winner = null
            ),
            numberOfAvailableSeasons = 7,
            lastUpdated = "2021-07-20T18:42:17Z"
        ))
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val recyclerView: RecyclerView = RecyclerView(appContext)
        val adapter = CompetitionsAdapter()
        recyclerView.adapter = adapter
        adapter.submitList(expectedCompetitions)
        assertEquals(1, adapter.itemCount)
        val actualCompetitions = adapter.currentList[0]
        assertEquals(expectedCompetitions[0], actualCompetitions)

    }
    /**
     * Test the item click
     */

    @Test
    fun testItemClick() {
        val expectedCompetitions = arrayListOf<Competitions>(Competitions(
            id = 2013,
            area = Area(
                id = 2032,
                name = "Brazil",
                code = "BRA",
                flag = "https://crests.football-data.org/764.svg"
            ),
            name = "Campeonato Brasileiro Série A",
            code = "BSA",
            type = "LEAGUE",
            emblem = "https://crests.football-data.org/764.svg",
            plan = "TIER_ONE",
            currentSeason = CurrentSeason(
                id = 1557,
                startDate = "2023-04-15",
                endDate = "2023-12-03",
                currentMatchday = 37,
                winner = null
            ),
            numberOfAvailableSeasons = 7,
            lastUpdated = "2021-07-20T18:42:17Z"
        ))
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val recyclerView: RecyclerView = RecyclerView(appContext)
        val competitionsListener = CompetitionsOnItemClickListener()
        val adapter = CompetitionsAdapter(competitionsListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(appContext)
        adapter.submitList(expectedCompetitions)
        val viewHolder = adapter.onCreateViewHolder(recyclerView, adapter.getItemViewType(0))
        adapter.onBindViewHolder(viewHolder, 0)
        viewHolder.itemView.performClick()
        assertEquals(expectedCompetitions[0], competitionsListener.clickedItem)

    }
    class CompetitionsOnItemClickListener : CompetitionsAdapter.OnItemClickListener {
        var clickedItem: Competitions? = null

        override fun onItemClick(item: Competitions) {
            clickedItem = item
        }
    }
}