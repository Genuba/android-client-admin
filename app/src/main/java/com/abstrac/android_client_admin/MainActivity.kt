package com.abstrac.android_client_admin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*

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

                val client = gson.fromJson(body,Client::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(client)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("failure to execute request" + e)
            }
        })
    }

}

class Client(val data: Data)

class Data(val client_fname: String,
           val cl_tconsums: List<Cl_tconsum>,
           val cl_treceipts: List<Cl_treceipt>)

class Cl_tconsum(val consum_consum: Int,
              val consum_cant: Int,
              val consum_date: String,
              val consum_state: String,
              val client_client: Int)


class Cl_treceipt(val receipt_receipt: Int,
             val receipt_value: String,
             val receipt_date: String,
             val receipt_lastc: Int,
             val receipt_pasc: Int,
             val receipt_state: String,
             val client_client: Int)