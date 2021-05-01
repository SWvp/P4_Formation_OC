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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.kardabel.mareu.R;
import com.kardabel.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.ui.DatePickerFragment;
import com.kardabel.mareu.ui.TimePickerFragment;


public class AddMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextInputLayout email;
    private TextInputLayout meetingName;
    private AddMeetingViewModel addMeetingViewModel;
    private ChipGroup addMeetingChipGroup;
    private String meetingRoom;
    private int flag = -1;

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

        //Dropdown room choice menu
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

        //Date popup
        EditText dateButton = findViewById(R.id.date_setter);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        //Start time popup with flag update
        EditText startTime = findViewById(R.id.start_time_setter);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFlagForTime(0);
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "start time picker");
            }
        });

        //End time popup with flag update
        EditText endTime = findViewById(R.id.end_time_setter);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFlagForTime(1);
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "end time picker");
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

        Button save = findViewById(R.id.create);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeeting();
              if(!addMeetingViewModel.completeReunion()){
                  Toast.makeText(AddMeetingActivity.this, "complete all fields please", Toast.LENGTH_SHORT).show();
                  return;
              }

              else if(addMeetingViewModel.startTimeProblemWithOtherMeetings()){
                  Toast.makeText(AddMeetingActivity.this, "change start meeting hour please", Toast.LENGTH_SHORT).show();
                  return;
              }

              else if(addMeetingViewModel.endTimeProblemWithOtherMeetings()){
                  Toast.makeText(AddMeetingActivity.this, "change end meeting hour please", Toast.LENGTH_SHORT).show();
                  return;

              }
                finish();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    //Flag to know witch time edittext to update
    public void setFlagForTime(int i) {
        flag = i;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText dateEditText = findViewById(R.id.date_setter);
        addMeetingViewModel.onDateSetAddMeetingViewModel(view, year, month+1, dayOfMonth, dateEditText);
    }

    //with flag, we know witch edittext to update and compare
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditText startTime = findViewById(R.id.start_time_setter);
        EditText endTime = findViewById(R.id.end_time_setter);
        if(flag == 0){
            addMeetingViewModel.onStartTimeSet(view, hourOfDay, minute, startTime);
            if(addMeetingViewModel.startTimeAfterEndTime()){
                Toast.makeText(this, "start time must be before end time", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(addMeetingViewModel.startTimeOOB()){
                Toast.makeText(this, "start time between 08a.m and 05p.m ", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(flag == 1){
            addMeetingViewModel.onEndTimeSet(view, hourOfDay, minute, endTime);
            if(addMeetingViewModel.endTimeOOB()){
                Toast.makeText(this, "end time must be before 06p.m ", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(addMeetingViewModel.endTimeBeforeStartTime()){
                Toast.makeText(this, "end time must be after start time", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

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

    private  void addNewChipEmails(String email){
        LayoutInflater inflater = LayoutInflater.from(this);
        Chip newChip = (Chip) inflater.inflate(R.layout.mail_chip_item, this.addMeetingChipGroup, false);
        newChip.setText(email);
        this.addMeetingChipGroup.addView(newChip);
    }

    public void saveMeeting() {
        String stringMeetingName = meetingName.getEditText().getText().toString();
        addMeetingViewModel.addMeetingName(stringMeetingName);
        addMeetingViewModel.addMeetingRoom(meetingRoom);
        addMeetingViewModel.addNewMeeting();
    }
}
