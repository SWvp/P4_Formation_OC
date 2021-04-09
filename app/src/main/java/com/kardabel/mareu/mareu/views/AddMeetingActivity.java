package com.kardabel.mareu.mareu.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.kardabel.mareu.R;
import com.kardabel.mareu.mareu.model.Meeting;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class AddMeetingActivity extends AppCompatActivity {

    private TextInputLayout mTextInputLayout;
    private AutoCompleteTextView mAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeeting);

        mTextInputLayout = findViewById(R.id.dropdown_menu);
        mAutoCompleteTextView = findViewById(R.id.dropdown_autocomplete);

        String[] items = new String[]{
                "Peach",
                "Mario",
                "Toad",
                "Bowser",

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AddMeetingActivity.this,
                R.layout.activity_addmeeting_dropdown_item,
                items

        );
        mAutoCompleteTextView.setAdapter(adapter);
    }


}
