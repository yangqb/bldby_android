package com.bldby.baselibrary.core.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bldby.baselibrary.R;


@SuppressLint("AppCompatCustomView")
public class CornerTextView extends TextView {
    public CornerTextView(Context context) {
        super(context);
        init();
    }

    public CornerTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CornerTextView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CornerTextView);
        cornerRadius = typedArray.getDimension(R.styleable.CornerTextView_cornerRadius,0.f);
        typedArray.recycle();

        init();
    }

    private final RectF roundRect = new RectF();
    private float cornerRadius = 0.f;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();

    private void init() {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
    }

    /** please set dp */
    public void setcornerRadius(float cornerRadius) {
        float density = getResources().getDisplayMetrics().density;
        this.cornerRadius = cornerRadius * density;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, cornerRadius, cornerRadius, zonePaint);
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }
}
