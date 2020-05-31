package com.reciapp.gatherer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reciapp.gatherer.R
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.extensions.ui.hideLoaderView
import com.reciapp.gatherer.extensions.ui.hideLoaderViewWithDelay
import com.reciapp.gatherer.extensions.ui.showLoaderView
import com.reciapp.gatherer.ui.activities.RouteActivity
import com.reciapp.gatherer.ui.adapters.RoutesAdapter
import com.reciapp.gatherer.ui.states.AvailableRoutesState
import com.reciapp.gatherer.ui.viewmodels.AvailableRoutesViewModel
import kotlinx.android.synthetic.main.fragment_routes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoutesFragment : Fragment() {

    private val availableRoutesViewModel: AvailableRoutesViewModel by viewModel()
    private val routesAdapter: RoutesAdapter by lazy {
        RoutesAdapter(false, this::onClickRoute)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_routes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initSubscriptions()
    }

    override fun onResume() {
        super.onResume()
        availableRoutesViewModel.getRoutesAvailable()
    }

    private fun initViews() {
        rcvRoutes.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rcvRoutes.adapter = routesAdapter
    }

    private fun initSubscriptions() {
        availableRoutesViewModel.getRoutesAvailableStateLiveData()
            .observe(viewLifecycleOwner, Observer {
                when (it) {
                    is AvailableRoutesState.Loading -> {
                        showLoaderView()
                    }
                    is AvailableRoutesState.Success -> {
                        val routes = it.routes
                        routesAdapter.setRoutes(it.routes)
                        if (routes.isEmpty()) {
                            cnlNoAvailableRoutes.visibility = View.VISIBLE
                        } else {
                            cnlNoAvailableRoutes.visibility = View.GONE
                        }
                        hideLoaderViewWithDelay()
                    }
                    is AvailableRoutesState.Failure -> {
                        hideLoaderView()
                        Toast.makeText(this.context, R.string.error_server, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

    private fun onClickRoute(route: Route) {
        this.context?.let {
            RouteActivity.launch(it, route)
        }
    }
}