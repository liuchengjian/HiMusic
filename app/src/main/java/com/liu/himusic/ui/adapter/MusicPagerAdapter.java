package com.liu.himusic.ui.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.liu.himusic.R;
import com.liu.himusic.ui.widget.SongPlayManager;
import com.liucj.lib_common.view.PPImageView;
import com.lzx.starrysky.SongInfo;

import java.util.List;

/**
 * 播放页面ViewPager Adapter
 */
public class MusicPagerAdapter extends PagerAdapter {

    private Context mContext;
    /*
     * data
     */
    private List<SongInfo> mAudioBeans;
    private SparseArray<ObjectAnimator> mAnims = new SparseArray<>();
    private Callback mCallback;

    public MusicPagerAdapter(List<SongInfo> audioBeans, Context context, Callback callback) {
        mAudioBeans = audioBeans;
        mContext = context;
        mCallback = callback;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.indictor_item_view, null);
        PPImageView imageView = rootView.findViewById(R.id.circle_view);
        container.addView(rootView);
        imageView.setImageUrl(mAudioBeans.get(position).getSongCover(),true);
        //只在无动化时创建
        mAnims.put(position, createAnim(rootView)); // 将动画缓存起来
        return rootView;
    }

    @Override
    public int getCount() {
        return mAudioBeans == null ? 0 : mAudioBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private ObjectAnimator createAnim(View view) {
        view.setRotation(0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ROTATION.getName(), 0, 360);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        if (SongPlayManager.getInstance().isPlaying()) {
            animator.start();
        }
        return animator;
    }

    public ObjectAnimator getAnim(int pos) {
        return mAnims.get(pos);
    }

    /**
     * 与IndictorView回调,暂时没用到
     */
    public interface Callback {
        void onPlayStatus();

        void onPauseStatus();
    }
}

