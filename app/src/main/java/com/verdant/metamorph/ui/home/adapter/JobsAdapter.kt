package com.verdant.metamorph.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.verdant.metamorph.R
import com.verdant.metamorph.model.WebOrderResponse
import com.verdant.metamorph.util.formatDateTime


class JobsAdapter(
    private var jobDetailsDataSet: List<WebOrderResponse>,
    private val onJobItemRowClick: IJobRowItemOnClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class JobViewHolder(view: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val date: TextView
        val time: TextView
        val paid: TextView
        val earn: TextView
        val status: TextView
        val from: TextView
        val order: TextView
        val trn: TextView

        init {
            // Define click listener for the ViewHolder's View.
            date = view.findViewById(R.id.tv_date)
            time = view.findViewById(R.id.tv_time)
            paid = view.findViewById(R.id.tv_paid)
            earn = view.findViewById(R.id.tv_earn)
            status = view.findViewById(R.id.tv_status)
            from = view.findViewById(R.id.tv_from)
            order = view.findViewById(R.id.tv_order)
            trn = view.findViewById(R.id.tv_trn)

            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.job_info_row_item, viewGroup, false)
            val viewHolder = JobViewHolder(view) {
                onJobItemRowClick.setOnClickListener(jobDetailsDataSet[it].OrderNo)
            }
            return viewHolder

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                val viewHolder = viewHolder as JobViewHolder
                val resources = viewHolder.itemView.resources
                val jobItem = jobDetailsDataSet[position]
                // Get element from your dataset at this position and replace the
                // contents of the view with that element
                viewHolder.date.text = formatDateTime(jobDetailsDataSet[position].OrderDate)
                viewHolder.time.text = jobItem.DeliveryPlan
                viewHolder.paid.text = resources.getString(R.string.paid, jobItem.PaymentAmount.toString())
                viewHolder.earn.text = resources.getString(R.string.paid, jobItem.TranslatorFee.toString())
                viewHolder.status.text = jobItem.OrderStatusName
                viewHolder.from.text = jobItem.WebOrderTitle
                viewHolder.order.text = jobItem.OrderNo
                viewHolder.trn.text = jobItem.TranslatorNo.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = jobDetailsDataSet.size

    fun updateList(response: List<WebOrderResponse>?) {
        if (response != null) {
            jobDetailsDataSet = response
        }
        notifyDataSetChanged()

    }

    // Is loading true if the last item is footer
    fun isLoading() : Boolean {
        return false
    }

    interface IJobRowItemOnClick {
        fun setOnClickListener(orderNo: String)
    }
}