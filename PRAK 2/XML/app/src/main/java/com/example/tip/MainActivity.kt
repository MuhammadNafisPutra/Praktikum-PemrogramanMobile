package com.example.tip

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var etBillAmount: TextInputEditText
    private lateinit var spinnerTipPercentage: AutoCompleteTextView
    private lateinit var switchRoundUp: SwitchMaterial
    private lateinit var tvTipAmount: TextView

    private var tipPercentageStr = "15%"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBillAmount = findViewById(R.id.etBillAmount)
        spinnerTipPercentage = findViewById(R.id.spinnerTipPercentage)
        switchRoundUp = findViewById(R.id.switchRoundUp)
        tvTipAmount = findViewById(R.id.tvTipAmount)

        if (savedInstanceState != null) {
            tipPercentageStr = savedInstanceState.getString("SAVED_TIP_PERCENT", "15%")
        }

        val tipOptions = listOf("15%", "18%", "20%")
        spinnerTipPercentage.post {
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipOptions)
            spinnerTipPercentage.setAdapter(adapter)
            spinnerTipPercentage.setText(tipPercentageStr, false)
        }

        etBillAmount.doOnTextChanged { _, _, _, _ ->
            calculateTip()
        }
        spinnerTipPercentage.setOnItemClickListener { _, _, position, _ ->
            tipPercentageStr = tipOptions[position]
            calculateTip()
        }
        switchRoundUp.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }
        calculateTip()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("SAVED_TIP_PERCENT", tipPercentageStr)
    }
    private fun calculateTip() {
        val amountInput = etBillAmount.text.toString()
        val roundUp = switchRoundUp.isChecked

        val amount = amountInput.toDoubleOrNull() ?: 0.0
        val tipPercent = tipPercentageStr.replace("%", "").toDoubleOrNull() ?: 0.0

        var tip = (tipPercent / 100) * amount
        if (roundUp) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)
        tvTipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}