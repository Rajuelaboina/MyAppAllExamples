package com.phycaresolutions.demoapp_2025

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.phycaresolutions.mymap.R

class DotToDotView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    // List of dot positions (x, y) and labels (dot numbers)
    private val dotPositions = listOf(
        Pair(100f, 800f) to "1",
        Pair(200f, 700f) to "2",
        Pair(300f, 650f) to "3",
        Pair(400f, 600f) to "4",
        Pair(500f, 550f) to "5",
        Pair(600f, 500f) to "6",
        Pair(550f, 600f) to "7",
        Pair(450f, 700f) to "8",
        Pair(350f, 800f) to "9",
        Pair(250f, 850f) to "10"
    )

    // Paint objects for dots, lines, and labels
    private val dotPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 40f
        isAntiAlias = true
    }

    private val linePaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        isAntiAlias = true
    }

    // Tracks connected dots
    private val connectedDots = mutableListOf<Pair<Float, Float>>()

    // Bitmap for the complete image
    private val completeImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat)

    // Flag for completion
    private var isGameComplete = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw dots and labels
        dotPositions.forEach { (position, label) ->
            val (x, y) = position
            canvas.drawCircle(x, y, 20f, dotPaint) // Draw dot
            canvas.drawText(label, x + 30f, y - 20f, textPaint) // Draw label
        }

        // Draw lines between connected dots
        for (i in 1 until connectedDots.size) {
            val (startX, startY) = connectedDots[i - 1]
            val (endX, endY) = connectedDots[i]
            canvas.drawLine(startX, startY, endX, endY, linePaint)
        }

        // Reveal parts of the image for each connected dot
        for (i in connectedDots.indices) {
            val region = getImageRegion(i)
            canvas.drawBitmap(completeImage, region, getScreenRegion(region), null)
        }

        // If the game is complete, show a completion message
        if (isGameComplete) {
            textPaint.color = Color.GREEN
            textPaint.textSize = 60f
            canvas.drawText("Game Complete!", width / 2f - 150f, height / 2f, textPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isGameComplete) return true // Stop interaction if game is complete

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val touchX = event.x
                val touchY = event.y

                // Find the nearest dot
                val nearestDot = dotPositions.find { (position, _) ->
                    val (dotX, dotY) = position
                    Math.sqrt(
                        Math.pow((dotX - touchX).toDouble(), 2.0) +
                                Math.pow((dotY - touchY).toDouble(), 2.0)
                    ) < 50 // Threshold for detecting touch near a dot
                }

                if (nearestDot != null) {
                    // Check if the dot is the next in sequence
                    val expectedDot = dotPositions[connectedDots.size]
                    if (nearestDot == expectedDot) {
                        connectedDots.add(nearestDot.first) // Add to connected dots
                        invalidate() // Redraw the view

                        // Check if the game is complete
                        if (connectedDots.size == dotPositions.size) {
                            isGameComplete = true
                            Toast.makeText(context, "You completed the game!", Toast.LENGTH_SHORT).show()
                            invalidate()
                        }
                    } else {
                        Toast.makeText(context, "Wrong dot! Follow the sequence.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return true
    }

    /**
     * Gets the region of the complete image corresponding to the given dot index.
     */
    private fun getImageRegion(index: Int): Rect {
        val imageWidth = completeImage.width
        val imageHeight = completeImage.height

        val regionWidth = imageWidth / dotPositions.size
        return Rect(
            index * regionWidth,
            0,
            (index + 1) * regionWidth,
            imageHeight
        )
    }

    /**
     * Maps the image region to the screen coordinates for partial display.
     */
    private fun getScreenRegion(imageRegion: Rect): RectF {
        val screenWidth = width.toFloat()
        val screenHeight = height.toFloat()

        val regionWidth = screenWidth / dotPositions.size
        return RectF(
            imageRegion.left * (screenWidth / completeImage.width),
            0f,
            imageRegion.right * (screenWidth / completeImage.width),
            screenHeight
        )
    }
}





