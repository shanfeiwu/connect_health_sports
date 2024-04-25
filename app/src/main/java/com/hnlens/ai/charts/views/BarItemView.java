package com.hnlens.ai.charts.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.hnlens.ai.sports.R;
import com.hnlens.ai.utils.Constants;
import com.hnlens.ai.utils.DisplayUtils;

public class BarItemView extends View {

    private Context mContext;
    private Paint linePaint;
    private int barColir;
    private int lineColor;
    private int moneyColor;
    private int timeColor;
    private Paint barPaint;
    private Paint moneyPaint;
    private Paint timePaint;
    private int width;
    private int heigt;
    private int lineHeigt;
    private int lineWidth;
    private int barHeight;
    private int maxBarHeight;
    private float animValue;
    private int animBarHeight;
    private int pos;
    private int barWidth;

    public BarItemView(Context context) {
        this(context, null);
    }


    public BarItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BarItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.BarItemView);
        try {
            barColir = typedArray.getColor(R.styleable.BarItemView_barColor, Color.RED);
            lineColor = typedArray.getColor(R.styleable.BarItemView_lineColor, Color.GRAY);
            moneyColor = typedArray.getColor(R.styleable.BarItemView_moneyColor, Color.GRAY);
            timeColor = typedArray.getColor(R.styleable.BarItemView_textColor, Color.GRAY);
        } finally {
            typedArray.recycle();
        }
        init();
    }

    private ColumnBean columnBean;
    private boolean hasAnimation;

    public void setValue(ColumnBean bean) {
        this.columnBean = bean;
    }

    public synchronized void updateValue(int position, ColumnBean columnBean, boolean hasAnimation) {
        this.pos = position;
        this.columnBean = columnBean;
        this.hasAnimation = true;
        if (hasAnimation) {
            doAnimation();
        }
        postInvalidate();
    }

    ValueAnimator animator;

    private void doAnimation() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    private void init() {
        initLinePaint();
        initBarPaint();
        initTimePaint();
        initMoneyPaint();
    }

    private void initMoneyPaint() {
        moneyPaint = new Paint();
        moneyPaint.setAntiAlias(true);
        moneyPaint.setColor(moneyColor);
        moneyPaint.setStyle(Paint.Style.FILL);
        moneyPaint.setStrokeWidth(2);

    }

    private void initTimePaint() {
        timePaint = new Paint();
        timePaint.setAntiAlias(true);
        timePaint.setColor(timeColor);
        timePaint.setStyle(Paint.Style.FILL);
        timePaint.setStrokeWidth(2);

    }

    private void initBarPaint() {
        barPaint = new Paint();
        barPaint.setAntiAlias(true);
        barPaint.setColor(barColir);
        barPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private void initLinePaint() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        heigt = getMeasuredHeight();

        initWidth();

        lineHeigt = heigt - DisplayUtils.dip2px(getContext().getApplicationContext(), 32);
        //计算 bar的最大高度 == 总高 - （月份整体高度+value+线的高度+margin距离 * 2）
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTextSize(36);
        paint.getTextBounds("测试", 0, 1, rect);
        int textHeight = rect.height();
        Log.e("距离3", "" + textHeight);
        //bar最大高度 等于最大高度依次减去其他
        maxBarHeight = heigt - DisplayUtils.dip2px(getContext().getApplicationContext(), 32 + textHeight + 6 * 2 + 1);
        setMeasuredDimension(lineWidth, heigt);
    }

    private void initWidth() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        if (columnBean == null) {
            width = lineWidth = DisplayUtils.dip2px(getContext().getApplicationContext(), 60);
            barWidth = DisplayUtils.dip2px(getContext().getApplicationContext(), 30);
        } else {
            width = lineWidth = DisplayUtils.getDetailBarWidth(columnBean.getCurrentCycleFlag(), screenWidth);
            barWidth = width / 2;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //真正的宽高
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (hasAnimation) {
            animBarHeight = (int) (maxBarHeight * animValue);
        } else {
            animBarHeight = maxBarHeight;
        }
        drawLine(canvas);
        drawBar(canvas);
        if(DisplayUtils.shouldDrawIndex(columnBean.getIndex(),columnBean.getCurrentCycleFlag())){
            drawTime(canvas);
        }
        //drawValue(canvas); //绘制alue
    }

    private int marginForMoney = DisplayUtils.dip2px(getContext().getApplicationContext(), 6);

    private void drawValue(Canvas canvas) {
        canvas.save();
        //动态的
        int height = lineHeigt - barHeight;

        Rect rect = new Rect();
        String value = String.valueOf(columnBean.getValue());
        timePaint.setTextSize(36);
        timePaint.getTextBounds(value, 0, value.length(), rect);

        //获得字符串的宽度
        float strNameFix = timePaint.measureText(value) / 2;
        canvas.drawText(value, (float) (width / 2 - rect.width() / 2), height - marginForMoney, timePaint);
    }

    private void drawBar(Canvas canvas) {
        canvas.save();
        int barStartWidth = width / 2 - barWidth / 2;
        int barEndWidth = width / 2 + barWidth / 2;
        //倒角
        float arcRect = 15.0f;
        //高度
        Log.e("animBarHeight ==", pos + "  " + animBarHeight + "" + "  value==" + animValue);
        barHeight = (int) (animBarHeight * columnBean.getPercent());
        //这里要判断

        RectF oval1 = new RectF(barStartWidth, lineHeigt - barHeight + arcRect, barEndWidth, lineHeigt);
        RectF oval2 = new RectF(barStartWidth + arcRect, lineHeigt - barHeight, barEndWidth - arcRect, lineHeigt);
        RectF oval3 = new RectF(barStartWidth, lineHeigt - barHeight, barStartWidth + 2 * arcRect, lineHeigt - barHeight + 2 * arcRect);
        RectF oval4 = new RectF(barEndWidth - 2 * arcRect, lineHeigt - barHeight, barEndWidth, lineHeigt - barHeight + 2 * arcRect);
        canvas.drawRect(oval1, barPaint);
        canvas.drawRect(oval2, barPaint);
        canvas.drawArc(oval3, 180, 90, true, barPaint);
        canvas.drawArc(oval4, 270, 90, true, barPaint);
    }

    /**
     * 绘制 月份
     *
     * @param canvas
     */
    private void drawTime(Canvas canvas) {
        canvas.save();
        Rect rect = new Rect();
        String month = columnBean.getX_coordinate();
        timePaint.setTextSize(24);
        timePaint.getTextBounds(month, 0, month.length(), rect);
        //获得字符串的宽度
        float strNameFix = timePaint.measureText(month) / 2;
        canvas.drawText(month, (float) (width / 2 - rect.width() / 2), getHeight() - DisplayUtils.dip2px(getContext().getApplicationContext(), 16) + rect.height() / 2, timePaint);
    }

    private void drawLine(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, lineHeigt);
        path.lineTo(lineWidth, lineHeigt);
        canvas.drawPath(path, linePaint);
    }

    public ColumnBean getColumnBean() {
        return columnBean;
    }
}

