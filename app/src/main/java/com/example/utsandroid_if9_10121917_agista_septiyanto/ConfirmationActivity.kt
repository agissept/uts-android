package com.example.utsandroid_if9_10121917_agista_septiyanto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.utsandroid_if9_10121917_agista_septiyanto.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
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

        val testType = intent.getStringExtra("testType")
        val confirmationDate = intent.getStringExtra("confirmationDate")
        val nik = intent.getStringExtra("nik")
        val name = intent.getStringExtra("name")
        val birthDate = intent.getStringExtra("birthDate")
        val gender = intent.getStringExtra("gender")
        val relationship = intent.getStringExtra("relationship")

        binding.tvTestType.text = testType
        binding.tvConfirmationDate.text = confirmationDate
        binding.tvNik.text = nik
        binding.tvName.text = name
        binding.tvBirthDate.text = birthDate
        binding.tvGender.text = gender
        binding.tvRelation.text = relationship

        binding.btnCheckResult.setOnClickListener {
            val modalBottomSheet = BottomSheetFragment()
            modalBottomSheet.show(supportFragmentManager, "dialog")
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}