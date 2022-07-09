package com.example.mykotlin.fragmenttest.vp2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.mykotlin.R

class vp2PicAdaptor(var context: Context) : RecyclerView.Adapter<vp2PicAdaptor.viewholder>() {
    var s1 = "https://lmg.jj20.com/up/allimg/tp05/1Z9291QR05927-0-lp.jpg";
    var s2 = "http://5b0988e595225.cdn.sohucs.com/images/20200224/90987d1951ed48e9919b5b85a285b945.jpeg";
    var s3 = "http://pic1.win4000.com/mobile/2018-01-15/5a5c7b429d737.jpg";
    var s4 = "http://pics5.baidu.com/feed/d009b3de9c82d1581871cc36b30118dfbd3e425c.jpeg";
    var list = mutableListOf<String>(s1,s2,s3,s4)
    inner class viewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var im = itemView.findViewById<ImageView>(R.id.im)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
       var view = LayoutInflater.from(context).inflate(R.layout.item_vp2_pic,parent,false)
       return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        Glide.with(context).load(list.get(position)).into(holder.im)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}