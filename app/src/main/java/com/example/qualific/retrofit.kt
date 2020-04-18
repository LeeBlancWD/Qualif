package com.example.qualific

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class Episode{
    @SerializedName("id")
    @Expose var id: Int? = null

    @SerializedName("name")
    @Expose var name: String? = null

    @SerializedName("season")
    @Expose var season: Int? = null

    @SerializedName("number")
    @Expose var number: Int? = null

    @SerializedName("summary")
    @Expose var summary: String? = null
}

interface api {
    @GET("shows/{id}/episodes")
    fun episodes(@Path("id") id: Int): Call<List<Episode>>
}