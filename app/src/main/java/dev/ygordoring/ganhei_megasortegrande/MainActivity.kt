package dev.ygordoring.ganhei_megasortegrande

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var prefer: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Buscar referência dos objetos
        val editText: EditText = findViewById(R.id.editNumber)
        val txtResult: TextView = findViewById(R.id.txtResult)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

        // Save prefer
        prefer = getSharedPreferences("DB", Context.MODE_PRIVATE)
        val result = prefer.getString("result_ID", null)
        if (result != null) {
            txtResult.text = "Último valor sorteado: \n $result"
        }

        btnGenerate.setOnClickListener {
            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }
    }


    private fun numberGenerator(text: String, txtResult: TextView) {
        if (text.isEmpty()) {
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val qtd = text.toInt()
        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }


        val numbers = mutableSetOf<Int>()
        val random = Random()

        while (true) {

            val number = random.nextInt(60)
            numbers.add(number + 1)
            if (numbers.size == qtd) {
                break
            }

        }
        txtResult.text = numbers.joinToString(" - ")
        val editor = prefer.edit()
        editor.putString("result_ID", txtResult.text.toString())
        editor.apply()
    }

}