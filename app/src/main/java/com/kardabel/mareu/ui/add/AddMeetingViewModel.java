package com.kardabel.mareu.ui.add;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.ui.add.utils.SingleLiveEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddMeetingViewModel extends ViewModel {

    private MeetingsRepository mMeetingsRepository;

    private String mMeetingName;

    private LocalTime mStartTime;
    private LocalTime mEndTime;
    private LocalDate mDate;

    private Room mRoomName;
    private Room mRoomAvatar;

    private List<Email> emails = new ArrayList<>();

    private boolean chooseDateWasNotPassed = false;

    private SingleLiveEvent<Integer> toastTimeEvent = new SingleLiveEvent<>();


    public AddMeetingViewModel(@NonNull MeetingsRepository meetingsRepository) {
        mMeetingsRepository = meetingsRepository;

    }

    public void addMeetingName(String meetingName) {
        this.mMeetingName = meetingName;

    }

    public void addMeetingRoom(String roomName) {
        this.mRoomName = Room.contains(roomName);
        this.mRoomAvatar = Room.contains(roomName);

    }

    // when start time is picked
    public void onStartTimeSet(TimePicker view, int hourOfDay, int minute, EditText startTime) {
        mStartTime = LocalTime.of(hourOfDay, minute);

        if (mStartTime.isAfter(LocalTime.of(17, 00)) || mStartTime.isBefore(LocalTime.of(8, 00))) {
            onTimeSetProblem(1);
            return;

        }
        if (mEndTime != null) {
            if (mStartTime.isAfter(mEndTime)) {
                onTimeSetProblem(2);
                return;

            }
        }
        String humanReadableHour = mStartTime.toString();
        startTime.setText(humanReadableHour);

    }

    //when end time is picked
    public void onEndTimeSet(TimePicker view, int hourOfDay, int minute, EditText hourEditText) {

        mEndTime = LocalTime.of(hourOfDay, minute);

        if (mEndTime.isAfter(LocalTime.of(18, 00)) || mEndTime.isBefore(LocalTime.of(9, 00))) {
            onTimeSetProblem(3);
            return;

        }
        if (mStartTime != null) {
            if (mEndTime.isBefore(mStartTime)) {
                onTimeSetProblem(4);
                return;

            }
        }
        String humanReadableHour = mEndTime.toString();
        hourEditText.setText(humanReadableHour);

    }

    //When a date is picked
    public void onDateSetAddMeetingViewModel(DatePicker view, int year, int month, int dayOfMonth, EditText dateEditText) {
        chooseDateWasNotPassed = false;
        mDate = LocalDate.of(year, month, dayOfMonth);
        if(mDate.isBefore(LocalDate.now())){
            chooseDateWasNotPassed = true;
            return;

        }
        String humanReadableDate = mDate.toString();
        dateEditText.setText(humanReadableDate);

    }

    public boolean chooseGoodDate(){ return chooseDateWasNotPassed; }

    //Create meeting ID
    public int meetingId() {
        int lastmeetingId = mMeetingsRepository.findLastMeetingId();
        int meetingId = lastmeetingId + 1;
        return meetingId;

    }


    public void addEmails(String email) {
        emails.add(new Email(email));

    }

    public void addNewMeeting() {
        Meeting meeting = new Meeting(
                meetingId(),
                mMeetingName,
                mRoomAvatar,
                mStartTime,
                mEndTime,
                mDate,
                mRoomName,
                emails
                );
        if (completeReunion()) {
            mMeetingsRepository.addNewMeeting(meeting);

        }
    }

    public boolean completeReunion() {
        if (mMeetingName != null && mStartTime != null && mEndTime != null && mDate !=null && mRoomName!= null && mRoomAvatar != null && emails!= null) {
            return true;

        }
        else {
            onTimeSetProblem(5);
            return false;

        }
    }

    public void onTimeSetProblem(Integer index) {toastTimeEvent.setValue(index);}

    public SingleLiveEvent<Integer> getTimeEvent() {return toastTimeEvent;}

}
