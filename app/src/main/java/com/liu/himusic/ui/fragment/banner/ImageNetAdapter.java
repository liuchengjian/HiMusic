package com.liu.himusic.ui.fragment.banner;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liu.himusic.R;
import com.liu.himusic.model.bean.ExtInfo;
import com.liucj.lib_common.utils.PixUtils;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局，网络图片
 */
public class ImageNetAdapter extends BannerAdapter<ExtInfo.BannersBean, ImageHolder> {

    public ImageNetAdapter(List<ExtInfo.BannersBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = BannerUtils.getView(parent, R.layout.banner_image);
        //通过裁剪实现圆角
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            BannerUtils.setBannerRound(view, 20);
//        }
        return new ImageHolder(view);
    }

    @Override
    public void onBindView(ImageHolder holder, ExtInfo.BannersBean data, int position, int size) {
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        Glide.with(holder.imageView)
                .load(data.getPic())
                .thumbnail(Glide.with(holder.imageView).load(R.drawable.loading))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(PixUtils.dp2px(10))))
                .into(holder.imageView);
        if (!TextUtils.isEmpty(data.getTypeTitle())) {
            int fillColor = TextUtils.isEmpty(data.getTitleColor()) ? Color.parseColor("red") : Color.parseColor(data.getTitleColor());
//            GradientDrawable gd = new GradientDrawable();
//            ShapeDrawable sd = new ShapeDrawable();
//            gd.setColor(fillColor);
//            gd.setCornerRadius(20);
//            holder.tag.setBackgroundDrawable(gd);
            holder.tag.setText(data.getTypeTitle());
            holder.tag.setVisibility(View.VISIBLE);
            ((GradientDrawable)holder.tag.getBackground()).setColor(fillColor);
        } else {
            holder.tag.setVisibility(View.GONE);
        }


    }

}
