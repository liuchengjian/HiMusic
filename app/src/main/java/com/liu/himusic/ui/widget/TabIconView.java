package com.liu.himusic.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liu.himusic.R;

import java.util.Calendar;

public class TabIconView extends RelativeLayout {
    private ImageView tabIcon;
    private TextView tabDay;
    private Context context;

    public TabIconView(Context context) {
        this(context, null);
    }

    public TabIconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.find_tab_item, this);
        tabIcon = view.findViewById(R.id.iv_icon);
        tabDay = view.findViewById(R.id.tv_day);
    }

    public void setTabIcon(String icon) {
//        tabIcon.setBackgroundDrawable(context.getDrawable(icon));
        Glide.with(context)
                .load(icon)
                .into(tabIcon);
        int color = context.getResources().getColor(R.color.common_red);
        tabIcon.setColorFilter(color);
    }

    public void setTabDay(int index) {
        Calendar calendar = Calendar.getInstance();//取得当前时间的年月日 时分秒
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        tabDay.setVisibility(index == 0 ? View.VISIBLE : View.GONE);
        tabDay.setText(String.valueOf(day));
    }
}
