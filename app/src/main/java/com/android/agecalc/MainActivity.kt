package com.android.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity()
{
    private var tv_selecteddate:TextView? =null
    private var tv_ageinmins:TextView? =null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btndate:Button = findViewById(R.id.btndatepicker)
        tv_selecteddate = findViewById(R.id.tv_selecteddate)
        tv_ageinmins = findViewById(R.id.tv_ageinmins)
        btndate.setOnClickListener{
            clickdatepicker()

        }
    }

    private fun clickdatepicker()
    {
        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =DatePickerDialog(this,
            {
                    _,
                    selectedyear,
                    selectedmonth,
                    selecteddayOfMonth ->

                //show the toast
                //Toast.makeText(this, "year was $selectedyear ,month was ${selectedmonth+1} " + ",day was $selecteddayOfMonth", Toast.LENGTH_SHORT).show()

                val selecteddate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"

                //tv_selecteddate?.setText(selecteddate)  this is one way of assigning
                tv_selecteddate?.text = selecteddate

                val sdf =SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val thedate=sdf.parse(selecteddate)

                thedate?.let{
                    val selecteddateinmins=thedate.time/60000

                    val currentdate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentdate?.let{
                        val currentdateinmins=currentdate.time/60000

                        val differenceinmins = currentdateinmins - selecteddateinmins

                        tv_ageinmins?.text = differenceinmins.toString()
                    }

                }

            }
            ,year
            ,month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000  //for it to be yesterday
        dpd.show()
        //Toast.makeText(this,"date picker pressed", Toast.LENGTH_SHORT).show()

    }
}