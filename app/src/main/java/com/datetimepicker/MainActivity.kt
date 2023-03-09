package com.datetimepicker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomdatetimepicker.BottomDateTimePicker
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val something: TextView = findViewById(R.id.something)
        something.setOnClickListener {
            val bottomDateTimePicker: BottomDateTimePicker = BottomDateTimePicker.newInstance("Title")
            bottomDateTimePicker.show(supportFragmentManager, "SomeTAG")
            bottomDateTimePicker.setOnDateTimeSetListener(object :
                BottomDateTimePicker.OnDateTimeSetListener {
                override fun onDateTimeSelected(selectedDateTime: Calendar?) {
                    something.text = selectedDateTime?.let { it.time.toString()}
                }
            })
        }
    }
}