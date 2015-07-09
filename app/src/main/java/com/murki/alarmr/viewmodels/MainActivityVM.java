package com.murki.alarmr.viewmodels;

import android.databinding.BindingAdapter;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.murki.alarmr.BuildConfig;
import com.murki.alarmr.TimePickerDialogFragment;
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
        Picasso pic = Picasso.with(view.getContext());
        if (BuildConfig.DEBUG) {
            pic.setLoggingEnabled(true);
        }
        pic.load(url).into(view);
    }

    public void pickTime(FragmentManager fragmentManager) {
        DialogFragment newFragment = new TimePickerDialogFragment();
        newFragment.show(fragmentManager, "timePicker");
    }

    public void timeUpdated(View view, int hour, int minute) {
        Snackbar.make(view, "Time selected is: " + hour + " hours, and " + minute + " minutes.", Snackbar.LENGTH_LONG).show();
    }
}
