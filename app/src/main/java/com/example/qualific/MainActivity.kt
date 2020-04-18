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

class ListAdapter(context: Context, resource:Int, array: List<Episode>):
    ArrayAdapter<Episode>(context,resource,array) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val episode = getItem(position)
                if (episode == null) {
                    throw Exception()
                }
            val view = if (convertView!=null) {
                convertView
            }
            else {
                LayoutInflater.from(context).inflate(R.layout.item1, null)
            }
        val idView = view.findViewById<TextView>(R.id.id)
        val nameView = view.findViewById<TextView>(R.id.name)
        val seasonView = view.findViewById<TextView>(R.id.season)
        val numberView = view.findViewById<TextView>(R.id.number)
        val summaryView = view.findViewById<TextView>(R.id.summary)
        idView.text = episode.id.toString()
        nameView.text = episode.name
        seasonView.text = episode.season.toString()
        numberView.text = episode.number.toString()
        summaryView.text = episode.summary
        return view
        }
    }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build()
        val listview = topList
        val api = retrofit.create(api::class.java)
        Thread(Runnable {
            api.episodes(1).enqueue(object : Callback<List<Episode>> {
                override fun onFailure(call: Call<List<Episode>>, t: Throwable) {

                }
                override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {
                    listview.post {
                        listview.adapter = ListAdapter(this@MainActivity, R.layout.item1, response.body()!!)
                    }
                }
            })
        }).start()
    }
}
