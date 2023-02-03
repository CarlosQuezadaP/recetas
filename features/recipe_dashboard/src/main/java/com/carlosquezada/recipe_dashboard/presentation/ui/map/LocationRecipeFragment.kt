package com.carlosquezada.recipe_dashboard.presentation.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.carlosquezada.recipe_dashboard.R
import com.carlosquezada.recipe_dashboard.databinding.FragmentLocationRecipeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationRecipeFragment : Fragment(R.layout.fragment_location_recipe), OnMapReadyCallback {

    private lateinit var binding: FragmentLocationRecipeBinding
    private lateinit var mMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationRecipeBinding.bind(view)
        setupMap()
    }

    private fun setupMap() {
        val supportMapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val latLong = LatLng(
            19.8077463, -99.4077038
        )
        mMap.addMarker(MarkerOptions().position(latLong).title("mexico"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLong))
    }
}