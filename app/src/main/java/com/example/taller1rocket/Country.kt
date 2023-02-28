package com.example.taller1rocket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(

    @SerializedName("capital") @Expose var capital: String? = null,
    @SerializedName("nombre_pais") @Expose var nomPais: String? = null,
    @SerializedName("nombre_pais_int") @Expose var nomPaisInt: String? = null,
    @SerializedName("sigla") @Expose var sigla: String? = null
)