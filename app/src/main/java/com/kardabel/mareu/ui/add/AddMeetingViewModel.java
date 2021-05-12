package com.kardabel.mareu.ui.add;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.ui.add.utils.AddMeetingViewAction;
import com.kardabel.mareu.ui.add.utils.SingleLiveEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class AddMeetingViewModel extends ViewModel {

    private MeetingsRepository mMeetingsRepository;

    private String mMeetingName;
    private int meetingId;

    private LocalTime mStartTime;
    private LocalTime mEndTime;
    private LocalDate mDate;

    private Room mRoomName;
    private Room mRoomAvatar;

    private List<Email> emails = new ArrayList<>();


    private SingleLiveEvent<AddMeetingViewAction> mActionSingleLiveEvent = new SingleLiveEvent<>();


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

    // When start time is picked
    public void onStartTimeSet(int hourOfDay, int minute) {
        mStartTime = LocalTime.of(hourOfDay, minute);

        if (mStartTime.isAfter(LocalTime.of(17, 00)) || mStartTime.isBefore(LocalTime.of(8, 00))) {
            mActionSingleLiveEvent.setValue(AddMeetingViewAction.DISPLAY_START_OOB);
            return;

        }
        if (mEndTime != null) {
            if (mStartTime.isAfter(mEndTime)) {
                mActionSingleLiveEvent.setValue(AddMeetingViewAction.DISPLAY_START_AFTER_ERROR);
                return;

            }
        }

        mActionSingleLiveEvent.setValue(AddMeetingViewAction.DISPLAY_START_HOUR);

    }

    // When end time is picked
    public void onEndTimeSet(int hourOfDay, int minute) {

        mEndTime = LocalTime.of(hourOfDay, minute);

        if (mEndTime.isAfter(LocalTime.of(18, 00)) || mEndTime.isBefore(LocalTime.of(9, 00))) {
            mActionSingleLiveEvent.setValue(AddMeetingViewAction.DISPLAY_END_OOB);
            return;

        }
        if (mStartTime != null) {
            if (mEndTime.isBefore(mStartTime)) {
                mActionSingleLiveEvent.setValue(AddMeetingViewAction.DISPLAY_END_BEFORE_ERROR);
                return;

            }
        }
        mActionSingleLiveEvent.setValue(AddMeetingViewAction.DISPLAY_END_HOUR);

    }

    // When a date is picked
    public void onDateSet(int year, int month, int dayOfMonth) {
        mDate = LocalDate.of(year, month+1, dayOfMonth);
        if(mDate.isBefore(LocalDate.now())){
            mActionSingleLiveEvent.setValue(AddMeetingViewAction.DATE_ERROR);
            return;

        }
        mActionSingleLiveEvent.setValue(AddMeetingViewAction.DISPLAY_DATE);

    }

    // Create meeting ID
    public int meetingId() {
        int lastmeetingId = mMeetingsRepository.findLastMeetingId();
        int meetingId = lastmeetingId + 1;
        return meetingId;

    }


    public void addEmails(String email) {
        emails.add(new Email(email));

    }

    public void onSaveButtonClick() {
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

    private boolean completeReunion() {
        if (mMeetingName != null && mStartTime != null && mEndTime != null && mDate !=null && mRoomName!= null && mRoomAvatar != null && emails!= null) {
            mActionSingleLiveEvent.setValue(AddMeetingViewAction.FINISH_ACTIVITY);
            return true;
        }
        else {
            mActionSingleLiveEvent.setValue(AddMeetingViewAction.DISPLAY_FIELDS_ERROR);
            return false;
        }
    }

    public SingleLiveEvent<AddMeetingViewAction> getViewActionSingleLiveEvent() {return mActionSingleLiveEvent;}

}
