package com.example.taller1rocket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class CountriesActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        val countryList : ListView = findViewById(R.id.listCountries)
        val paises = loadCountries(this)
        var paisesStr = arrayOf<String>()
        for (pais in paises)
        {
            paisesStr = paisesStr.plus(pais.nomPais.toString())
        }
        val arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, paisesStr)
        countryList.adapter = arrayAdapter
        createOnSelectListener(countryList, paises)
    }

    private fun createOnSelectListener(pCountryList : ListView, pPaises : List<Country>)
    {
        pCountryList.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val intentCountryDetails = Intent(baseContext, CountryDetailsActivity::class.java)
                intentCountryDetails.putExtra("Pais", Gson().toJson(pPaises[position]))
                startActivity(intentCountryDetails)
            }
    }

    private fun loadCountries(context: Context): List<Country>
    {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("paises.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.i("MyApp", ioException.toString())
        }

        val listCountryType = object : TypeToken<List<Country>>() {}.type
        return Gson().fromJson(jsonString, listCountryType)
    }
}

