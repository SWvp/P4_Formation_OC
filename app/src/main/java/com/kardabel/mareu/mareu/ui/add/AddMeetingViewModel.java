package com.kardabel.mareu.mareu.ui.add;

import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.android.material.textfield.TextInputLayout;
import com.kardabel.mareu.R;
import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.mareu.ui.details.DetailsActivityViewState;

import java.util.ArrayList;
import java.util.List;

public class AddMeetingViewModel extends ViewModel {
    private MeetingsRepository mMeetingsRepository;
    private Meeting meeting;
    private AddMeetingViewState addMeetingViewState;
    private List<String> emails = new ArrayList<>();

    private LiveData<AddMeetingViewState> addMeetingLiveData;

    public AddMeetingViewModel(@NonNull MeetingsRepository meetingsRepository) {
        mMeetingsRepository = meetingsRepository;
    }

    public void onTimeSetAddMeetingViewModel(TimePicker view, int hourOfDay, int minute, EditText editText) {
        editText.setText(hourOfDay + "h" + minute);
        if(minute < 10 && hourOfDay < 10){
            editText.setText("0" + hourOfDay + "h" + "0" + minute);

        }
        else if(minute < 10){
            editText.setText(hourOfDay + "h" + "0" + minute);

        }
        else if(hourOfDay < 10){
            editText.setText("0" + hourOfDay + "h" + minute);

        }
    }

    public void onDateSetAddMeetingViewModel(DatePicker view, int year, int month, int dayOfMonth, EditText dateEditText) {
        dateEditText.setText(year + "-" + month + "-" + dayOfMonth);
        if(month < 10 && dayOfMonth < 10){
            dateEditText.setText("0" + dayOfMonth + "-" + "0" + month + "-" + year);

        }
        else if(month < 10){
            dateEditText.setText(dayOfMonth + "-" + "0" + month + "-" + year);

        }
        else if(dayOfMonth < 10){
            dateEditText.setText("0" + dayOfMonth + "-" + month + "-" + year);

        }
    }

    public void addMeetingName(String meetingName) {
        addMeetingViewState.setAddMeetingDate(meetingName);
    }

    public void addRoomName(String roomName) {
    }

    public void addMeetingDate(String date) {
    }

    public void addMeetingHour(String hour) {
    }

    public void addEmails(String email) {
        emails.add(email);

    }

    public void insertMeeting(){
        if(meeting.completeReunion(meeting)){
        mMeetingsRepository.insertMeeting(meeting);

        }
    }

    public LiveData<AddMeetingViewState> getDetailsLiveData(){ return addMeetingLiveData; }
}
