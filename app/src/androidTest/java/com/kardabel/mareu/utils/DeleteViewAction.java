package com.kardabel.mareu.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.kardabel.mareu.R;

import org.hamcrest.Matcher;

/**
 * Created by stéphane Warin OCR on 29/04/2021.
 */
public class DeleteViewAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.delete_button);
        button.performClick();
    }


}
