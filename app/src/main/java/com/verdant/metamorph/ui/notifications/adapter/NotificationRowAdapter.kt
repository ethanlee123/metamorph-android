package com.verdant.metamorph.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.verdant.metamorph.R
import com.verdant.metamorph.model.NotificationRowResponse
import com.verdant.metamorph.util.formatDateTime

class NotificationRowAdapter(
    private var notificationsDataSet: List<NotificationRowResponse>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class NotificationRowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val order: TextView
        val deliveryDate: TextView
        val translatorFee: TextView
        val translatorPay: TextView
        val orderStatusName: TextView
        val from: TextView
        val deliveryPlan: TextView

        init {
            order = view.findViewById(R.id.tv_order)
            deliveryDate = view.findViewById(R.id.tv_delivery_date)
            deliveryPlan = view.findViewById(R.id.tv_time)
            translatorFee = view.findViewById(R.id.tv_translator_fee)
            translatorPay = view.findViewById(R.id.tv_translator_pay)
            orderStatusName = view.findViewById(R.id.tv_order_status)
            from = view.findViewById(R.id.tv_from)
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
        val deliveryDateFormatted = notificationRowItem.deliveryDate?.let { formatDateTime(it) }

        notificationRowViewHolder.order.text = notificationRowItem.orderNo
        notificationRowViewHolder.deliveryDate.text = deliveryDateFormatted
        notificationRowViewHolder.deliveryPlan.text = notificationRowItem.deliveryPlan
        notificationRowViewHolder.translatorPay.text = resources.getString(R.string.paid, notificationRowItem.translatorPay.toString())
        notificationRowViewHolder.translatorFee.text = resources.getString(R.string.paid, notificationRowItem.translatorFee.toString())
        notificationRowViewHolder.orderStatusName.text = notificationRowItem.orderStatusName
        notificationRowViewHolder.from.text = notificationRowItem.webOrderTitle
    }

    override fun getItemCount() = notificationsDataSet.size

    fun updateList(response: List<NotificationRowResponse>?) {
        if (response != null) {
            notificationsDataSet = response
        }
        notifyDataSetChanged()
    }
}