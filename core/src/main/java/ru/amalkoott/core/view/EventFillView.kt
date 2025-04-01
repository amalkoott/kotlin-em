package ru.amalkoott.core.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import ru.amalkoott.core.R
import kotlin.math.min
import kotlin.random.Random

class EventFillView @JvmOverloads constructor(
    context : Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var percentOfFilled = 0
    private var fillStep : Int = 10

    private val borderPaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private var center = Point()

    private var innerWidth : Float = width.toFloat()
    private var innerHeight : Float = height.toFloat()

    private var innerOffsetX : Float = 0f
    private var innerOffsetY : Float = 0f

    private val innerPaint = Paint().apply {
        style = Paint.Style.FILL
    }

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.EventFillView,
            defStyleAttr,
            0
        ).use { ta ->
            fillStep = ta.getInt(R.styleable.EventFillView_fillStep, 10)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val desiredWidth = 400
        val desiredHeight = 400

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(desiredWidth, widthSize)
            else -> desiredWidth //
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // рамка view
        canvas.drawRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            borderPaint
        )

        // painter для заливки
        innerPaint.apply {
            color = getRandomColor()
        }

        // заливка
        canvas.drawRect(
            center.x - innerOffsetX,
            center.y - innerOffsetY,
            innerWidth + center.x - innerOffsetX,
            innerHeight + center.y - innerOffsetY,
            innerPaint
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                updatePercentOfFilled()
                updateInnerSize()
                true
            }
            MotionEvent.ACTION_UP -> {
                invalidate()
                true
            }
            else -> super.onTouchEvent(event)
        }
    }

    private fun getRandomColor(): Int {
        val random = Random.Default
        return Color.rgb(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )
    }

    private fun updateInnerSize(){
        center = Point(width / 2, height / 2)

        innerWidth = (width / 100 * percentOfFilled).toFloat()
        innerHeight = (height / 100 * percentOfFilled).toFloat()

        innerOffsetX = innerWidth / 2
        innerOffsetY = innerHeight / 2
    }

    private fun updatePercentOfFilled(){
        if (percentOfFilled < 100){
            percentOfFilled += fillStep
        }else{
            percentOfFilled = 0
        }
    }
}