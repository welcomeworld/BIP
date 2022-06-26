package com.github.welcomeworld.bangumi.instrumentality.project.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.R;

@SuppressWarnings("unused")
public class BatteryView extends View {
    private int battery = 50; //max 100
    private String batteryText = "50%";
    private final Paint batteryPaint = new Paint();
    private final Paint textPaint = new Paint();
    private int[] batteryColors = {Color.GREEN, Color.RED};
    private float[] colorPositions = null;
    private Rect batteryRect = null;
    private boolean batteryVertical = false;
    private Drawable batteryWrapper;
    private int wrapperPaddingStart = 0;
    private int wrapperPaddingEnd = 0;
    private int wrapperPaddingTop = 0;
    private int wrapperPaddingBottom = 0;


    public BatteryView(Context context) {
        this(context, null, 0, 0);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BatteryView, defStyleAttr, defStyleRes);
        int start = typedArray.getDimensionPixelSize(R.styleable.BatteryView_batteryWrapperPaddingStart, 0);
        int end = typedArray.getDimensionPixelSize(R.styleable.BatteryView_batteryWrapperPaddingEnd, 0);
        int top = typedArray.getDimensionPixelSize(R.styleable.BatteryView_batteryWrapperPaddingTop, 0);
        int bottom = typedArray.getDimensionPixelSize(R.styleable.BatteryView_batteryWrapperPaddingBottom, 0);
        setBatteryWrapperPadding(start, end, top, bottom);
        setBattery(typedArray.getInt(R.styleable.BatteryView_battery, battery));
        batteryPaint.setAntiAlias(true);
        batteryPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        int textColor = typedArray.getColor(R.styleable.BatteryView_batteryTextColor, Color.BLACK);
        setTextColor(textColor);
        int textSize = typedArray.getDimensionPixelSize(R.styleable.BatteryView_batteryTextSize, 24);
        setTextSize(textSize);
        Drawable wrapper = typedArray.getDrawable(R.styleable.BatteryView_batteryWrapper);
        setBatteryWrapper(wrapper, wrapper.getIntrinsicWidth(), wrapper.getIntrinsicHeight());
        updateGradient();
        setBatteryVertical(typedArray.getBoolean(R.styleable.BatteryView_batteryVertical, batteryVertical));
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateRect();
        updateGradient();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getBatteryViewSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getBatteryViewSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    protected int getSuggestedMinimumWidth() {
        return (int) (getWrapperWidth() + getTextWidth());
    }

    protected int getSuggestedMinimumHeight() {
        return (int) Math.max(getWrapperHeight(), getTextWidth());
    }

    private int getBatteryViewSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = size;
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float textWidth = getTextWidth();
        //draw battery text
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float textHeight = fontMetrics.ascent / 2;
        canvas.drawText(batteryText, 0, getHeight() / 2f - textHeight, textPaint);
        //draw battery progress
        canvas.save();
        canvas.translate(textWidth + wrapperPaddingStart, (getHeight() - getWrapperHeight()) / 2f + wrapperPaddingTop);
        canvas.drawRect(batteryRect, batteryPaint);
        canvas.restore();
        //draw drawable
        canvas.save();
        canvas.translate(textWidth, (getHeight() - getWrapperHeight()) / 2f);
        batteryWrapper.draw(canvas);
        canvas.restore();
    }

    public void setBattery(int battery) {
        if (this.battery != battery) {
            this.battery = battery;
            this.batteryText = battery + "%";
            updateRect();
        }
    }

    public void setBatteryVertical(boolean vertical) {
        if (batteryVertical != vertical) {
            batteryVertical = vertical;
            updateRect();
        }
    }

    public void setBatteryColor(int batteryColor) {
        setBatteryColors(new int[]{batteryColor, batteryColor}, null);
    }

    public void setBatteryColors(int[] batteryColors, @Nullable float[] colorPositions) {
        this.batteryColors = batteryColors;
        this.colorPositions = colorPositions;
        updateGradient();
    }

    public void setTextColor(int color) {
        textPaint.setColor(color);
    }

    public void setTextSize(int px) {
        textPaint.setTextSize(px);
        updateRect();
    }

    private float getTextWidth() {
        return textPaint.measureText(batteryText);
    }

    private float getTextHeight() {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        return fontMetrics.top - fontMetrics.bottom;
    }

    private int getWrapperWidth() {
        if (batteryWrapper != null) {
            return batteryWrapper.getBounds().width();
        }
        return 0;
    }

    private int getWrapperHeight() {
        if (batteryWrapper != null) {
            return batteryWrapper.getBounds().height();
        }
        return 0;
    }

    public void setBatteryWrapper(Drawable batteryWrapper, int width, int height) {
        this.batteryWrapper = batteryWrapper;
        batteryWrapper.setBounds(0, 0, width, height);
        updateRect();
    }

    public void setBatteryWrapperPadding(int start, int end, int top, int bottom) {
        wrapperPaddingStart = start;
        wrapperPaddingEnd = end;
        wrapperPaddingTop = top;
        wrapperPaddingBottom = bottom;
        updateRect();
    }

    private void updateRect() {
        int rectWidth = getWrapperWidth() - wrapperPaddingStart - wrapperPaddingEnd;
        int rectHeight = getWrapperHeight() - wrapperPaddingTop - wrapperPaddingBottom;
        if (batteryVertical) {
            rectHeight = rectHeight * battery / 100;
        } else {
            rectWidth = rectWidth * battery / 100;
        }
        batteryRect = new Rect(0, 0, rectWidth, rectHeight);
        invalidate();
    }

    private void updateGradient() {
        LinearGradient linearGradient;
        if (batteryVertical) {
            linearGradient = new LinearGradient(0, batteryRect.height(), 0, getPaddingTop(), batteryColors, colorPositions, Shader.TileMode.CLAMP);
        } else {
            linearGradient = new LinearGradient(0, 0, batteryRect.width(), 0, batteryColors, colorPositions, Shader.TileMode.CLAMP);
        }
        batteryPaint.setShader(linearGradient);
        invalidate();
    }
}
