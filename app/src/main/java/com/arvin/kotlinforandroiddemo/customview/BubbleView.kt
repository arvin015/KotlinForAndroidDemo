package com.arvin.kotlinforandroiddemo.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class BubbleView : View {

    private var viewW = 0
    private var viewH = 0

    private var bubbleList = CopyOnWriteArrayList<Bubble>()

    private val bubblePaint = Paint()
    private val glassPaint = Paint()

    private var mThread: Thread? = null

    private var containerRectF: RectF? = null

    private var needPlay = true

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        bubblePaint.style = Paint.Style.FILL
        bubblePaint.isAntiAlias = true
        bubblePaint.color = Color.parseColor("#bdc5dc")

        glassPaint.style = Paint.Style.FILL
        glassPaint.isAntiAlias = true
        glassPaint.color = Color.parseColor("#4374d0")
    }

    fun startBubbling() {

        stopBubbing()

        mThread = Thread {
            while (true) {
                if (needPlay) {
                    Thread.sleep(60)
                    if (Random().nextFloat() > 0.92F) createBubbles()
                    postInvalidate()
                }
            }
        }
        mThread!!.start()
    }

    fun pauseBubbing() {
        needPlay = false
    }

    fun resumeBubbing() {
        needPlay = true
    }

    fun stopBubbing() {
        if (mThread != null) {
            mThread!!.interrupt()
            mThread = null
        }
    }

    private fun createBubbles() {
        if (containerRectF != null) {
            val bubble = Bubble((viewW / 2).toFloat(), (viewH - 20).toFloat(), containerRectF!!)
            bubble.setListener {
                bubbleList.remove(bubble)
            }
            bubbleList.add(bubble)
        }
    }

    private fun drawBubbles(canvas: Canvas) {
        for (bubble in bubbleList) {
            bubble.draw(canvas, bubblePaint)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        viewW = w
        viewH = h

        containerRectF = RectF(0F, 0F, viewW.toFloat(), viewH.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(containerRectF, glassPaint)

        drawBubbles(canvas!!)
    }

    class Bubble(originalX: Float, originalY: Float, private val rectF: RectF) {

        private val maxSpeedX = 3
        private val maxSpeedY = 8
        private val maxRadius = 20

        private var speedX = 0F
        private var speedY = 0F
        private var radius = 0

        private var currentX = 0F
        private var currentY = 0F

        init {
            do {
                speedX = (Random().nextFloat() * maxSpeedX) - 1.5F
                speedY = 0.5F + Random().nextFloat() * maxSpeedY
            } while (Math.abs(speedX) >= Math.abs(speedY))

            radius = Random().nextInt(maxRadius - 10 + 1) + 10
            currentX = originalX + speedX
            currentY = originalY + speedY
        }

        private var listener: (() -> Unit)? = null

        fun setListener(listener: () -> Unit) {
            this.listener = listener
        }

        fun draw(canvas: Canvas, paint: Paint) {

            currentX += speedX
            currentY -= speedY

            if (!rectF.contains(currentX, currentY)) {
                listener?.invoke()
                return
            }

            canvas.drawCircle(currentX, currentY, radius.toFloat(), paint)
        }
    }
}