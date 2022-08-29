package com.liu.himusic.ui.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liu.himusic.R;
import com.liu.himusic.model.Const;
import com.liu.himusic.ui.activity.MusicPlayerActivity;
import com.liu.himusic.ui.activity.SongPlayerActivity;
import com.liu.himusic.ui.adapter.MusicPagerAdapter;
import com.lzx.starrysky.SongInfo;

import java.util.List;

/**
 * 自定义布局，网络图片
 */
public class BottomSongAdapter extends RecyclerView.Adapter<BottomSongAdapter.SongHolder> {
    private static final String TAG = "BottomSongPlayBar";
    List<SongInfo> mSongs;
    public ObjectAnimator animator;
    private ImageView ivPlay;
    private Context context;

    public BottomSongAdapter(List<SongInfo> mSongs, ImageView ivPlay, Context context) {
        this.mSongs = mSongs;
        this.ivPlay = ivPlay;
        this.context = context;
    }


    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bar_music, parent, false);
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        SongInfo currentSongInfo = mSongs.get(position);
        if (currentSongInfo != null) {
            setSongBean(holder, currentSongInfo);
            Log.d(TAG, "isPlaying " + SongPlayManager.getInstance().isPlaying());
            if (SongPlayManager.getInstance().isPlaying()) {
                if (ivPlay != null)
                    ivPlay.setImageResource(R.drawable.shape_stop);
            }
        }
        holder.itemView.setOnClickListener(v -> {
            SongPlayerActivity.start((Activity) context);
        });
        holder.leftView.setOnClickListener(v -> {
            SongPlayerActivity.start((Activity) context);
        });
        holder.llSongInfo.setOnClickListener(v -> {
            SongPlayerActivity.start((Activity) context);

        });

    }

    public void setSongBean(SongHolder holder, SongInfo bean) {
        holder.tvSongName.setText(bean.getSongName());
        holder.tvSongSinger.setText(bean.getArtist());
        if (!TextUtils.isEmpty(bean.getSongCover())) {
            holder.leftView.getImageView().setImageUrl(bean.getSongCover(), true);
        }
    }


    public void setAnimatorEnd() {

    }

    public void setAnimatorStart() {

        if (ivPlay != null) ivPlay.setImageResource(R.drawable.shape_stop);
    }


    public void setAnimatorPause() {

        if (ivPlay != null) ivPlay.setImageResource(R.drawable.shape_play);
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        public BottomSongBarLeftView leftView;
        public TextView tvSongName, tvSongSinger;
        public LinearLayout llSongInfo;

        public SongHolder(@NonNull View view) {
            super(view);
            leftView = view.findViewById(R.id.left_view);
            tvSongName = view.findViewById(R.id.tv_songname);
            tvSongSinger = view.findViewById(R.id.tv_singer);
            llSongInfo = view.findViewById(R.id.ll_songinfo);
        }
    }
}
