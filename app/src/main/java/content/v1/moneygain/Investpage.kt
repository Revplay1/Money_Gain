package content.v1.moneygain

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class Investpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_investpage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.hide(
                WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
        val monthInpt = findViewById<TextInputEditText>(R.id.inptMonths)
        val moneyInpt = findViewById<TextInputEditText>(R.id.inptMoney)
        val percInpt = findViewById<TextInputEditText>(R.id.inptPerc)

        val rootView = findViewById<View>(android.R.id.content)

        findViewById<View>(R.id.btnCalc).setOnClickListener {
            val monthsText = monthInpt.text.toString().trim()
            val moneyText = moneyInpt.text.toString().trim()
            val percText = percInpt.text.toString().trim()
            var resultView = findViewById<TextView>(R.id.resultText)

            when {
                monthsText.isEmpty() -> {
                    Snackbar.make(
                        rootView,
                        "Please enter the number of months!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                moneyText.isEmpty() -> {
                    Snackbar.make(
                        rootView,
                        "Please enter the amount of money!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                percText.isEmpty() -> {
                    Snackbar.make(
                        rootView,
                        "Please enter the percentage rate!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }

            val months = monthsText.toInt() ?: 0
            val money = moneyText.toDouble() ?: 0.0
            val annualRate = percText.toDouble() ?: 0.0

            val monthlyRate = annualRate / 100 /12

            val finalAmount = money * Math.pow(1 + monthlyRate, months.toDouble())

            resultView.text = "$finalAmount"

        }

    }
}