package com.example.flighteticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.flighteticket.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cities: Array<String>
    private lateinit var ticketCount: Array<String>
    private lateinit var classOptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cities = resources.getStringArray(R.array.cities)
        ticketCount = resources.getStringArray(R.array.ticket_count)
        classOptions = resources.getStringArray(R.array.class_options)

        val adapterCities = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            cities
        )
        adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFrom.adapter = adapterCities
        binding.spinnerTo.adapter = adapterCities

        val adapterTicketCount = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            ticketCount
        )
        adapterTicketCount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTicketCount.adapter = adapterTicketCount

        val adapterClassOptions = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            classOptions
        )
        adapterClassOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerClass.adapter = adapterClassOptions

        var selectedDate = ""
        binding.datePicker.init(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ) { _, year, monthOfYear, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, monthOfYear, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            selectedDate = dateFormat.format(calendar.time)
        }

        var selectedTime = ""
        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        }

        binding.buttonPesan.setOnClickListener {
            val selectedTicketCount = binding.spinnerTicketCount.selectedItem?.toString() ?: ""
            val dateTimeMessage =
                "$selectedTicketCount tikets with a departure schedule on the date $selectedDate at $selectedTime have been successfully booked."
            Toast.makeText(this, dateTimeMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
