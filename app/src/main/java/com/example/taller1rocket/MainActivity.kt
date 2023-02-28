package com.example.taller1rocket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnGuessGame: Button = findViewById(R.id.btnGuessGame)
        val txtRange = findViewById<EditText>(R.id.txtRange)
        val btnRandomGreet: Button = findViewById(R.id.btnRandomGreet)
        val languageSpinner = findViewById<Spinner>(R.id.spinnerLanguages)
        val btnCountries: Button = findViewById(R.id.btnCountries)
        createClickListeners(btnGuessGame, txtRange, btnRandomGreet, languageSpinner, btnCountries)
    }

    private fun createClickListeners(pBtnGame : Button, pTxtRange : EditText, pBtnGreet : Button, pSpinner : Spinner, pBtnCountries : Button)
    {
        pBtnGame.setOnClickListener {
            pTxtRange.inputType = InputType.TYPE_CLASS_NUMBER
            val rang = pTxtRange.text.toString()
            var rangInt = 0
            if(rang != "")
            {
                rangInt = rang.toInt()
            }
            if(rangInt < 1 || rangInt > 1000)
            {
                Toast.makeText(baseContext, getString(R.string.msgIncorrectRange), Toast.LENGTH_LONG).show()
            }
            else
            {
                val intentGame = Intent(this, GuessGameActivity::class.java)
                intentGame.putExtra("rang", rangInt)
                startActivity(intentGame)
            }
        }

        pBtnGreet.setOnClickListener {
            val intentGreet = Intent(this, RandomGreetActivity::class.java)
            intentGreet.putExtra("lang", pSpinner.selectedItem.toString())
            startActivity(intentGreet)
        }

        pBtnCountries.setOnClickListener {
            val intentCountries = Intent(this, CountriesActivity::class.java)
            startActivity(intentCountries)
        }

    }
}