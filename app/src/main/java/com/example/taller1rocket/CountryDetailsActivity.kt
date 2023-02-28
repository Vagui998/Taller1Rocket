package com.example.taller1rocket
import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class CountryDetailsActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
        val paisStr = intent.getStringExtra("Pais")
        val gson = Gson()
        val pais = gson.fromJson(paisStr, Country::class.java)
        initialize(pais)
        var picPath = "country_flags/".plus(pais.sigla.toString().lowercase())
        picPath = picPath.plus(".png")
        loadFlagPicture(this, picPath)
    }
    private fun initialize(pPais : Country)
    {
        val txtCountryName = findViewById<TextView>(R.id.countryName)
        val txtEngCountryName = findViewById<TextView>(R.id.engCountryName)
        val txtCapital = findViewById<TextView>(R.id.capitalTxt)
        val txtAbbreviation = findViewById<TextView>(R.id.abbreviationTxt)

        txtCountryName.text = pPais.nomPais.toString()
        txtEngCountryName.text = txtEngCountryName.text.toString().plus(" ")
        txtCapital.text = txtCapital.text.toString().plus(" ")
        txtAbbreviation.text = txtAbbreviation.text.toString().plus(" ")
        txtEngCountryName.text = txtEngCountryName.text.toString().plus(pPais.nomPaisInt.toString())
        txtCapital.text = txtCapital.text.toString().plus(pPais.capital.toString())
        txtAbbreviation.text = txtAbbreviation.text.toString().plus(pPais.sigla.toString())
    }

    private fun loadFlagPicture(pContext : Context, pPath : String)
    {
        val manager = pContext.assets
        val picFlag : ImageView = findViewById(R.id.countryFlagPic)
        try
        {
            val inpStr : InputStream = manager.open(pPath)
            val bitmap = BitmapFactory.decodeStream(inpStr)
            picFlag.setImageBitmap(bitmap)
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }

    }
}