package com.vertigo.andersenhomework3.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class Colombia(context: Context, attr: AttributeSet? = null): View(context, attr) {
    private val painter = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFirst(canvas)
        drawSecond(canvas)
        drawThird(canvas)
        drawStrokes(canvas)
    }

    private fun drawFirst(canvas: Canvas?) {
        canvas?.apply {
            val leftSide = width / 2f - width / 2f
            val rightSide = width.toFloat()
            val topSide = 0f
            val botSide = (height / 2).toFloat()
            painter.color = Color.YELLOW
            Log.d("AppVerbose", "$leftSide, $rightSide, $topSide, $botSide")
            drawRect(leftSide, topSide, rightSide, botSide, painter)
        }
    }

    private fun drawSecond(canvas: Canvas?) {
        canvas?.apply {
            val leftSide = width / 3f - width / 3f
            val rightSide = width.toFloat()
            val topSide = (height / 2).toFloat()
            val botSide = (height / 2 + height / 2).toFloat()
            painter.color = Color.BLUE
            Log.d("AppVerbose", "$leftSide, $rightSide, $topSide, $botSide")
            drawRect(leftSide, topSide, rightSide, botSide, painter)
        }
    }

    private fun drawThird(canvas: Canvas?) {
        canvas?.apply {
            val leftSide = width / 3f - width / 3f
            val rightSide = width.toFloat()
            val topSide = height - (height / 4).toFloat()
            val botSide = height.toFloat()
            painter.color = Color.RED
            Log.d("AppVerbose", "$leftSide, $rightSide, $topSide, $botSide")
            drawRect(leftSide, topSide, rightSide, botSide, painter)
        }
    }

    private fun drawStrokes(canvas: Canvas?) {
        canvas?.apply {
            painter.color = Color.BLACK
            painter.style = Paint.Style.STROKE
            painter.strokeWidth = 5f
            drawRect(3f, 3f, width.toFloat(), width.toFloat() + 73, painter)
        }
    }
}