package com.carlosquezada.recipe_dashboard.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.carlosquezada.core.EMPTY_STRING
import com.carlosquezada.recipe_dashboard.R
import com.carlosquezada.recipe_dashboard.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val dashboardViewModel: DashboardViewModel by viewModel()

    private lateinit var searchView: SearchView

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var menuToolbar: Menu? = null

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                dashboardViewModel.setQueryRecipe(it)
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let {
                dashboardViewModel.setQueryRecipe(it)
            }
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        observeViewModels()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupAppBarConfiguration()
    }

    private fun observeViewModels() {
        dashboardViewModel.apply {
            isDashboardPresent.observe(
                this@MainActivity,
                ::visibilityMenuItems
            )
            temporalNameOfRecipe.observe(
                this@MainActivity,
                ::setupToolbarName
            )
            imageUrlTemporal.observe(
                this@MainActivity,
                ::replaceImageToolbar
            )
        }
    }


    private fun replaceImageToolbar(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .into(binding.imageToolbar)
    }

    private fun restoreImageToolbar() {
        Glide.with(this)
            .load(R.drawable.recipe_3)
            .fitCenter()
            .into(binding.imageToolbar)
    }

    private fun setupToolbarName(toolbarTitle: String) {
        binding.textRecipe.text = toolbarTitle
    }

    private fun setupAppBarConfiguration() {
        val navController = findNavController(R.id.container)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.apply {
            setupWithNavController(navController, appBarConfiguration)
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        this.menuToolbar = menu
        val menuItem = menu?.findItem(R.id.nav_search)
        searchView = menuItem?.actionView as SearchView
        searchView.let {
            it.queryHint = getString(R.string.type_recite_search_hint)
            it.setOnQueryTextListener(queryTextListener)

            it.setOnCloseListener {
                menuItemsSeVisibility(true)
                return@setOnCloseListener false
            }

            it.setOnSearchClickListener {
                menuItemsSeVisibility(false)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        when (item.title) {
            getString(R.string.search) -> {
                menuItemsSeVisibility(false)
            }
            getString(R.string.name_of_recipe), getString(R.string.ingredients) -> {
                dashboardViewModel.setIsNameOfRecipeFilterSelected(item.title == getString(R.string.name_of_recipe))
                item.isChecked = !item.isChecked
                menuItemsSeVisibility(true)
                searchView.setQuery(EMPTY_STRING, true)
            }
        }
        val navController = findNavController(R.id.container)

        return item.onNavDestinationSelected(navController) or super.onOptionsItemSelected(item)
    }

    private fun menuItemsSeVisibility(visibility: Boolean) {
        binding.apply {
            textRecipe.isVisible = visibility
        }
    }

    private fun visibilityMenuItems(isVisible: Boolean) {
        menuToolbar?.let { it ->
            it.forEach { menuItem ->
                menuItem.isVisible = isVisible
            }
        }
        if (isVisible) {
            dashboardViewModel.setTemporalNameOfToolbar(getString(R.string.app_name))
            restoreImageToolbar()
        }
    }

}