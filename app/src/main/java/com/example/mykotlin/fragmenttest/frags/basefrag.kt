package com.example.mykotlin.fragmenttest.frags

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mykotlin.R


open class basefrag(open var TAG: String = "basefrag") : Fragment(){
    lateinit var mycontext: Context
    lateinit var mroot:View

//    fun newBaseFrag(bundle: Bundle):basefrag{
//        var frag = basefrag(TAG)
//        frag.arguments = bundle
//        return frag
//    }


    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach: ")
        mycontext = context
        if(arguments == null) Log.d(TAG, "arguments == null...... ")
        var bundle = arguments
        if(bundle == null) Log.d(TAG, "bundle == null........... ")
        bundle.let {
            Log.d(TAG, "onAttach: bundle  "+it?.getString("sc"))
        }
        super.onAttach(context)
    }

    lateinit var listenerkt:(String)->Unit
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
    }
    lateinit var t1:TextView
    lateinit var t2:TextView
    lateinit var listener:myCallack
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        var view = inflater.inflate(R.layout.frag1_layout,container,false)
        t1 = view.findViewById(R.id.t1)
        t2 = view.findViewById(R.id.t2)
        t1.text = TAG
        //todo t1用回调函数方法（java） t2用lambda（kotlin）
        t1.setOnClickListener {
            if(::listener.isInitialized)
            listener.tback((t1.text).toString())
        }
        t2.setOnClickListener {
            listenerkt(t2.text.toString())
        }
        return view
    }

    fun fragToActivity(trans:(String)->Unit){
        listenerkt = trans
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated: ")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
    override fun onDetach() {
        Log.d(TAG, "onDetach: ")
        super.onDetach()
    }


    interface myCallack{
        fun tback(string: String)
    }

}