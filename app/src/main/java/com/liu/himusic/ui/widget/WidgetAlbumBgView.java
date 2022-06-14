package com.liu.himusic.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

import com.liu.himusic.R;
import com.liucj.lib_common.utils.HiDisplayUtil;
import com.liucj.lib_common.utils.PixUtils;

public class WidgetAlbumBgView extends View {
    private Paint paint;
    // 圆环半径
    private int ringWidth;
    // 渐变色
    private int[] colors;
    private SweepGradient gradient;
    // 圆线距圆环内边的距离
    private int[] ringLinesMarginOut = {
            HiDisplayUtil.dp2px(3.78F),
            HiDisplayUtil.dp2px(7.03F),
            HiDisplayUtil.dp2px(10.27F),
            HiDisplayUtil.dp2px(12.97F)
    };
    // 圆线高度
    private int ringLineWidth;
    private Context context;

    public WidgetAlbumBgView(Context context) {
        this(context, null);
    }

    public WidgetAlbumBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WidgetAlbumBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context= context;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        colors = new int[]{getColor(R.color.color_gray), getColor(R.color.color_gray2),
                getColor(R.color.color_gray), getColor(R.color.color_gray2),
                getColor(R.color.color_gray)};

        ringWidth = PixUtils.dp2px(10);
        ringLineWidth = PixUtils.dp2px(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(ringWidth);
        paint.setColor(getColor(R.color.color_gray));
        if (gradient == null) {
            gradient = new SweepGradient(getWidth() * 0.5F, getHeight() * 0.5F, colors, new float[]{
                    0F, 0.25F, 0.5F, 0.75F, 1F
            });
        }
        paint.setShader(gradient);
        // 画圆环
        canvas.drawCircle(getWidth() * 0.5F, getHeight() * 0.5F, (getWidth() - ringWidth) * 0.5F, paint);
        paint.setShader(null);
        paint.setStrokeWidth(ringLineWidth);
        paint.setColor(getColor(R.color.color_gray));
        // 画圆线
        for (int marginOut : ringLinesMarginOut) {
            canvas.drawCircle(getWidth() * 0.5F, getHeight() * 0.5F, getWidth() * 0.5F - marginOut - ringLineWidth * 0.5F, paint);
        }
    }

    private int getColor(@ColorRes int color){
        return context.getResources().getColor(color);
    }

}
