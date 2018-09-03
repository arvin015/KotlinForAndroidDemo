package com.arvin.kotlinforandroiddemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint: Paint? = null

    var tempX = 0f
    var tempY = 0f

    init {
        paint = Paint()
        paint!!.color = Color.RED
        paint!!.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.drawCircle(20f, 20f, 20f, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {

            val x = event.rawX
            val y = event.rawY

            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    tempX = x - getX()
                    tempY = y - getY()
                }
                MotionEvent.ACTION_MOVE -> {
                    if (tempX != 0f && tempY != 0f) {
                        setX(x - tempX)
                        setY(y - tempY)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    tempX = 0f
                    tempY = 0f
                }
            }
            return true
        }

        return false
    }
}