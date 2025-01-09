package com.phycaresolutions.mymap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ConnectDotsView extends View {



    int[][] originalCatPoints ; /*{
            {300, 100}, // Left ear tip
            {200, 200}, // Left ear base
            {400, 200}, // Right ear base
            {500, 100}, // Right ear tip
            {350, 300}, // Chin
            {250, 250}, // Left cheek
            {450, 250}, // Right cheek
            {200, 400}, // Left body
            {500, 400}, // Right body
            {350, 500}  // Tail start
    };*/

    private int[][] catPoints; // Adjusted coordinates for centering

    private Paint dotPaint;
    private Paint linePaint;
    private Paint textPaint;

    private final List<int[]> selectedDots = new ArrayList<>();
    private boolean isCompleted = false;

    private int currentImageIndex = 0;
    private Bitmap catImage;

    public ConnectDotsView(Context context, int[][] originalCatPoints, int dog) {
        super(context);
        this.originalCatPoints =originalCatPoints;
        initializeCatDots();
        init(); // 
    }

    public ConnectDotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void init(){


        // Initialize paints
        dotPaint = new Paint();
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(8);
        linePaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40f);
        textPaint.setAntiAlias(true);

        // Load the cat image
        catImage = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Calculate the bounding box of the original points
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] point : originalCatPoints) {
            minX = Math.min(minX, point[0]);
            maxX = Math.max(maxX, point[0]);
            minY = Math.min(minY, point[1]);
            maxY = Math.max(maxY, point[1]);
        }

        // Calculate the offsets to center the pattern
        int patternWidth = maxX - minX;
        int patternHeight = maxY - minY;
        int offsetX = (w - patternWidth) / 2 - minX;
        int offsetY = (h - patternHeight) / 2 - minY;

        // Adjust the coordinates of the cat points
        catPoints = new int[originalCatPoints.length][2];
        for (int i = 0; i < originalCatPoints.length; i++) {
            catPoints[i][0] = originalCatPoints[i][0] + offsetX;
            catPoints[i][1] = originalCatPoints[i][1] + offsetY;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       /* // Draw the dots with numbers
        for (int i = 0; i < catPoints.length; i++) {
            int[] point = catPoints[i];
            dotPaint.setColor(i == 0 ? Color.RED : Color.BLACK); // First dot is red
            canvas.drawCircle(point[0], point[1], 20, dotPaint); // Draw the dot
            canvas.drawText(String.valueOf(i + 1), point[0] + 25, point[1], textPaint); // Draw the number
        }

        // Draw the lines between connected dots
        for (int i = 0; i < selectedDots.size() - 1; i++) {
            int[] start = selectedDots.get(i);
            int[] end = selectedDots.get(i + 1);
            canvas.drawLine(start[0], start[1], end[0], end[1], linePaint);
        }

        // Display the cat image when completed
        if (isCompleted && catImage != null) {
            int centerX = (canvas.getWidth() - catImage.getWidth()) / 2;
            int centerY = (canvas.getHeight() - catImage.getHeight()) / 2;


            canvas.drawBitmap(catImage, centerX, centerY, null);

            // Draw a completion message
            Paint textPaint = new Paint();
            textPaint.setColor(Color.GREEN);
            textPaint.setTextSize(60f);
            canvas.drawText("Pattern Completed!", canvas.getWidth() / 4, canvas.getHeight() - 100, textPaint);
            resetPattern();
            initializeCatDots();
        }*/
        // Draw dots
        if (catPoints != null) {
            for (int[] point : catPoints) {
                canvas.drawCircle(point[0], point[1], 20, dotPaint);
            }
        }

        // Draw lines between connected dots
        if (!selectedDots.isEmpty()) {
            for (int i = 0; i < selectedDots.size() - 1; i++) {
                int[] start = selectedDots.get(i);
                int[] end = selectedDots.get(i + 1);
                canvas.drawLine(start[0], start[1], end[0], end[1], linePaint);
            }
        }

        // If completed, display the current image
        if (isCompleted) {
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
            int centerX = (canvas.getWidth() - image.getWidth()) / 2;
            int centerY = (canvas.getHeight() - image.getHeight()) / 2;
            canvas.drawBitmap(image, centerX, centerY, null);

            Paint textPaint = new Paint();
            textPaint.setColor(Color.GREEN);
            textPaint.setTextSize(60);
            canvas.drawText("Pattern Completed!", canvas.getWidth() / 4, canvas.getHeight() - 100, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                for (int[] point : catPoints) {
                    // Check if the touch is near a dot
                    if (Math.hypot(x - point[0], y - point[1]) < 50) {
                        if (!isDotAlreadySelected(point)) {
                            selectedDots.add(point);
                            invalidate(); // Redraw the view
                        }
                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                // Check if the pattern is completed
                if (selectedDots.size() == catPoints.length) {
                    isCompleted = true;
                    invalidate(); // Redraw to show completion message
                }
                break;
        }
        return true;
    }

    private boolean isDotAlreadySelected(int[] dot) {
        for (int[] selectedDot : selectedDots) {
            if (selectedDot[0] == dot[0] && selectedDot[1] == dot[1]) {
                return true;
            }
        }
        return false;
    }
    public void resetPattern() {
        selectedDots.clear();   // Clear the selected dots
        isCompleted = false;    // Reset the completion flag

        // Clear all dots by reinitializing the catPoints array
        catPoints = null;

        invalidate();             // Redraw the view
    }
    public void initializeCatDots() {
        catPoints = new int[][]{
                {300, 100}, {200, 200}, {400, 200}, {500, 100}, {350, 300},
                {250, 250}, {450, 250}, {200, 400}, {500, 400}, {350, 500}
        };
        invalidate(); // Redraw the view
    }

    /*private final int[][] catPoints = {
            {300, 100}, // Left ear tip
            {200, 200}, // Left ear base
            {400, 200}, // Right ear base
            {500, 100}, // Right ear tip
            {350, 300}, // Chin
            {250, 250}, // Left cheek
            {450, 250}, // Right cheek
            {200, 400}, // Left body
            {500, 400}, // Right body
            {350, 500}  // Tail start
    };

    private final Paint dotPaint;
    private final Paint linePaint;
    private final Paint textPaint;

    private final List<int[]> selectedDots = new ArrayList<>();
    private boolean isCompleted = false;

    private Bitmap catImage;

    public ConnectDotsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize paint for dots
        dotPaint = new Paint();
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setAntiAlias(true);

        // Initialize paint for lines
        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(8);
        linePaint.setStyle(Paint.Style.STROKE);

        // Initialize paint for text (numbers)
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40f);
        textPaint.setAntiAlias(true);

        // Load the cat image
        catImage = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the dots with numbers
        for (int i = 0; i < catPoints.length; i++) {
            int[] point = catPoints[i];
            dotPaint.setColor(i == 0 ? Color.RED : Color.BLACK); // First dot is red
            canvas.drawCircle(point[0], point[1], 20, dotPaint); // Draw the dot
            canvas.drawText(String.valueOf(i + 1), point[0] + 25, point[1], textPaint); // Draw the number
        }

        // Draw the lines between connected dots
        for (int i = 0; i < selectedDots.size() - 1; i++) {
            int[] start = selectedDots.get(i);
            int[] end = selectedDots.get(i + 1);
            canvas.drawLine(start[0], start[1], end[0], end[1], linePaint);
        }

        // Display the cat image when completed
        if (isCompleted && catImage != null) {
            int centerX = (canvas.getWidth() - catImage.getWidth()) / 2;
            int centerY = (canvas.getHeight() - catImage.getHeight()) / 2;
            canvas.drawBitmap(catImage, centerX, centerY, null);

            // Draw a completion message
            Paint textPaint = new Paint();
            textPaint.setColor(Color.GREEN);
            textPaint.setTextSize(60f);
            canvas.drawText("Pattern Completed!", canvas.getWidth() / 4, canvas.getHeight() - 100, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                for (int[] point : catPoints) {
                    // Check if the touch is near a dot
                    if (Math.hypot(x - point[0], y - point[1]) < 50) {
                        if (!isDotAlreadySelected(point)) {
                            selectedDots.add(point);
                            invalidate(); // Redraw the view
                        }
                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                // Check if the pattern is completed
                if (selectedDots.size() == catPoints.length) {
                    isCompleted = true;
                    invalidate(); // Redraw to show completion message
                }
                break;
        }
        return true;
    }

    private boolean isDotAlreadySelected(int[] dot) {
        for (int[] selectedDot : selectedDots) {
            if (selectedDot[0] == dot[0] && selectedDot[1] == dot[1]) {
                return true;
            }
        }
        return false;
    }*/

   /* // Coordinates for the cat shape
    private final int[][] catPoints = {
            {300, 100}, // Left ear tip
            {200, 200}, // Left ear base
            {400, 200}, // Right ear base
            {500, 100}, // Right ear tip
            {350, 300}, // Chin
            {250, 250}, // Left cheek
            {450, 250}, // Right cheek
            {200, 400}, // Left body
            {500, 400}, // Right body
            {350, 500} ,
            {300, 100}// Tail start
    };

    private final List<int[]> selectedDots = new ArrayList<>();
    private final Paint dotPaint;
    private final Paint linePaint;
    private boolean isCompleted = false;
    private Bitmap dogImage; // Holds the dog image

    public ConnectDotsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Paint for dots
        dotPaint = new Paint();
        dotPaint.setColor(Color.BLACK);
        dotPaint.setStyle(Paint.Style.FILL);

        // Paint for lines
        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(8);
        linePaint.setStyle(Paint.Style.STROKE);
        // Load the dog image
        dogImage = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the dots for the cat shape
        for (int[] point : catPoints) {
            canvas.drawCircle(point[0], point[1], 30, dotPaint);
        }

        // Draw lines between connected dots
        if (!selectedDots.isEmpty()) {
            for (int i = 0; i < selectedDots.size() - 1; i++) {
                int[] start = selectedDots.get(i);
                int[] end = selectedDots.get(i + 1);
                canvas.drawLine(start[0], start[1], end[0], end[1], linePaint);
            }
        }

        // If completed, optionally display a message or image
        if (isCompleted) {
            Paint textPaint = new Paint();
            textPaint.setColor(Color.GREEN);
            textPaint.setTextSize(60);
            canvas.drawText("Pattern Completed!", 200, 700, textPaint);
            canvas.drawBitmap(dogImage, (canvas.getWidth() - dogImage.getWidth()) / 2,
                    (canvas.getHeight() - dogImage.getHeight()) / 2, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                for (int[] point : catPoints) {
                    // Check if touch is near a dot
                    if (Math.hypot(x - point[0], y - point[1]) < 50) {
                        if (!isDotAlreadySelected(point)) {
                            selectedDots.add(point);
                            invalidate(); // Redraw the view
                        }
                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                // Check if the pattern is completed
                if (selectedDots.size() == catPoints.length) {
                    isCompleted = true;
                    invalidate(); // Redraw to show completion message
                }
                break;
        }

        return true;
    }

    private boolean isDotAlreadySelected(int[] dot) {
        for (int[] selectedDot : selectedDots) {
            if (selectedDot[0] == dot[0] && selectedDot[1] == dot[1]) {
                return true;
            }
        }
        return false;
    }*/
    // Coordinates for a dog shape
   /* private final int[][] points = {
            {0, 0},     // Head
            {40, -40},  // Ears
            {80, 0},    // Top of head
            {120, 40},  // Neck
            {160, 100}, // Back
            {120, 200}, // Tail
            {40, 300},  // Back legs
            {-40, 300}, // Belly
            {-80, 200}, // Front legs
            {-40, 100}, // Chest
            {-20, 40}   // Back to head
    };

    private int currentIndex = 0; // Tracks the current dot to connect
    private boolean isCompleted = false; // Tracks if all dots are connected
    private final Paint dotPaint;
    private final Paint linePaint;
    private Bitmap dogImage; // Holds the dog image

    public ConnectDotsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize paint for dots
        dotPaint = new Paint();
        dotPaint.setColor(Color.BLACK);
        dotPaint.setStyle(Paint.Style.FILL);

        // Initialize paint for lines
        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(12); // Increased stroke width

        // Load the dog image
        dogImage = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isCompleted) {
            // Draw the dog image when all dots are connected
            canvas.drawBitmap(dogImage, (canvas.getWidth() - dogImage.getWidth()) / 2,
                    (canvas.getHeight() - dogImage.getHeight()) / 2, null);
            return;
        }

        // Get the center of the canvas
        int canvasCenterX = canvas.getWidth() / 2;
        int canvasCenterY = canvas.getHeight() / 2;

        // Calculate the offsets to center the shape
        int shapeCenterX = 80; // Approximate X center of the shape (dog points)
        int shapeCenterY = 150; // Approximate Y center of the shape (dog points)

        int offsetX = canvasCenterX - shapeCenterX;
        int offsetY = canvasCenterY - shapeCenterY;

        // Draw the dots and lines
        for (int i = 0; i < points.length; i++) {
            int adjustedX = points[i][0] + offsetX;
            int adjustedY = points[i][1] + offsetY;

            if (i < currentIndex) {
                dotPaint.setColor(Color.RED); // Connected dots
            } else {
                dotPaint.setColor(Color.BLACK); // Remaining dots
            }
            canvas.drawCircle(adjustedX, adjustedY, 30, dotPaint); // Draw the dot
        }

        // Draw the lines between connected dots
        for (int i = 0; i < currentIndex - 1; i++) {
            int startX = points[i][0] + offsetX;
            int startY = points[i][1] + offsetY;
            int endX = points[i + 1][0] + offsetX;
            int endY = points[i + 1][1] + offsetY;

            canvas.drawLine(startX, startY, endX, endY, linePaint); // Draw the line
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            // Get the center offset for touch detection
            int canvasCenterX = getWidth() / 2;
            int canvasCenterY = getHeight() / 2;

            int shapeCenterX = 80;
            int shapeCenterY = 150;

            int offsetX = canvasCenterX - shapeCenterX;
            int offsetY = canvasCenterY - shapeCenterY;

            if (currentIndex < points.length) {
                int px = points[currentIndex][0] + offsetX;
                int py = points[currentIndex][1] + offsetY;

                // Check if the touch is near the current point
                if (Math.abs(x - px) < 50 && Math.abs(y - py) < 50) {
                    currentIndex++;
                    if (currentIndex == points.length) {
                        isCompleted = true; // Mark as completed
                    }
                    invalidate(); // Redraw the view
                }
            }
            return true;
        }
        return super.onTouchEvent(event);
    }*/
}

   /* private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private static final int TOUCH_TOLERANCE_DP = 24;
    private static final int BACKGROUND = 0xFFDDDDDD;
    // Points to be connected.
    private List<Point> mPoints = new ArrayList<Point>();
    private int mLastPointIndex = 0;
    private int mTouchTolerance;
    private boolean isPathStarted = false;

    public ConnectDotsView(Context context) {
        super(context);
        mCanvas = new Canvas();
        mPath = new Path();
        initPaint();
    }

    public ConnectDotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCanvas = new Canvas();
        mPath = new Path();
        initPaint();
    }

    public ConnectDotsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCanvas = new Canvas();
        mPath = new Path();
        initPaint();
    }

    public void clear() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mBitmap.eraseColor(BACKGROUND);
        mCanvas.setBitmap(mBitmap);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        clear();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(BACKGROUND);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawPath(mPath, mPaint);

        // TODO remove if you don't want points to be visible.
        for (Point point : mPoints) {
            canvas.drawPoint(point.x, point.y, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up(x, y);
                invalidate();
                break;
        }
        return true;
    }

    private void touch_start(float x, float y) {

        if (checkPoint(x, y, mLastPointIndex)) {
            mPath.reset();
            // User starts from given point so path can be drawn.
            isPathStarted = true;
        } else {
            // User starts move from point which does not belong to mPoints list
            isPathStarted = false;
        }

    }

    private void touch_move(float x, float y) {
        if (isPathStarted) {
            mPath.reset();
            Point point = mPoints.get(mLastPointIndex);
            mPath.moveTo(point.x, point.y);
            if (checkPoint(x, y, mLastPointIndex + 1)) {
                point = mPoints.get(mLastPointIndex + 1);
                mPath.lineTo(point.x, point.y);
                mCanvas.drawPath(mPath, mPaint);
                mPath.reset();
                ++mLastPointIndex;
            } else {
                mPath.lineTo(x, y);
            }
        }
    }

    private void touch_up(float x, float y) {
        mPath.reset();
        if (checkPoint(x, y, mLastPointIndex + 1) && isPathStarted) {
            // Move finished at valid point so I draw whole line.
            // That's the start point of current line segment.
            Point point = mPoints.get(mLastPointIndex);
            mPath.moveTo(point.x, point.y);
            // And that's the end point.
            point = mPoints.get(mLastPointIndex + 1);
            mPath.lineTo(point.x, point.y);
            mCanvas.drawPath(mPath, mPaint);
            mPath.reset();
            // Increment point index.
            ++mLastPointIndex;
            isPathStarted = false;
        }

    }

    *//**
     * Checks if user touch point with some tolerance
     *//*
    private boolean checkPoint(float x, float y, int pointIndex) {
        if (pointIndex >= mPoints.size()) {
            // All dots already connected.
            return false;
        }
        Point point = mPoints.get(pointIndex);
        if (x > (point.x - mTouchTolerance) && x < (point.x + mTouchTolerance)) {
            if (y > (point.y - mTouchTolerance) && y < (point.y + mTouchTolerance)) {
                return true;
            }
        }
        return false;
    }

    *//**
     * Sets up paint attributes.
     *//*
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);

        mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);
    }

    *//**
     * Converts dpi units to px
     *
     * @param dp
     * @return
     *//*
    private int dp2px(int dp) {
        Resources r = getContext().getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }

    public void setPaint(Paint paint) {
        this.mPaint = paint;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public List<Point> getPoints() {
        return mPoints;
    }

    public void setPoints(List<Point> points) {
        this.mPoints = points;
    }
}*/