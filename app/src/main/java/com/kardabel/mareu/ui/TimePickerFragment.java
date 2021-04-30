package com.kardabel.mareu.ui;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.time.LocalTime;

/**
 * Created by stéphane Warin OCR on 09/04/2021.
 */
public class TimePickerFragment extends DialogFragment {

    public static final int FLAG_START_TIME = 0;
    public static final int FLAG_END_TIME = 1;

    private int flag = 0;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LocalTime time = LocalTime.now();

        int hour = time.getHour();
        int minute = time.getMinute();

        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));

    }
    public void setFlag(int i) {
        flag = i;
    }
}
