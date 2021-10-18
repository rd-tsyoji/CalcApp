package jp.techacademy.takafumi.shouji.calcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.techacademy.takafumi.shouji.calcapp.databinding.ActivityCalcResultBinding

class CalcResult : AppCompatActivity() {
    private lateinit var binding: ActivityCalcResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val value1 = intent.getDoubleExtra("VALUE1", 0.0)
        val value2 = intent.getDoubleExtra("VALUE2", 0.0)
        val calcString = intent.getStringExtra("CALC") ?: ""

        showCalcResult(value1, value2, calcString)
    }

    /**
     * 結果を表示
     */
    private fun showCalcResult(value1: Double, value2: Double, calcString: String) {

        var calcResult = try {
            when (calcString) {
                // 加算
                "addition_button" -> (value1 + value2).toString()
                // 減算
                "subtraction_button" -> (value1 - value2).toString()
                // 乗算
                "multiplication_button" -> (value1 * value2).toString()
                // 除算
                "division_button" -> if (value2 == 0.0) "計算エラー" else (value1 / value2).toString()
                // その他はエラー
                else -> "計算エラー"
            }
        } catch (e: Exception) {
            "計算エラー"
        }

        binding.textResult.text = calcResult
    }
}