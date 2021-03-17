package com.example.scanninganimation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat


class ScanningView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), ValueAnimator.AnimatorUpdateListener {

    private var paint1: Paint = Paint()
    private var paint2: Paint = Paint()
    private var mHeight: Int = 0
    var mAnimator: ValueAnimator? = null


    init {

        paint1.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            shader = getGradient()
            alpha = 70
        }


        paint2.apply {
            style = Paint.Style.STROKE
            strokeWidth = 20f
            isAntiAlias = true
            shader = getGradient()
        }

        Log.d("sdasddssa", "init: ${height}")

    }

    private fun getGradient(): LinearGradient {

        var colors = intArrayOf(
            ContextCompat.getColor(context, R.color.primary_color_one),
            ContextCompat.getColor(context, R.color.primary_color_two),

            )
        var positions = floatArrayOf(0.0f, 0.1f)

        return (LinearGradient(
            0f,
            0f,
            height.toFloat(),
            0f,
            colors,
            positions,
            Shader.TileMode.CLAMP
        ))
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {

            drawRect(0f, 0f, width.toFloat(), mHeight.toFloat(), paint1)

            drawLine(0f, mHeight.toFloat() - 10, width.toFloat(), mHeight.toFloat() - 10, paint2)
        }
//        Log.d("sdasddssa", "onDraw: ${height}")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        Log.d("sdasddssa", "onMeasure: ${height}")

//        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
//        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        Log.d("sdasddssa", "onMeasure heightSize: ${heightSize}")
        setMeasuredDimension(widthSize, heightSize);
        startAnim(heightSize)

    }

    private fun startAnim(maxValue: Int) {

        mAnimator = ValueAnimator.ofInt(0, maxValue).apply {
            duration = 600
            repeatMode = ValueAnimator.REVERSE
//            interpolator = AccelerateInterpolator()
            repeatCount = -1
            start()
        }
        mAnimator?.addUpdateListener(this)
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {

        val value = animation?.animatedValue as Int
        Log.d("aadada", "onAnimationUpdate: $value")
        mHeight = value

        invalidate()
    }


}