package com.example.metamorph.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.metamorph.R
import com.example.metamorph.ui.home.model.WebOrderResponse

class JobsAdapter(private val jobDetailsDataSet: Array<WebOrderResponse>) :
    RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.job_info_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.date.text = jobDetailsDataSet[position].date
        viewHolder.time.text = jobDetailsDataSet[position].time
        viewHolder.paid.text = jobDetailsDataSet[position].paid
        viewHolder.earn.text = jobDetailsDataSet[position].earn
        viewHolder.status.text = jobDetailsDataSet[position].status
        viewHolder.from.text = jobDetailsDataSet[position].from
        viewHolder.order.text = jobDetailsDataSet[position].order
        viewHolder.trn.text = jobDetailsDataSet[position].trn
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = jobDetailsDataSet.size
}