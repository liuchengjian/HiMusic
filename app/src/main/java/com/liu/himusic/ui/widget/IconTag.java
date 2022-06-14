package com.liu.himusic.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liu.himusic.R;
import com.liu.himusic.utils.HiUtils;

public class IconTag extends RelativeLayout {
    private Context context;
    private TextView tag_num;

    public IconTag(Context context) {
        this(context, null);
    }

    public IconTag(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.find_icon_tag, this);
        tag_num = view.findViewById(R.id.tag_num);
    }

    public void setTagNumText(long num) {
        tag_num.setText(HiUtils.watchNum(num));
    }
}
