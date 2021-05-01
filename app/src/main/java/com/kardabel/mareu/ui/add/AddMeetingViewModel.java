package com.kardabel.mareu.ui.add;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.repository.MeetingsRepository;

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

    private boolean startTimeProblemSendToast = false;
    private boolean endTimeProblemSendToast = false;
    private boolean startTimeOOB = false;
    private boolean startTimeAfterEndToast = false;
    private boolean endTimeOOB = false;
    private boolean endTimeBeforeStartToast = false;

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

    public void onStartTimeSet(TimePicker view, int hourOfDay, int minute, EditText startTime) {
        startTimeAfterEndToast = false;
        mStartTime = LocalTime.of(hourOfDay, minute);

        if(mStartTime.isAfter(LocalTime.of(17,00))){
            //toast too late start !!!
            return;
        }
        if(mEndTime != null){
            if(mStartTime.isAfter(mEndTime)){
                //Toast start must be before end
                return;

            }
        }
 //     if(startTimeIsOutOfBound(mStartTime)){
 //         startTimeOOB = true;
 //         return;

 //     }
 //     else{
            String humanReadableHour = mStartTime.toString();
            startTime.setText(humanReadableHour);

    }

    //when end time is picked
    public void onEndTimeSet(TimePicker view, int hourOfDay, int minute, EditText hourEditText) {
        endTimeBeforeStartToast = false;
        mEndTime = LocalTime.of(hourOfDay, minute);

        if(mEndTime.isAfter(LocalTime.of(18 ,00))){
            //toast too late end !!
            return;
        }
        if(mStartTime != null){
            if(mEndTime.isBefore(mStartTime)){
                //Toast end must be after start
                return;

            }
        }
 //    if(mStartTime != null){
 //        if (mEndTime.isBefore(mStartTime)) {
 //            endTimeBeforeStartToast = true;
 //            return;

 //        }
 //    }
 //    if(endTimeIsOutOfBound(mEndTime)){
 //        endTimeOOB = true;
 //        return;

 //    }
        //else{
            String humanReadableHour = mEndTime.toString();
            hourEditText.setText(humanReadableHour);


    }

    //When a date is picked
    public void onDateSetAddMeetingViewModel(DatePicker view, int year, int month, int dayOfMonth, EditText dateEditText) {
        mDate = LocalDate.of(year, month, dayOfMonth);
        if(mDate.isBefore(LocalDate.now())){

            return;
        }
        String humanReadableDate = mDate.toString();
        dateEditText.setText(humanReadableDate);

    }

    //Compare date selected to other meetings
    private boolean dateValidate(LocalDate date){
        if(mMeetingsRepository.compareDate(date)){ return true; }
        else{ return false; }
    }

    //Compare room selected to other meetings
    private boolean roomValidate(Room room){
        if(mMeetingsRepository.compareRoom(room)){ return true; }
        else{ return false; }
    }

// //Compare start time selected to time limits
// private boolean startTimeIsOutOfBound(LocalTime time){
//     if(time.isBefore(LocalTime.of(8, 0))){ return true; }

//     else if (time.isAfter((LocalTime.of(17,0)))){ return true; }

//     else{ return false; }

// }

// //Compare send time selected to time limits
// private boolean endTimeIsOutOfBound(LocalTime time){
//     if(time.isAfter(LocalTime.of(18, 0))){ return true; }

//     else{ return false; }

// }

    //Compare time selected to other meetings
    private boolean startTimeValidate(LocalTime time){
        if(mMeetingsRepository.compareStartTime(time)){
            startTimeProblemSendToast = true;
            return true;
        }
        else{ return false; }
    }

    private boolean endTimeValidate(LocalTime time){
        if(mMeetingsRepository.compareEndTime(time)){
            endTimeProblemSendToast = true;
            return true;
        }
        else{ return false; }
    }

    public boolean startTimeProblemWithOtherMeetings(){ return startTimeProblemSendToast; }

    public boolean endTimeProblemWithOtherMeetings(){ return endTimeProblemSendToast; }

    public boolean startTimeOOB(){ return startTimeOOB; }

    public boolean endTimeOOB(){ return endTimeOOB; }

    public boolean startTimeAfterEndTime(){ return startTimeAfterEndToast; }

    public boolean endTimeBeforeStartTime(){ return endTimeBeforeStartToast; }


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
            return false;

        }
    }
}
