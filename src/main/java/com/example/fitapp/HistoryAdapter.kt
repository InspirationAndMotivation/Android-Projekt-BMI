/*package com.example.fitapp

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.service.autofill.Dataset
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text


class HistoryAdapter(var context: Context, var data: List<Record>, private var listener: OnAdapterClickListener?) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    // Return the size of your dataset
    override fun getItemCount() = data.size


    override fun onCreateViewHolder(parent: ViewGroup?,
                                    viewType: Int): HistoryViewHolder {

        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.record_row, parent, false)
        return HistoryViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder?.itemView?.tag = data[position]
        holder?.bmi.text = data[position].bmi.toString()
        holder?.description.text = data[position].description

    }

    class HistoryViewHolder(itemView: View?, private val listener: ViewPager.OnAdapterClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var bmi: TextView = itemView!!.findViewById(R.id.bmi_value)
        var description: TextView = itemView!!.findViewById(R.id.description)

        init {
            itemView!!.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
          listener?.onItemClick(adapterPosition,itemView.tag as Record)
              }
    }

}
*/