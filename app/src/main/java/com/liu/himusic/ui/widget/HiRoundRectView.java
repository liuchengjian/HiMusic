package com.liu.himusic.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.liu.himusic.R;


/**
 * 歌单封面的view,是一个圆角矩形
 */
public class HiRoundRectView extends AppCompatImageView {

    private float roundRatio = 16f;
    private Path path;

    public HiRoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RikkaRoundRectView, 0, 0);
        try {
            roundRatio = typedArray.getFloat(R.styleable.RikkaRoundRectView_roundRatio, 16f);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (path == null) {
            path = new Path();
            path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), roundRatio * getWidth(), roundRatio * getHeight(), Path.Direction.CW);
        }
        canvas.save();
        canvas.clipPath(path);
        super.onDraw(canvas);
        canvas.restore();
    }
}
