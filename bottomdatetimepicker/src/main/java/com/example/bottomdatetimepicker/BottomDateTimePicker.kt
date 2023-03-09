package com.example.bottomdatetimepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomdatetimepicker.TimeAdapter.OnTimeSelectedListener
import com.github.badoualy.datepicker.DatePickerTimeline
import com.github.badoualy.datepicker.DatePickerTimeline.OnDateSelectedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class BottomDateTimePicker : BottomSheetDialogFragment() {
    private lateinit var selectedCal: Calendar
    private var dateTimeSetListener: OnDateTimeSetListener? = null
    fun setOnDateTimeSetListener(onDateTimeSetListener: OnDateTimeSetListener?) {
        if (onDateTimeSetListener != null) {
            dateTimeSetListener = onDateTimeSetListener
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_date_time_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var title: String? = ""
        if (arguments != null) {
            title = requireArguments().getString(INPUT_TITLE)
        }
        val selectedDateTime = view.findViewById<TextView>(R.id.selected_date_time)
        selectedDateTime.text = title
        selectedCal = Calendar.getInstance()
        val datePicker = view.findViewById<DatePickerTimeline>(R.id.date_picker)
        datePicker.setFirstVisibleDate(
            selectedCal.get(Calendar.YEAR), selectedCal.get(Calendar.MONTH), selectedCal.get(
                Calendar.DAY_OF_MONTH
            )
        )
        datePicker.onDateSelectedListener =
            OnDateSelectedListener { year: Int, month: Int, day: Int, index: Int ->
                selectedCal.set(Calendar.YEAR, year)
                selectedCal.set(Calendar.MONTH, month)
                selectedCal.set(Calendar.DAY_OF_MONTH, day)
            }

        val adapter = TimeAdapter(selectedCal[Calendar.HOUR_OF_DAY], object : OnTimeSelectedListener {
                override fun onTimeSelected(hourOfTheDay: Int) {
                    selectedCal[Calendar.HOUR_OF_DAY] = hourOfTheDay
                }
            })
        val timeRecycler = view.findViewById<RecyclerView>(R.id.time_picker)
        timeRecycler.scrollToPosition(selectedCal.get(Calendar.HOUR_OF_DAY))
        timeRecycler.adapter = adapter
        
        val doneButton = view.findViewById<TextView>(R.id.picker_done)
        doneButton.setOnClickListener {
            dateTimeSetListener!!.onDateTimeSelected(selectedCal)
            dismissAllowingStateLoss()
        }

    }

    interface OnDateTimeSetListener {
        fun onDateTimeSelected(selectedDateTime: Calendar?)
    }

    companion object {
        private const val TAG = "BottomSlidingDateTime"
        private const val INPUT_TITLE = "Title"
        fun newInstance(title: String?): BottomDateTimePicker {
            val args = Bundle()
            args.putString(INPUT_TITLE, title)
            val fragment = BottomDateTimePicker()
            fragment.arguments = args
            return fragment
        }
    }
}