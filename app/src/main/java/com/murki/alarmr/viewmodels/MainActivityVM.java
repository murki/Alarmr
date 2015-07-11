package com.murki.alarmr.viewmodels;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.murki.alarmr.BuildConfig;
import com.murki.alarmr.TimePickerDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.TimeZone;

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

    public void pickTime(FragmentManager fragmentManager) {
        DialogFragment newFragment = new TimePickerDialogFragment();
        newFragment.show(fragmentManager, "timePicker");
    }

    public void timeUpdated(View view, int hour, int minute) {
        Snackbar.make(view, "Time selected is: " + hour + " hours, and " + minute + " minutes.", Snackbar.LENGTH_LONG).show();
    }

    public void createAlarm() {

    }

    public void createReminder(ContentResolver contentResolver) {
        Calendar calendar = Calendar.getInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CalendarContract.Events.CALENDAR_ID, 1); // default Calendar
        contentValues.put(CalendarContract.Events.HAS_ALARM, 1);
        contentValues.put(CalendarContract.Events.TITLE, "Title");
        contentValues.put(CalendarContract.Events.DESCRIPTION, "Description");
        contentValues.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        contentValues.put(CalendarContract.Events.DTSTART, calendar.getTimeInMillis() + 60 * 1000); // now +1 minute
//        contentValues.put(CalendarContract.Events.DTEND, calendar.getTimeInMillis() + 2 * 60 * 1000);
        contentValues.put(CalendarContract.Events.DURATION, "+P1H"); // 1 hour

        // insert event to calendar
        Uri eventUri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues);
        long eventId = Long.parseLong(eventUri.getLastPathSegment());

        ContentValues reminder = new ContentValues();
        reminder.put(CalendarContract.Reminders.EVENT_ID, eventId);
        reminder.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALARM);
        reminder.put(CalendarContract.Reminders.MINUTES, 0);

        // insert reminder
        Uri reminderUri = contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, reminder);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso pic = Picasso.with(view.getContext());
        if (BuildConfig.DEBUG) {
            pic.setLoggingEnabled(true);
        }
        pic.load(url).into(view);
    }
}
