package com.liu.himusic.ui.fragment.banner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.liu.himusic.R;

public class GalleryImageHolder extends RecyclerView.ViewHolder {
    public View view;
    ConstraintLayout container;
    ImageView imageView;
    ImageView shareImage;
    TextView title;
    TextView tag_hot;

    public GalleryImageHolder(@NonNull View view) {
        super(view);
        this.view = view;
        container = view.findViewById(R.id.container);
        imageView = view.findViewById(R.id.banner_image);
        shareImage = view.findViewById(R.id.shareImage);
        title = view.findViewById(R.id.title);
        tag_hot = view.findViewById(R.id.tag_hot);
    }
}
