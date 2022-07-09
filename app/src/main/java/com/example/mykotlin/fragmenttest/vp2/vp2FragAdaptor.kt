package com.example.mykotlin.fragmenttest.vp2

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mykotlin.fragmenttest.frags.basefrag

class vp2FragAdaptor(var fragmentActivity: FragmentActivity,var list: List<basefrag>):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }
    private  val TAG = "vp2FragAdaptor"
    var shouldRefresh = false
    var a1 = 1L
    var a2 = 2L
    var a3 = 3L
    var a4 = 4L
    var a0 = 0L
    var alist = mutableListOf<Long>(a0,a1,a2,a3,a4)
    var usedID = hashSetOf<Long>()
    var id = mutableListOf<Long>(0,1,2,3,4)
    var changeid = true
    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "createFragment: "+position)
        return when(position){
            1->{usedID.add(alist[1])
                basefrag("......frag1")
            }
            2->{usedID.add(alist[2])
                basefrag("......frag2")
            }
            3->{usedID.add(alist[3])
                basefrag("......frag3")
            }
            4->{usedID.add(alist[4])
                basefrag("......frag4")
            }
            else -> {usedID.add(alist[0])
                basefrag("......frag0")
            }
        }
    }

    fun update(position: Int,scale:Long){
        alist[position] += scale
        id[position]+= scale
        notifyDataSetChanged()
    }
    override fun getItemId(position: Int): Long {
        Log.d(TAG, "getItemId: "+position)
        return id[position]
    }

    override fun containsItem(itemId: Long): Boolean {
       return usedID.contains(itemId)
    }

}