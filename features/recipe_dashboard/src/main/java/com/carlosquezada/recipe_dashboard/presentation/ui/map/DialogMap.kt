package com.carlosquezada.recipe_dashboard.presentation.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.carlosquezada.recipe_dashboard.R
import com.carlosquezada.recipe_dashboard.databinding.FragmentMapDialogBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

class DialogMap : DialogFragment(), OnMapReadyCallback {

    private var paramArgumentNameOfRecipe: String? = null
    private var paramArgumentLatitude: Double? = null
    private var paramArgumentLongitude: Double? = null

    private lateinit var binding: FragmentMapDialogBinding

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramArgumentNameOfRecipe = it.getString(ARG_PARAM1)
            paramArgumentLatitude = it.getDouble(ARG_PARAM2)
            paramArgumentLongitude = it.getDouble(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentMapDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameOfRecipe.text = "Recipe: $paramArgumentNameOfRecipe"
        setupMap()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val latLong = paramArgumentLatitude?.let {
            paramArgumentLongitude?.let { it1 ->
                LatLng(
                    it, it1
                )

            }
        }
        latLong?.let {
            mMap.addMarker(MarkerOptions().position(it))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(it))
        }
    }


    private fun setupMap() {
        val supportMapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
    }


    companion object {
        @JvmStatic
        fun newInstance(nameOfRecipe: String, latRecipe: Double, lng: Double) =
            DialogMap().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, nameOfRecipe)
                    putDouble(ARG_PARAM2, latRecipe)
                    putDouble(ARG_PARAM3, lng)
                }
            }
    }
}