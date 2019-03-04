package com.abstrac.android_client_admin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.client_row.view.*

class MainAdapter(val client: Client): RecyclerView.Adapter<CustomViewHolder>(){

    //val client = listOf("alejo","david","julian","master pro")

    // number of items
    override fun getItemCount(): Int {
        return client.data.cl_tconsums.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0?.context)
        val cellForRow = layoutInflater.inflate(R.layout.client_row,p0,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        val clientConsumo = client.data.cl_tconsums.get(p1)
        p0.itemView?.textView_name_client?.text = clientConsumo.consum_date
    }

}

class CustomViewHolder(v: View): RecyclerView.ViewHolder(v){

}