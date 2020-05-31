package com.reciapp.gatherer.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
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
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.reciapp.gatherer.R
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.extensions.ui.hideLoaderView
import com.reciapp.gatherer.extensions.ui.showLoaderView
import com.reciapp.gatherer.ui.states.AssignRouteState
import com.reciapp.gatherer.ui.states.FinishPointRouteState
import com.reciapp.gatherer.ui.states.StartRouteState
import com.reciapp.gatherer.ui.viewmodels.RouteViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_route.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RouteActivity : AppCompatActivity(), OnMapReadyCallback {

    private val routeViewModel: RouteViewModel by viewModel {
        parametersOf(intent?.extras?.getParcelable(EXTRA_ROUTE)!!)
    }

    private lateinit var mapView: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationCallback = getLocationCallback()

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
        initSubscriptions()
        initListeners()
        initViews()
        initMap()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::fusedLocationClient.isInitialized) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    private fun initSubscriptions() {
        routeViewModel.getAssignRouteStateLiveData().observe(this, Observer {
            when (it) {
                is AssignRouteState.Loading -> {
                    showLoaderView()
                }
                is AssignRouteState.Success -> {
                    hideLoaderView()
                    setTitleButton(routeViewModel.route)
                }
                is AssignRouteState.Failure -> {
                    hideLoaderView()
                    Toast.makeText(this, R.string.error_server, Toast.LENGTH_SHORT).show()
                }
            }
        })

        routeViewModel.getStartRouteStateLiveData().observe(this, Observer {
            when (it) {
                is StartRouteState.Loading -> {
                    showLoaderView()
                }
                is StartRouteState.Success -> {
                    hideLoaderView()
                    setTitleButton(routeViewModel.route)
                    setDataMap()
                }
                is StartRouteState.Failure -> {
                    hideLoaderView()
                    Toast.makeText(this, R.string.error_server, Toast.LENGTH_SHORT).show()
                }
            }
        })

        routeViewModel.getFinishPointRouteStateLiveData().observe(this, Observer {
            when(it) {
                is FinishPointRouteState.Loading -> {
                    showLoaderView()
                }
                is FinishPointRouteState.Success -> {
                    hideLoaderView()
                    setTitleButton(routeViewModel.route)
                    setDataMap()
                }
                is FinishPointRouteState.Failure -> {
                    hideLoaderView()
                    Toast.makeText(this, R.string.error_server, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun initListeners() {
        tbRoute.setNavigationOnClickListener {
            onBackPressed()
        }
        compositeDisposable.add(
            btnAction.clicks()
                .subscribe({
                    when (routeViewModel.route.status) {
                        Route.STATUS.AVAILABLE -> {
                            routeViewModel.assignRoute()
                        }
                        Route.STATUS.ASSIGNED -> {
                            routeViewModel.startRoute()
                        }
                        Route.STATUS.INITIATED -> {
                            routeViewModel.markPointComplete()
                        }
                        Route.STATUS.FINISHED -> {
                            finish()
                        }
                    }
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun initViews() {
        tbRoute.title = routeViewModel.route.sector
        setTitleButton(routeViewModel.route)
    }

    private fun setTitleButton(route: Route) {
        when (route.status) {
            Route.STATUS.AVAILABLE -> {
                btnAction.setText(R.string.btn_i_collect)
            }
            Route.STATUS.ASSIGNED -> {
                btnAction.setText(R.string.btn_start_route)
            }
            Route.STATUS.INITIATED -> {
                btnAction.setText(R.string.btn_pick_up_point)
            }
            Route.STATUS.FINISHED -> {
                btnAction.setText(R.string.btn_finish_route)
            }
        }
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapView = googleMap
        setDataMap()
    }

    private fun setDataMap() {
        routeViewModel.route.pickingPoints.firstOrNull()?.let {
            moveCamera(LatLng(it.latitude, it.longitude))
        }
        setMarkets(routeViewModel.route.pickingPoints)
        /*if (routeViewModel.route.status == Route.STATUS.INITIATED) {
            getLocationPermission()
        }*/
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
        if (::mapView.isInitialized) {
            mapView.isMyLocationEnabled = true
            mapView.uiSettings.isMyLocationButtonEnabled = true
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = UPDATE_INTERVAL
        locationRequest.fastestInterval = FASTEST_INTERVAL

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun getLocationCallback(): LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            val location = locationResult.lastLocation
            if (::mapView.isInitialized) {
                moveCamera(LatLng(location.latitude, location.longitude))
            }
        }
    }

    private fun setMarkets(points: List<Route.PickingPoint>) {
        runOnUiThread {
            mapView.clear()
            points.forEach { point ->
                mapView.addMarker(
                    MarkerOptions()
                        .position(LatLng(point.latitude, point.longitude))
                        .title(point.addressFirst)
                )
            }

        }
    }

    private fun moveCamera(latLng: LatLng) {
        runOnUiThread {
            mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM))
        }
    }

    companion object {
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1001
        private const val UPDATE_INTERVAL = (10 * 1000).toLong()
        private const val FASTEST_INTERVAL: Long = 2000
        private const val DEFAULT_ZOOM = 16f
        private const val EXTRA_ROUTE = "route"

        fun launch(from: Context, route: Route) {
            from.startActivity(Intent(from, RouteActivity::class.java).apply {
                putExtra(EXTRA_ROUTE, route)
            })
        }
    }
}