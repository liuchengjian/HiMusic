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
import com.google.gson.Gson;
import com.liu.himusic.R;
import com.liu.himusic.model.api.FindApi;
import com.liu.himusic.model.bean.CreativesBean;
import com.liu.himusic.model.bean.ResourcesBean;
import com.liu.himusic.model.bean.TopicDetailBean;
import com.liu.himusic.model.bean.UiElementBean;
import com.liu.himusic.model.net.ApiFactory;
import com.liucj.lib_common.utils.PixUtils;
import com.liucj.lib_network.restful_kt.HiCallback;
import com.liucj.lib_network.restful_kt.HiResponse;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义布局，网络图片
 */
public class ImageGalleryAdapter extends BannerAdapter<ResourcesBean, GalleryImageHolder> {
    private Map<String, TopicDetailBean> urlMap = new HashMap<>();

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
        toHotDetail(holder, data.resourceId);
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        UiElementBean uiElement = data.uiElement;
        GradientDrawable gd = new GradientDrawable();
//        int fillColor = Color.parseColor("red");
//        gd.setColor(fillColor);
        gd.setCornerRadius(20);
        holder.container.setBackgroundDrawable(gd);
        if (!TextUtils.isEmpty(uiElement.mainTitle.title)) {
//            holder.tag.setBackgroundDrawable(gd);
            holder.title.setText("# " + uiElement.mainTitle.title);
            holder.tag_hot.setText(uiElement.subTitle.title);
        }
    }

    /**
     * 获取热门话题详情
     *
     * @param holder
     * @param actid
     */
    private void toHotDetail(GalleryImageHolder holder, String actid) {
        if (urlMap.get(actid) != null) {
            TopicDetailBean topicDetailBean = urlMap.get(actid);
            setImageData(holder, topicDetailBean);
            return;
        }
        ApiFactory.INSTANCE.create(FindApi.class).topicDetail(actid)
                .enqueue(new HiCallback<TopicDetailBean>() {
                    @Override
                    public void onSuccess(@NotNull HiResponse<TopicDetailBean> response) {
                        if (response.getCode() == 200) {
                            if (!TextUtils.isEmpty(response.getRawData())) {
                                TopicDetailBean bean = new Gson().fromJson(response.getRawData(), TopicDetailBean.class);
                                urlMap.put(actid, bean);
                                setImageData(holder, bean);
                            }
                        }
                    }

                    @Override
                    public void onFailed(@NotNull Throwable throwable) {

                    }
                });


    }

    /**
     * 设置背景和旋转图片资源
     *
     * @param holder
     * @param topicDetailBean
     */
    private void setImageData(GalleryImageHolder holder, TopicDetailBean topicDetailBean) {
        if (topicDetailBean != null && topicDetailBean.act != null) {
            //旋转图片
            Glide.with(holder.shareImage)
                    .load(topicDetailBean.act.sharePicUrl)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(PixUtils.dp2px(10))))
                    .into(holder.shareImage);
            //背景
            Glide.with(holder.imageView)
                    .load(topicDetailBean.act.coverMobileUrl)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(PixUtils.dp2px(10))))
                    .into(holder.imageView);
        }
    }

}
