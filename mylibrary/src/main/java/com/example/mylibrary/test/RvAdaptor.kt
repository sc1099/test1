package com.example.mylibrary.test

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ItemRvBinding


class RvAdaptor: RecyclerView.Adapter<RvAdaptor.RvViewholder>(){
    private  val TAG = "RvAdaptor"
    var list = MutableList<BeanItem>(500){
        BeanItem(it.toString())
    }


    inner class RvViewholder(var mydatabinding: ItemRvBinding) : RecyclerView.ViewHolder(mydatabinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewholder {
        val databinding = DataBindingUtil.inflate<ItemRvBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_rv,
            parent,
            false
        )
        return RvViewholder(databinding)
    }



    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: "+list.size)
        return list.size
    }

    override fun onBindViewHolder(holder: RvViewholder, position: Int) {
        holder.mydatabinding.rvItem = list[position]
    }
}