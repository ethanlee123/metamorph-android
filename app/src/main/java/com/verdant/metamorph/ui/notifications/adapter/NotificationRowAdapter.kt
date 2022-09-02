package com.verdant.metamorph.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.verdant.metamorph.R
import com.verdant.metamorph.model.NotificationRowResponse

class NotificationRowAdapter(
    private val notificationsDataSet: List<NotificationRowResponse>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class NotificationRowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orderNo: TextView

        init {
            orderNo = view.findViewById(R.id.tv_orderNo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_row_item, parent, false)
        return NotificationRowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val notificationRowViewHolder = holder as NotificationRowViewHolder
        val resources = notificationRowViewHolder.itemView.resources
        val notificationRowItem = notificationsDataSet[position]
        notificationRowViewHolder.orderNo.text = notificationRowItem.orderNo
    }

    override fun getItemCount() = notificationsDataSet.size
}