package com.chunkingz.foodiepal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.chunkingz.foodiepal.adapter.ViewPagerAdapter
import com.chunkingz.foodiepal.fragments.AboutMeFragment
import com.chunkingz.foodiepal.fragments.BlogFragment
import com.chunkingz.foodiepal.fragments.ContactFragment
import com.chunkingz.foodiepal.fragments.MealPlannerFragment
import com.chunkingz.foodiepal.fragments.RecipeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val fragments = listOf(
            RecipeFragment(),
            MealPlannerFragment(),
            BlogFragment(),
            ContactFragment(),
            AboutMeFragment()
        )

        val pagerAdapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Recipes"
                1 -> "Meal Planner"
                2 -> "Blog"
                3 -> "Contact"
                4 -> "About me"
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }.attach()

        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val newPosition = when (menuItem.itemId) {
                R.id.nav_recipes -> 0
                R.id.nav_meal_planner -> 1
                R.id.nav_blog -> 2
                else -> -1
            }

            if (newPosition != -1 && newPosition < (viewPager.adapter?.itemCount ?: 0)) {
                viewPager.currentItem = newPosition
            }
            true
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position < bottomNavigationView.menu.size()) {
                    bottomNavigationView.menu.getItem(position).isChecked = true
                }
            }
        })

    }
}