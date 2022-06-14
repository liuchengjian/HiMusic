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
import com.liu.himusic.model.bean.CreativesBean;
import com.liu.himusic.model.bean.ResourcesBean;
import com.liu.himusic.model.bean.UiElementBean;
import com.liucj.lib_common.utils.PixUtils;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局，网络图片
 */
public class ImageGalleryAdapter extends BannerAdapter<ResourcesBean, GalleryImageHolder> {

    public ImageGalleryAdapter(List<ResourcesBean> mDatas) {
        super(mDatas);
    }

    @Override
    public GalleryImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = BannerUtils.getView(parent, R.layout.banner_gallery);
        return new GalleryImageHolder(view);
    }

    @Override
    public void onBindView(GalleryImageHolder holder, ResourcesBean data, int position, int size) {
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        UiElementBean uiElement  = data.uiElement;
        Glide.with(holder.imageView)
                .load(uiElement.mainTitle.titleImgUrl)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(PixUtils.dp2px(20))))
                .into(holder.imageView);
        GradientDrawable gd = new GradientDrawable();
        int fillColor = Color.parseColor("red");
        gd.setColor(fillColor);
        gd.setCornerRadius(20);
        holder.container.setBackgroundDrawable(gd);
        if (!TextUtils.isEmpty(uiElement.mainTitle.title)) {
//
//            holder.tag.setBackgroundDrawable(gd);
            holder.title.setText("# "+uiElement.mainTitle.title);
            holder.tag_hot.setText(uiElement.subTitle.title);
        }


    }

}
