package com.example.mykotlin.fragmenttest.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.mykotlin.R
import com.example.mykotlin.fragmenttest.vp2.vp2PicAdaptor

class frag2(override var TAG: String) : basefrag() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.layout_vp2,container,false)
        var vp2 = view.findViewById<ViewPager2>(R.id.vp2)
        vp2.adapter = vp2PicAdaptor(mycontext)
        return view
    }


}