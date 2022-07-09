package com.example.mylibrary.fragmentAndViewModle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlin.jetpack.fragmentAndViewModle.Fragment1ViewModel
import com.example.mylibrary.R
import com.example.mylibrary.databinding.Fragment2Binding
import com.example.mylibrary.databinding.Fragment3Binding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment3 : Fragment() {


    private lateinit var viewModel: Fragment1ViewModel
    private lateinit var viewBinding:Fragment3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        viewBinding = Fragment3Binding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it).get(Fragment1ViewModel::class.java) }!!
        viewModel.run { getdata().observe(activity!!,object :Observer<Int>{
            override fun onChanged(t: Int?) {
                viewBinding.bt.text = t.toString()
            }
        }) }

//        viewBinding.bt.setOnClickListener {
//            viewModel.addone()
//        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            Fragment3()

    }
}