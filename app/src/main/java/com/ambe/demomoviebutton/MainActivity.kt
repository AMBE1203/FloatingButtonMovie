package com.ambe.demomoviebutton

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.PopupWindow
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity(), View.OnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ln_View.setOnTouchListener(this)
    }

    private var downRawX = 0f
    private var downRawY = 0f
    private var dX = 0f
    private var dY = 0f
    private var upRawX: Int = 0
    private var upRawY: Int = 0
    private val pw: PopupWindow? = null
    private var check = false
    private val CLICK_DRAG_TOLERANCE = 10
    private var lastXAxis = 0f
    private var lastYAxis = 0f

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            downRawX = event.rawX
            downRawY = event.rawY
            dX = v.x - downRawX
            dY = v.y - downRawY

            lastXAxis = event.x
            lastYAxis = event.y


        } else if (event.action == MotionEvent.ACTION_MOVE) {
            val viewWidth = v.width
            val viewHeight = v.height
            val viewParent = v.parent as View
            val parentWidth = viewParent.width
            val parentHeight = viewParent.height
            var newX = event.rawX + dX
            newX = max(0f, newX)
            newX = min((parentWidth - viewWidth).toFloat(), newX)
            var newY = event.rawY + dY
            newY = max(0f, newY)
            newY = min((parentHeight - viewHeight).toFloat(), newY)

            val dx = abs(event.x - lastXAxis)
            val dy = abs(event.y - lastYAxis)
            v.animate().x(newX).y(newY).setDuration(0).start()
            if (pw != null && dx > 2 || pw != null && dy > 2) {
                pw.dismiss()
                check = false


            }
            //
        } else if (event.action == MotionEvent.ACTION_UP) {
            upRawX = event.rawX.toInt()
            upRawY = event.rawY.toInt()

            val upDX = (upRawX - downRawX).toInt()
            val upDY = (upRawY - downRawY).toInt()
            if (abs(upDX) < CLICK_DRAG_TOLERANCE && abs(upDY) < CLICK_DRAG_TOLERANCE) {
                // todo click view
                Log.e("AMBE12030204", "click me")


            }

        }
        return true
    }
}
