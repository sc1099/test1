package com.example.mykotlin.md

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

class txfollowBehavior(context: Context, attributeSet: AttributeSet?=null) : CoordinatorLayout.Behavior<View>(context,attributeSet) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is myfloatbutton
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        child.y = dependency.y+300
        child.x = dependency.x
        return true
    }

}