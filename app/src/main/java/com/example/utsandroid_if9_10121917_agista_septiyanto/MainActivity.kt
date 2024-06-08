package com.example.utsandroid_if9_10121917_agista_septiyanto

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.utsandroid_if9_10121917_agista_septiyanto.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = binding.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.edBirthDate.transformIntoDatePicker(this, "dd/MM/yyyy")
        binding.edConfirmationDate.transformIntoDatePicker(this, "dd/MM/yyyy")

        binding.nextBtn.setOnClickListener {
            val testType = getTestType()
            val confirmationDate = getConfirmationDate()
            val nik = binding.edNik.text.toString()
            val name = binding.edName.text.toString()
            val birthDate = binding.edBirthDate.text.toString()
            val gender = getGender()
            val relationship = getRelationship()

            if (testType != null && confirmationDate != null && name.isNotEmpty() && birthDate.isNotEmpty()
                && gender != null && relationship != null
            ) {
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("testType", testType)
                intent.putExtra("confirmationDate", confirmationDate)
                intent.putExtra("nik", nik)
                intent.putExtra("name", name)
                intent.putExtra("birthDate", birthDate)
                intent.putExtra("gender", gender)
                intent.putExtra("relationship", relationship)
                startActivity(intent)
            }
        }
    }

    private fun getTestType(): String? {
        val selectedTestType = binding.testTypeRG.checkedRadioButtonId
        if (selectedTestType == -1) {
            Toast.makeText(this, "Please select a test type", Toast.LENGTH_SHORT).show()
            binding.rbPcr.error = "Please select a test type"
            return null
        }

        return findViewById<RadioButton>(selectedTestType).text.toString()
    }

    private fun getConfirmationDate(): String? {
        val confirmationDate = binding.edConfirmationDate.text.toString()
        if (confirmationDate.isEmpty()) {
            binding.edConfirmationDate.error = "Please enter a confirmation date"
            return null
        }
        return confirmationDate
    }

    private fun getGender(): String? {
        val selectedGender = binding.rgGender.checkedRadioButtonId
        if (selectedGender == -1) {
            Toast.makeText(this, "Please enter your gender", Toast.LENGTH_SHORT).show()
            binding.rbMale.error = "Please enter your gender"
            return null
        }
        return findViewById<RadioButton>(selectedGender).text.toString()
    }

    private fun getRelationship(): String? {
        val selectedRelationship = binding.rgRelationship.checkedRadioButtonId
        if (selectedRelationship == -1) {
            Toast.makeText(this, "Please enter your relationship", Toast.LENGTH_SHORT).show()
            binding.rbOtherRelationship.error = "Please enter your relationship"
            return null
        }
        return findViewById<RadioButton>(selectedRelationship).text.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun EditText.transformIntoDatePicker(context: Context, format: String) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.US)
                setText(sdf.format(myCalendar.time))
            }

        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                show()
            }
        }
    }
}