package com.vertigo.andersenhomework3.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class Poland(context: Context, attr: AttributeSet? = null): View(context, attr) {
    private val painter = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFirst(canvas)
        drawSecond(canvas)
        drawStrokes(canvas)
    }

    private fun drawFirst(canvas: Canvas?) {
        canvas?.apply {
            val leftSide = width / 3f - width / 3f
            val rightSide = width.toFloat()
            val topSide = 0f
            val botSide = (height / 2).toFloat()
            painter.color = Color.WHITE
            Log.d("AppVerbose", "$leftSide, $rightSide, $topSide, $botSide")
            drawRect(leftSide, topSide, rightSide, botSide, painter)
        }
    }

    private fun drawSecond(canvas: Canvas?) {
        canvas?.apply {
            val leftSide = width / 3f - width / 3f
            val rightSide = width.toFloat()
            val topSide = height - (height / 2).toFloat()
            val botSide = height.toFloat()
            painter.color = Color.RED
            Log.d("AppVerbose", "$leftSide, $rightSide, $topSide, $botSide")
            drawRect(leftSide, topSide, rightSide, botSide, painter)
        }
    }

    private fun  drawStrokes(canvas: Canvas?) {
        canvas?.apply {
            painter.color = Color.BLACK
            painter.style = Paint.Style.STROKE
            painter.strokeWidth = 5f
            drawRect(1f, 1f, width.toFloat() - 1, width.toFloat() + 75, painter)
        }
    }
}