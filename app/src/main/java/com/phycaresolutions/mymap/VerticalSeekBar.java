package com.phycaresolutions.mymap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatSeekBar;

public class VerticalSeekBar extends AppCompatSeekBar {

    private Paint mDotPaint;
    private float mDotRadius = 8f; // Radius of the dot
    private int mDotSpacing = 20; // Distance between the dots
    private int mStepSize = 10; // Define the step size (this will be the "discrete" interval)

    public VerticalSeekBar(Context context) {
        super(context);
        init();
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDotPaint = new Paint();
        mDotPaint.setColor(Color.BLUE); // Dot color
        mDotPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Swap width and height to make it vertical
        setMeasuredDimension(h, w);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // Adjust layout for vertical orientation
        setLayoutParams(getLayoutParams());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Save the canvas state
        canvas.save();

        // Rotate the canvas to draw vertically
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);

        // Calculate the number of dots based on the progress
        int progress = getProgress();
        int maxProgress = getMax();

        // Number of dots to draw
        int numDots = (int) ((float) progress / maxProgress * getHeight() / mDotSpacing);

        // Draw the dots
        for (int i = 0; i < numDots; i++) {
            float yPos = i * mDotSpacing + mDotRadius;
            canvas.drawCircle(getWidth() / 2, yPos, mDotRadius, mDotPaint); // Draw dots along the vertical line
        }

        // Restore the canvas state
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // Handle touch event and adjust progress with discrete steps
                float y = event.getY();
                // Calculate progress based on vertical position
                float progress = (getHeight() - y) / (float) getHeight();

                // Snap the progress to discrete steps
                int discreteProgress = Math.round(progress * getMax() / mStepSize) * mStepSize;
                setProgress(discreteProgress);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    // Optionally, add a setter for step size if needed
    public void setStepSize(int stepSize) {
        mStepSize = stepSize;
    }
}


