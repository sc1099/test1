package com.example.mykotlin.Mylistview

import android.graphics.Canvas
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlin.R
import com.example.mykotlin.Utils.dip2px
import java.lang.Float.max
import java.util.*

private const val TAG = "myItemTouchHelpCallback"
class myItemTouchHelpCallback(var list: MutableList<People>, var myadaptor: adaptor) : ItemTouchHelper.Callback() {
    var mcurrentdx = 0f
    var maxpx = 0f
    var firstscrollx = false
    var mCurrentScrollXWhenInactive = 0f
    var mInitXWhenInactive = 0f
    var maxLastX = 0f
    var showDel = false
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //拖拽方向
        var dragflag = ItemTouchHelper.DOWN or  ItemTouchHelper.UP
//        Log.d(TAG, "dragflag: "+dragflag)
        //滑动方向
        var swipeflag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragflag,swipeflag)
    }

    /**
     * viewHolder:开始位置
     * target：最终位置
     * */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val frompos = viewHolder.adapterPosition
        val finalpos = target.adapterPosition
//        Log.d(TAG, "onMove: "+frompos+"   "+finalpos)
        Collections.swap(list,frompos,finalpos)
        myadaptor.notifyItemMoved(frompos,finalpos)
        return true
    }

    //TODO 当大于此阈值，会执行滑动删除操作，所以要小于该值，当view不可见后执行onSwiped
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return Int.MAX_VALUE.toFloat()
    }
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return Int.MAX_VALUE.toFloat()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        list.removeAt(viewHolder.adapterPosition)
        myadaptor.notifyItemRemoved(viewHolder.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun canDropOver(
        recyclerView: RecyclerView,
        current: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean//true 代表手指滑动 false 代表动画滑动
    ) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            if(dX == 0f){//第一次滑动，每次滑动dx = 0，记录下原始状态的值
                mcurrentdx = viewHolder.itemView.scrollX.toFloat()
                maxpx = dip2px(recyclerView.context,100f).toFloat()
                firstscrollx = true
            }
            //如果是手指滑动，那么按照手指滑动
            if(isCurrentlyActive){
                Log.d(TAG, "onChildDraw: "+viewHolder.itemView.scrollX+"    "+dX)
                if(dX<0){
                    viewHolder.itemView.scrollTo(Math.min(mcurrentdx-dX,maxpx).toInt(),0)
//                    ((viewHolder.itemView.findViewById(R.id.del))as TextView).scaleX=Math.min(mcurrentdx-dX/maxpx,1f)
                }else{
                    if(showDel){
                        //因为每次手指滑动，都会归位（原始状态）,所以要记录初始值，在这基础上进行距离运算
                        viewHolder.itemView.scrollTo(Math.max(mcurrentdx-dX,-maxpx).toInt(),0)
                    }else{
                        Log.d(TAG, "onChildDraw: 归位..............")
                        viewHolder.itemView.scrollTo(0,0)
                    }
                }

//                viewHolder.itemView.scrollTo((mcurrentdx-dX).toInt(),0)
            }else{//动画滑动
                Log.d(TAG, "onChildDraw: 动画滑动")
                if(firstscrollx){
                    firstscrollx = false
                    mCurrentScrollXWhenInactive = viewHolder.itemView.scrollX.toFloat()
                    mInitXWhenInactive = dX
                }
                //向左移动
                if( viewHolder.itemView.scrollX >= maxpx/2){
                    viewHolder.itemView.scrollTo(maxpx.toInt(),0)
                    showDel = true
                }else{
                    viewHolder.itemView.scrollTo(0,0)
                    showDel = false
                }
//                if(viewHolder.itemView.scrollX >= 100){
//                    viewHolder.itemView.scrollTo(0,0)
//                }
            }
        }else if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
            Log.d(TAG, "ACTION_STATE_DRAG: ")
            viewHolder.itemView.alpha =  0.5f
        } else if(actionState == ItemTouchHelper.ACTION_STATE_IDLE){
            Log.d(TAG, "ACTION_STATE_IDLE: ")
        } else{
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

    }

//TODO 方法在ItemView滑动完成以后会回调，因此想要实现侧滑ItemView停在某种状态，此方法是核心之点。
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
    if (viewHolder.itemView.getScrollX() > maxpx/2) {
        viewHolder.itemView.scrollTo(maxpx.toInt(), 0);
    } else if (viewHolder.itemView.getScrollX() < maxpx/2) {
        viewHolder.itemView.scrollTo(0, 0);
    }
    }
}