package com.example.mylibrary.mynavigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.mylibrary.R
import com.example.mylibrary.databinding.FragmentNav1Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentNav1.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentNav1 : Fragment() {
    private  val TAG = "FragmentNav1"
    lateinit var viewbinding:FragmentNav1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        viewbinding = FragmentNav1Binding.inflate(inflater,container,false)
        viewbinding.tv.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentNav1_to_fragmentNav2)
        }
        return viewbinding.root
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
    


}