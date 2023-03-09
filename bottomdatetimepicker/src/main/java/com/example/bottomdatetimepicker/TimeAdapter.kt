package com.example.bottomdatetimepicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TimeAdapter(
    selectedPos: Int,
    onTimeSelectedListener: OnTimeSelectedListener
) : RecyclerView.Adapter<TimeAdapter.Holder>() {
    private val times: ArrayList<String?> = object : ArrayList<String?>() {
        init {
            add("12 AM")
            add("1 AM")
            add("2 AM")
            add("3 AM")
            add("4 AM")
            add("5 AM")
            add("6 AM")
            add("7 AM")
            add("8 AM")
            add("9 AM")
            add("10 AM")
            add("11 AM")
            add("12 AM")
            add("1 PM")
            add("2 PM")
            add("3 PM")
            add("4 PM")
            add("5 PM")
            add("6 PM")
            add("7 PM")
            add("8 PM")
            add("9 PM")
            add("10 PM")
            add("11 PM")
        }
    }
    private var selectedPos = -1
    private var onTimeSelected: OnTimeSelectedListener
    private var context: Context? = null

    init {
        if (onTimeSelectedListener != null)
            onTimeSelected = onTimeSelectedListener
        else throw IllegalStateException("Not implemented")

        this.selectedPos = selectedPos
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Holder {
        context = viewGroup.context
        return Holder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_time, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.timeText.text = times[pos]
        if (pos == selectedPos) {
            holder.itemView.background =
                ContextCompat.getDrawable(context!!, R.drawable.background_time)
            holder.timeText.setTextColor(ContextCompat.getColor(context!!, R.color.white))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.white))
            holder.timeText.setTextColor(ContextCompat.getColor(context!!, R.color.greyish))
        }
        holder.itemView.setOnClickListener {
            selectedPos = holder.adapterPosition
            onTimeSelected.onTimeSelected(holder.adapterPosition)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return times.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var timeText: TextView

        init {
            timeText = itemView.findViewById(R.id.item_time_text)
        }
    }

    interface OnTimeSelectedListener {
        fun onTimeSelected(hourOfTheDay: Int)
    }
}