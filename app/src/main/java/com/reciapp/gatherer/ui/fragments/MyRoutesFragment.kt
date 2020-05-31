package com.reciapp.gatherer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.reciapp.gatherer.ui.states.MyRoutesState
import com.reciapp.gatherer.ui.viewmodels.MyRoutesViewModel
import kotlinx.android.synthetic.main.fragment_my_routes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyRoutesFragment : Fragment() {

    private val myRoutesViewModel: MyRoutesViewModel by viewModel()

    private val routesAdapter: RoutesAdapter by lazy {
        RoutesAdapter(true, this::onClickRoute)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_routes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initSubscriptions()
    }

    override fun onResume() {
        super.onResume()
        myRoutesViewModel.getMyRoutes()
    }

    private fun initViews() {
        rcvMyRoutes.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rcvMyRoutes.adapter = routesAdapter
    }

    private fun initSubscriptions() {
        myRoutesViewModel.getMyRoutesStateLiveData()
            .observe(viewLifecycleOwner, Observer {
                when (it) {
                    is MyRoutesState.Loading -> {
                        showLoaderView()
                    }
                    is MyRoutesState.Success -> {
                        val routes = it.routes
                        if (routes.isEmpty()) {
                            // TODO: View No Routes
                        } else {
                            routesAdapter.setRoutes(it.routes)
                        }
                        hideLoaderViewWithDelay()
                    }
                    is MyRoutesState.Failure -> {
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