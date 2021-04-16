package com.kardabel.mareu.mareu.ui.list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.kardabel.mareu.R;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class AddMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextInputLayout mTextInputLayout;
    private AutoCompleteTextView mAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeeting);

        mTextInputLayout = findViewById(R.id.dropdown_menu);
        mAutoCompleteTextView = findViewById(R.id.dropdown_autocomplete);

        //Dropdown room choice menu
        String[] items = new String[]{
                "Peach", "Mario", "Luigi", "Toad", "Yoshi", "Donkey", "Koopa", "Boo",
                "Goomba", "Kamek",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AddMeetingActivity.this,
                R.layout.activity_addmeeting_dropdown_item,
                items
        );
        mAutoCompleteTextView.setAdapter(adapter);

        //Time and date popup
        Button timeButton = (Button) findViewById(R.id.time_choose_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button dateButton = (Button) findViewById(R.id.date_choose_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    //Time and date text input
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditText editText = (EditText) findViewById(R.id.time_setter);
        editText.setText(hourOfDay + "h" + minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText dateSet = (EditText) findViewById(R.id.date_setter);
        dateSet.setText(year + "/" + month + "/" + dayOfMonth);

    }
}
