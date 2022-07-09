package com.example.mykotlin.Mylistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlin.R


class adaptor(val list: List<People>,val context:Context):RecyclerView.Adapter<adaptor.myViewHolder>() {
    var listener:rvclicklistener?=null
    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tx:TextView = itemView.findViewById(R.id.tx)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_listactivity,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.tx.text = list.get(position).name
        listener?.myclick(holder.tx,position)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}



interface rvclicklistener{
    fun myclick(view:View,pos:Int)
}