package com.kardabel.mareu.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;

/**
 * Created by stéphane Warin OCR on 15/04/2021.
 */
public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LocalDate date = LocalDate.now();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);

    }
}
