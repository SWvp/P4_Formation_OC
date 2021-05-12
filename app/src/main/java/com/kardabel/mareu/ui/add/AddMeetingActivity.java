package com.kardabel.mareu.ui.add;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.kardabel.mareu.R;
import com.kardabel.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.ui.DatePickerFragment;
import com.kardabel.mareu.ui.TimePickerFragment;
import com.kardabel.mareu.ui.add.utils.AddMeetingViewAction;


public class AddMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextInputLayout email;
    private TextInputLayout meetingName;
    private AddMeetingViewModel addMeetingViewModel;
    private ChipGroup addMeetingChipGroup;
    private String meetingRoom;
    private int flag = -1;
    private String startTimeText;
    private String endTimeText;
    private String dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeeting);

        meetingName = findViewById(R.id.meeting_name);
        addMeetingChipGroup = findViewById(R.id.add_meeting_chipGroup);


        Toolbar toolbar = (Toolbar) findViewById(R.id.add_meeting_toolbar);
        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        addMeetingViewModel =
                new ViewModelProvider(this, MareuViewModelFactory.getInstance()).get(AddMeetingViewModel.class);

        // Dropdown room choice menu
        AutoCompleteTextView roomSelect = findViewById(R.id.dropdown_autocomplete);
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

        roomSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                meetingRoom = parent.getItemAtPosition(position).toString();

            }
        });

        // Date popup
        EditText dateEditText = findViewById(R.id.date_setter);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // Start time popup with flag update
        EditText startTime = findViewById(R.id.start_time_setter);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFlagForTime(0);
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "start time picker");
            }
        });

        // End time popup with flag update
        EditText endTime = findViewById(R.id.end_time_setter);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFlagForTime(1);
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "end time picker");
            }
        });

        // Click on "add email" button
        email = findViewById(R.id.email_input);
        Button addMailButton = findViewById(R.id.add_mail_button);
        addMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailAddress(email);
            }
        });

        // Click on "Save" button
        Button save = findViewById(R.id.create);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { saveMeeting(); }
        });

        // Back to main activity
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }

        });

        ///////////////SingleLiveEvent////////////////

        addMeetingViewModel.getViewActionSingleLiveEvent().observe(this, new Observer<AddMeetingViewAction>() {
            @Override
            public void onChanged(AddMeetingViewAction action) {
                switch (action) {
                    case FINISH_ACTIVITY:
                        finish();
                        Toast.makeText(AddMeetingActivity.this, "Thank you !", Toast.LENGTH_LONG).show();
                        break;
                    case DISPLAY_START_HOUR:
                        startTime.setText(startTimeText);
                        break;
                    case DISPLAY_END_HOUR:
                        endTime.setText(endTimeText);
                        break;
                    case DISPLAY_DATE:
                        dateEditText.setText(dateText);
                        break;
                    case DISPLAY_FIELDS_ERROR:
                        Toast.makeText(AddMeetingActivity.this, "Complete all fields please", Toast.LENGTH_LONG).show();
                        break;
                    case DISPLAY_START_OOB:
                        Toast.makeText(AddMeetingActivity.this, "Start time must be between 8am and 17pm", Toast.LENGTH_LONG).show();
                        break;
                    case DISPLAY_END_OOB:
                        Toast.makeText(AddMeetingActivity.this, "End time must before 18pm and after 9am", Toast.LENGTH_LONG).show();
                        break;
                    case DISPLAY_START_AFTER_ERROR:
                        Toast.makeText(AddMeetingActivity.this, "Start time must be before end time !", Toast.LENGTH_LONG).show();
                        break;
                    case DISPLAY_END_BEFORE_ERROR:
                        Toast.makeText(AddMeetingActivity.this, "End time must be after start time !", Toast.LENGTH_LONG).show();
                        break;
                    case DATE_ERROR:
                        Toast.makeText(AddMeetingActivity.this, "Date must be this day or higher", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    // Flag to know witch time editText to update
    public void setFlagForTime(int i) {
        flag = i;

    }

    // Convert date picked to string
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateText = year + "-" + (month+1) + "-" + dayOfMonth;
        addMeetingViewModel.onDateSet(year, month, dayOfMonth);

    }

    // Convert time picked to string, then, with flag, we know witch editText to update
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String min = "";
        String hour = "";
        if (minute < 10) { min = "0" + minute; } else { min = String.valueOf(minute); }
        if (hourOfDay < 10) { hour = "0" + hourOfDay; } else { hour = String.valueOf(hourOfDay); }

        if (flag == 0) {
            startTimeText = hour + ":" + min;
            addMeetingViewModel.onStartTimeSet(hourOfDay, minute);

        }
        if (flag == 1) {
            endTimeText = hour + ":" + min;
            addMeetingViewModel.onEndTimeSet(hourOfDay, minute);

        }
    }

    // Check email entry
    private boolean validateEmailAddress(TextInputLayout email){
        String emailInput = email.getEditText().getText().toString();
        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).find()){
            addNewChipEmails(emailInput);
            addMeetingViewModel.addEmails(emailInput);
            return true;

        }else{
            Toast.makeText(this, "invalid address", Toast.LENGTH_SHORT).show();
            return false;

        }
    }

    // Add email as a chip in chipGroup
    private  void addNewChipEmails(String email){
        LayoutInflater inflater = LayoutInflater.from(this);
        Chip newChip = (Chip) inflater.inflate(R.layout.mail_chip_item, this.addMeetingChipGroup, false);
        newChip.setText(email);
        this.addMeetingChipGroup.addView(newChip);

    }

    // When meeting is about to be saved
    public void saveMeeting() {
        String stringMeetingName = meetingName.getEditText().getText().toString();
        addMeetingViewModel.addMeetingName(stringMeetingName);
        addMeetingViewModel.addMeetingRoom(meetingRoom);
        addMeetingViewModel.onSaveButtonClick();

    }
}
