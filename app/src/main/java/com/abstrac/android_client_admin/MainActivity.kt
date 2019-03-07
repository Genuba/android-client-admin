package com.abstrac.android_client_admin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import com.google.gson.reflect.TypeToken



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        //recyclerView_main.adapter = MainAdapter()

        fetchJson()
    }

    fun fetchJson(){
        println("Attemting to Fetch a json")

        val url = "http://10.0.2.2:3000/client/1"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val listType = object : TypeToken<ResponseRest<Client>>() { }.type

                val responseRest = gson.fromJson<ResponseRest<Client>>(body,listType)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(responseRest)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("failure to execute request" + e)
            }
        })
    }

}