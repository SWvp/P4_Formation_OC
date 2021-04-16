package com.kardabel.mareu.mareu.ui.list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.kardabel.mareu.R;

import java.util.regex.Pattern;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class AddMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextInputLayout mTextInputLayout;
    private AutoCompleteTextView mAutoCompleteTextView;
    private TextInputLayout email;

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

        //Time popup
        Button timeButton = (Button) findViewById(R.id.time_choose_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        //Date popup
        Button dateButton = (Button) findViewById(R.id.date_choose_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        //Add email
        email = findViewById(R.id.email_input);
        Button addMailButton = findViewById(R.id.add_mail_button);
        addMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailAddress(email);
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

    private boolean validateEmailAddress(TextInputLayout email){
        String emailInput = email.getEditText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(this, "email valid", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, "invalid adress", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
