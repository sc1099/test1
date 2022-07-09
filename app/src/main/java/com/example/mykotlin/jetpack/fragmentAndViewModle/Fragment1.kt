package com.example.mykotlin.jetpack.fragmentAndViewModle

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mykotlin.R

class Fragment1 : Fragment() {



    companion object {
        fun newInstance() = Fragment1()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    private lateinit var viewModel: Fragment1ViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragment1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it).get(Fragment1ViewModel::class.java) }!!
        activity?.let { viewModel.getdata().observe(it,object :Observer<Int>{
            override fun onChanged(t: Int?) {

            }
        }) }
    }

}