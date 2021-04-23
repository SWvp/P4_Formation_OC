package com.kardabel.mareu.ui.add;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.kardabel.mareu.R;
import com.kardabel.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.ui.DatePickerFragment;
import com.kardabel.mareu.ui.TimePickerFragment;

public class AddMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private AutoCompleteTextView roomSelect;
    private TextInputLayout email;
    private AddMeetingViewModel addMeetingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeeting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_meeting_toolbar);
        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        addMeetingViewModel =
                new ViewModelProvider(this, MareuViewModelFactory.getInstance()).get(AddMeetingViewModel.class);

        //Add name to meeting
        EditText nameEditText = (EditText) findViewById(R.id.add_meeting_name);

        //Dropdown room choice menu
        roomSelect = findViewById(R.id.dropdown_autocomplete);
        String[] items = new String[]{
                "Peach", "Mario", "Luigi", "Toad", "Yoshi", "Donkey", "Koopa", "Boo",
                "Goomba", "Kamek",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AddMeetingActivity.this,
                R.layout.activity_addmeeting_dropdown_item,
                items
        );
        roomSelect.setAdapter(adapter);

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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    //Time/date text input
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditText timeEditText = (EditText) findViewById(R.id.time_setter);
        addMeetingViewModel.onTimeSetAddMeetingViewModel(view, hourOfDay, minute, timeEditText);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText dateEditText = (EditText) findViewById(R.id.date_setter);
        addMeetingViewModel.onDateSetAddMeetingViewModel(view, year, month, dayOfMonth, dateEditText);

    }

    private boolean validateEmailAddress(TextInputLayout email){
        String emailInput = email.getEditText().getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).find()){
            Toast.makeText(this, "email valid", Toast.LENGTH_SHORT).show();
            addMeetingViewModel.addEmails(emailInput);
            return true;

        }else{
            Toast.makeText(this, "invalid adress", Toast.LENGTH_SHORT).show();
            return false;

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        addMeetingViewModel.insertMeeting();
        return super.onOptionsItemSelected(item);
    }
}
