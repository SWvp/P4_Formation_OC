package com.kardabel.mareu.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.kardabel.mareu.R;

import org.hamcrest.Matcher;


public class ItemViewAction implements ViewAction {
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
        View button = view.findViewById(R.id.item);
        // Maybe check for null
        button.performClick();
    }
}
