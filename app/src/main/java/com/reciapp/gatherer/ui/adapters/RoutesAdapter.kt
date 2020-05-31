package com.reciapp.gatherer.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reciapp.gatherer.R
import com.reciapp.gatherer.domain.models.Route
import com.reciapp.gatherer.utils.DateFormatConverter
import kotlinx.android.synthetic.main.item_route.view.*

class RoutesAdapter(
    private val isMine: Boolean,
    private val closure: (Route) -> Unit
) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    private val routes = mutableListOf<Route>()

    fun setRoutes(routes: List<Route>) {
        this.routes.clear()
        this.routes.addAll(routes)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_route, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = routes.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = routes[position]
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(route: Route) {
            itemView.txvSector.text = route.sector
            itemView.txvDate.text = DateFormatConverter.formatDate(route.date, DateFormatConverter.PatternDate.DATE_RFC3339, DateFormatConverter.PatternDate.DAY_NAME_AND_NUMBER)
            if (isMine) {
                itemView.btnViewRoute.text = itemView.context.getString(R.string.btn_view_my_route)
            } else {
                itemView.btnViewRoute.text = itemView.context.getString(R.string.btn_view_route)
            }
            itemView.btnViewRoute.setOnClickListener {
                closure.invoke(route)
            }
        }
    }
}