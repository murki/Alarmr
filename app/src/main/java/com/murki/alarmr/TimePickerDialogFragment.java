package com.murki.alarmr;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private TimePickerDialogFragmentCallbacks callbacks;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callbacks = (TimePickerDialogFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int defaultHour = c.get(Calendar.HOUR_OF_DAY);
        int defaultMinute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, defaultHour, defaultMinute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        callbacks.timeUpdated(hour, minute);
    }

    public interface TimePickerDialogFragmentCallbacks {
        void timeUpdated(int hour, int minute);
    }
}