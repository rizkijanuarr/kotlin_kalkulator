package udemy.lazday.kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var value1: EditText
    private lateinit var value2: EditText
    private lateinit var result: TextView
    private lateinit var button: Button
    private lateinit var radioGroup: RadioGroup

    private var OPERATOR: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupListener()
    }

    private fun initViews() {
        value1 = findViewById(R.id.et_value1)
        value2 = findViewById(R.id.et_value2)
        result = findViewById(R.id.tv_Hasil)
        button = findViewById(R.id.bt_hitung)
        radioGroup = findViewById(R.id.radio_operators)
    }

    private fun calculator(value1: Int, value2: Int): String {
        var result: Int = 0
        when(OPERATOR) {
            "+" -> result = value1 + value2
            "-" -> result = value1 - value2
            "x" -> result = value1 * value2
            ":" -> if (value2 != 0) result = value1 / value2 else return "Tidak bisa dibagi nol"
        }
        return result.toString()
    }

    private fun setupListener() {
        button.setOnClickListener {
            if (validate()) {
                val value1Int: Int = value1.text.toString().toIntOrNull() ?: 0
                val value2Int: Int = value2.text.toString().toIntOrNull() ?: 0
                result.text = calculator(value1Int, value2Int)
            } else {
                showMessage("Masukan data dengan benar!")
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val checkedRadioButton: RadioButton = findViewById(checkedId)
            OPERATOR = checkedRadioButton.text.toString()
            result.text = "HASIL"
        }
    }

    private fun validate(): Boolean {
        return !(value1.text.isNullOrEmpty() || value2.text.isNullOrEmpty() || OPERATOR.isNullOrEmpty())
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
