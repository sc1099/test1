package com.example.mykotlin.md

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

//TODO 子类的构造函数必须调用父类的构造函数，这里父类没有（），所以必须使用super进行调用
class myfloatbutton :FloatingActionButton{
    var x_down = 0f
    var y_down = 0f
    var view_x_down =0f
    var view_y_down =0f
    var dx = 0f
    var dy = 0f
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr){

            }


    private  val TAG = "myfloatbutton"
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when(ev.action){
            ACTION_DOWN->{
                x_down = ev.x
                y_down = ev.y
            }
            ACTION_MOVE->{
                dy = ev.y-y_down
                dx = ev.x-x_down
                var top = y+dy
                var bottom = top+height
                var left = x+dx
                var right = left+width
                layout(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())

            }
        }
        return true
    }
}