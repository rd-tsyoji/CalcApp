package jp.techacademy.takafumi.shouji.calcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import jp.techacademy.takafumi.shouji.calcapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.additionButton.setOnClickListener(this)
        binding.subtractionButton.setOnClickListener(this)
        binding.multiplicationButton.setOnClickListener(this)
        binding.divisionButton.setOnClickListener(this)
        setContentView(binding.root)
    }

    /**
     * ボタンクリック時動作
     */
    override fun onClick(v: View) {
        // 入力が空の場合入力を促すメッセージを表示
        if (TextUtils.isEmpty(binding.editTextFirst.text.toString())
            || TextUtils.isEmpty(binding.editTextSecond.text.toString())
        ) {
            Snackbar.make(v, "数値を入力してください。", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK"){}.show()
            return;
        }
        var firstInput: Double = 0.0
        var secondInput: Double = 0.0
        try {
            firstInput = binding.editTextFirst.text.toString().toDouble()
            secondInput = binding.editTextSecond.text.toString().toDouble()
        } catch (e: Exception) {
            // 不正な文字列の場合メッセージを表示
            Log.d("BUTTON", e.toString())
            Snackbar.make(v, "入力値が不正です。", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK"){}.show()
            return;
        }

        // CalcResult画面へデータを送り表示
        val intent = Intent(this, CalcResult::class.java)
        intent.putExtra("VALUE1", firstInput)
        intent.putExtra("VALUE2", secondInput)
        intent.putExtra("CALC",resources.getResourceEntryName(v.id))
        startActivity(intent)
    }

}