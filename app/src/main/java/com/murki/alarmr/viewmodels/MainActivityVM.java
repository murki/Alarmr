package com.murki.alarmr.viewmodels;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivityVM {

    private final String mainLine;
    private final String imageUrl;

    public MainActivityVM(String mainLine, String imageUrl) {
        this.mainLine = mainLine;
        this.imageUrl = imageUrl;
    }

    public String getMainLine() {
        return mainLine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }
}
