package com.example.taller1rocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class RandomGreetActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_greet)
        val language = intent.getStringExtra("lang").toString()
        val txtGreeting = findViewById<TextView>(R.id.greeting)
        val btnRandomize : Button = findViewById(R.id.btnRandomize)
        updateGreeting(txtGreeting, language)
        createClickListeners(txtGreeting, btnRandomize, language)
    }

    private fun createClickListeners(pTxtGreeting : TextView, pBtnRandomize : Button, pLanguage: String)
    {
        pBtnRandomize.setOnClickListener {
            updateGreeting(pTxtGreeting, pLanguage)
        }
    }

    private fun updateGreeting(pTxtGreeting : TextView, pLanguage: String)
    {
        val randomIndex = (0..2).random()
        when(pLanguage)
        {
            resources.getStringArray(R.array.languages)[0] -> pTxtGreeting.text = resources.getStringArray(R.array.enGreetings)[randomIndex]
            resources.getStringArray(R.array.languages)[1] -> pTxtGreeting.text = resources.getStringArray(R.array.spGreetings)[randomIndex]
            resources.getStringArray(R.array.languages)[2] -> pTxtGreeting.text = resources.getStringArray(R.array.frGreetings)[randomIndex]
        }
    }
}