package com.liu.himusic.ui.fragment.banner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liu.himusic.R;

public class ImageHolder extends RecyclerView.ViewHolder {
    public View view;
    ImageView imageView;
    TextView tag;

    public ImageHolder(@NonNull View view) {
        super(view);
        this.view = view;
        imageView = view.findViewById(R.id.banner_image);
        tag = view.findViewById(R.id.banner_tag);
    }
}