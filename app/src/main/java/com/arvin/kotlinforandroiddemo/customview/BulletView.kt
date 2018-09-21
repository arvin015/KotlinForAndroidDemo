package com.arvin.kotlinforandroiddemo.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class BulletView : SurfaceView, SurfaceHolder.Callback {

    private var mHolder: SurfaceHolder? = null

    private var needDraw = true

    private var mCanvas: Canvas? = null

    private var bulletList = CopyOnWriteArrayList<Bullet>()

    private var xList = mutableListOf<Float>()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mHolder = holder
        mHolder?.setKeepScreenOn(true)
        mHolder?.addCallback(this)
    }

    fun createBullet(text: String) {
        val bullet = Bullet(text, width.toFloat(), xList[Random().nextInt(xList.size)], RectF(0F, 0F, width.toFloat(), height.toFloat()))
        bullet.setListener {

        }
        bulletList.add(bullet)
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        needDraw = false
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {

        xList.clear()
        val count = height / 60
        val perH = height / count
        for (num in 1..count) {
            xList.add((num * perH - 30).toFloat())
        }

        Thread {

            while (true) {

                Thread.sleep(60)

                if (needDraw) try {
                    mCanvas = mHolder?.lockCanvas()
                    mCanvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                    for (bullet in bulletList) {
                        bullet.draw(mCanvas)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    mHolder?.unlockCanvasAndPost(mCanvas!!)
                }
            }

        }.start()
    }

    class Bullet(var text: String,
                 var originalX: Float,
                 var orininalY: Float,
                 var area: RectF,
                 var textColor: Int = Color.WHITE,
                 var textSize: Int = 40,
                 var moveSpeed: Float = 5F) {

        private val mPaint: Paint = Paint()

        private var currentX = 0F
        private var currentY = 0F

        private var listener: (() -> Unit)? = null

        init {

            currentX = originalX - moveSpeed
            currentY = orininalY

            mPaint.color = textColor
            mPaint.isAntiAlias = true
            mPaint.textSize = textSize.toFloat()
        }

        fun setListener(listener: () -> Unit) {
            this.listener = listener
        }

        fun draw(canvas: Canvas?) {
            canvas?.let {

                originalX -= moveSpeed

                canvas.drawText(text, originalX, orininalY, mPaint)
            }
        }

    }
}