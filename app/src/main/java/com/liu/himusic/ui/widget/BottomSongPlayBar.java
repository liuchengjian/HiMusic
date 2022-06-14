package com.liu.himusic.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.liu.himusic.R;
import com.liucj.lib_common.livedata.LiveDataBus;
import com.lzx.starrysky.OnPlayProgressListener;
import com.lzx.starrysky.SongInfo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BottomSongPlayBar extends RelativeLayout {
    private static final String TAG = "BottomSongPlayBar";

    private Context mContext;


    private SongInfo currentSongInfo;
    private ViewDataBinding mBinding;
    private List<SongInfo> songList = new ArrayList<>();
    private BottomSongAdapter adapter;
    private ImageView ivPlay;
    private ImageView ivController;
    private RecyclerView rvMusic;
    private LinearLayoutManager layoutManager;
    private PagerSnapHelper snapHelper;
    private CircleProgressBar loadingSong;
    private View view;


    public BottomSongPlayBar(Context context) {
        this(context, null);
    }

    public BottomSongPlayBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomSongPlayBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initListener();
    }

    public void initEvent(Activity activity) {
        LiveDataBus.get().with("stop_music").observe((LifecycleOwner) mContext, new Observer<SongInfo>() {
            @Override
            public void onChanged(SongInfo bean) {
                Log.d(TAG, "stop_music");
                updateList();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                    ivPlay.setImageResource(R.drawable.shape_play);
                    View currentView = snapHelper.findSnapView(layoutManager);
                    if (currentView != null) {
                        BottomSongBarLeftView leftView = currentView.findViewById(R.id.left_view);
                        if (leftView != null) {
                            leftView.end();
                        }
                    }
                }
            }
        });
        LiveDataBus.get().with("play_music").observe((LifecycleOwner) activity, new Observer<SongInfo>() {
            @Override
            public void onChanged(SongInfo bean) {
                Log.d(TAG, "MusicStartEvent :");
                updateList();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                    ivPlay.setImageResource(R.drawable.shape_stop);
                    int currentSongIndex = SongPlayManager.getInstance().getCurrentSongIndex();
                    rvMusic.scrollToPosition(currentSongIndex);
                    View currentView = snapHelper.findSnapView(layoutManager);
                    if (currentView != null) {
                        BottomSongBarLeftView leftView = currentView.findViewById(R.id.left_view);
                        if (leftView != null) {
                            leftView.start();
                        }
                    }
                }

            }
        });
        LiveDataBus.get().with("pause_music").observe((LifecycleOwner) activity, new Observer<SongInfo>() {
            @Override
            public void onChanged(SongInfo bean) {
                Log.d(TAG, "onPauseMusicEvent");
                ivPlay.setImageResource(R.drawable.shape_play);
                View currentView = snapHelper.findSnapView(layoutManager);
                if (currentView != null) {
                    BottomSongBarLeftView leftView = currentView.findViewById(R.id.left_view);
                    if (leftView != null) {
                        leftView.paused();
                    }
                }
            }
        });
    }


    private void initView() {
        view = inflate(mContext, R.layout.layout_bottom_songplay_control, this);
        view.setTag("layout/" + "layout_bottom_songplay_control" + "_0");
        mBinding = DataBindingUtil.bind(view);
//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        mBinding = Data.inflate(layoutInflater,this, true);
        loadingSong = view.findViewById(R.id.loading_song);
        ivPlay = view.findViewById(R.id.iv_bottom_play);
        ivController = view.findViewById(R.id.iv_bottom_controller);
        rvMusic = view.findViewById(R.id.rv_music);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        rvMusic.setLayoutManager(layoutManager);
//        GravitySnapHelper snapHelper = new GravitySnapHelper(Gravity.CENTER);
        snapHelper = new PagerSnapHelper();
        rvMusic.setOnFlingListener(null);
        snapHelper.attachToRecyclerView(rvMusic);
        SongPlayManager.getInstance().initSongData();
        SongPlayManager.getInstance().setOnPlayProgressListener(new OnPlayProgressListener() {
            @Override
            public void onPlayProgress(long l, long l1) {
                if (loadingSong != null) {
                    loadingSong.setMax((int) (l1));
                    loadingSong.setProgress((int) (l));
                }
            }
        });
        updateList();
        adapter = new BottomSongAdapter(songList, ivPlay);
        rvMusic.setAdapter(adapter);
        rvMusic.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                BottomSongBarLeftView leftView = view.findViewById(R.id.left_view);
                if (leftView != null) {
                    leftView.end();
                }
            }
        });
        rvMusic.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //停止滚动
                    if (recyclerView != null && recyclerView.getChildCount() != 0) {
                        View currentSnapingView = snapHelper.findSnapView(layoutManager);
                        int currentSnapingViewPosition = recyclerView.getChildAdapterPosition(currentSnapingView);
                        int currentSongIndex = SongPlayManager.getInstance().getCurrentSongIndex();
                        if (currentSongIndex < currentSnapingViewPosition) {
                            //下一首
                            SongPlayManager.getInstance().playNextMusic();
                        }
                        if (currentSongIndex > currentSnapingViewPosition) {
                            //上一首
                            SongPlayManager.getInstance().playPreMusic();
                        }
//                        BottomSongBarLeftView leftView = currentSnapingView.findViewById(R.id.left_view);
//                        if (leftView != null) {
//                            Log.e("onScrollStateChanged","p:"+currentSnapingViewPosition);
////                            leftView.paused();
//                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 获取RecyclerView的对于View
     *
     * @param recyclerView
     * @param position
     * @return
     */
    public View getRecyclerViewItem(RecyclerView recyclerView, int position) {
        if (recyclerView == null || recyclerView.getLayoutManager() == null || recyclerView.getAdapter() == null) {
            return null;
        }
        if (position > recyclerView.getAdapter().getItemCount()) {
            return null;
        }
        RecyclerView.ViewHolder viewHolder = recyclerView.getAdapter().createViewHolder(recyclerView, recyclerView.getAdapter().getItemViewType(position));
        recyclerView.getAdapter().onBindViewHolder(viewHolder, position);
        viewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return viewHolder.itemView;
    }

    public void initListener() {
        ivPlay.setOnClickListener(v -> {
            if (!SongPlayManager.getInstance().isPlaying()) {
                Log.d(TAG, "playMusic");
                SongPlayManager.getInstance().clickBottomContrllerPlay(currentSongInfo);
            } else {
                Log.d(TAG, "pauseMusic");
                SongPlayManager.getInstance().pauseMusic();
            }
        });
    }

    public void updateList() {
        songList.clear();
        songList.addAll(SongPlayManager.getInstance().getSongList());
        view.setVisibility(songList.isEmpty() ? View.GONE : View.VISIBLE);
    }

}
