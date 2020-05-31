package com.reciapp.gatherer.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import com.reciapp.gatherer.R
import kotlinx.android.synthetic.main.activity_route.*

class RouteActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
        initToolbar()
        initMap()
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun initToolbar() {
        tbRoute.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapView = googleMap
        mapView.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    DEFAULT_LOCATION.latitude,
                    DEFAULT_LOCATION.longitude
                ), DEFAULT_ZOOM
            )
        )
        getLocationPermission()
    }

    private fun getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        } else {
            initLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    initLocation()
                } else {
                    val snackBar = Snackbar.make(
                        colContent,
                        R.string.warning_permission_denied,
                        Snackbar.LENGTH_LONG
                    ).setAction(R.string.btn_retry) {
                        getLocationPermission()
                    }
                    snackBar.show()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun initLocation() {
        mapView.isMyLocationEnabled = true
        mapView.uiSettings.isMyLocationButtonEnabled = true

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = UPDATE_INTERVAL
        locationRequest.fastestInterval = FASTEST_INTERVAL

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                val location = locationResult.lastLocation
                mapView.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            location.latitude,
                            location.longitude
                        ), DEFAULT_ZOOM
                    )
                )
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    companion object {
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1001
        private const val UPDATE_INTERVAL = (10 * 1000).toLong()
        private const val FASTEST_INTERVAL: Long = 2000
        private const val DEFAULT_ZOOM = 16f
        private val DEFAULT_LOCATION = LatLng(4.6097102, -74.081749)

        fun launch(from: Context) {
            from.startActivity(Intent(from, RouteActivity::class.java))
        }
    }
}