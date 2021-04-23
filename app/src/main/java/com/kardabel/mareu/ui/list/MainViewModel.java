package com.kardabel.mareu.ui.list;

import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.repository.MeetingsRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final MeetingsRepository mMeetingsRepository;

    private final MediatorLiveData<List<MainViewState>> meetingsListMediatorLiveData = new MediatorLiveData<>();

    private final MutableLiveData<Room> roomFilterMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> dateFilterMutableLiveData = new MutableLiveData<>();

    //Observers Triggers
    public MainViewModel(@NonNull MeetingsRepository meetingsRepository) {
        mMeetingsRepository = meetingsRepository;

        LiveData<List<Meeting>> meetingsListLiveData = mMeetingsRepository.getMeetingsList();

        meetingsListMediatorLiveData.addSource(meetingsListLiveData, new Observer<List<Meeting>>() {
            @Override
            public void onChanged(List<Meeting> meetings) {
                combine(meetings, roomFilterMutableLiveData.getValue(), dateFilterMutableLiveData.getValue());

            }
        });

        meetingsListMediatorLiveData.addSource(roomFilterMutableLiveData, new Observer<Room>() {
            @Override
            public void onChanged(Room room) {
                combine(meetingsListLiveData.getValue(), room, dateFilterMutableLiveData.getValue());

            }
        });

        meetingsListMediatorLiveData.addSource(dateFilterMutableLiveData, new Observer<String>() {
            @Override
            public void onChanged(String Date) {
                combine(meetingsListLiveData.getValue(), roomFilterMutableLiveData.getValue(), Date);

            }
        });
    }

    //Filter the LiveData
    private void combine(@Nullable List<Meeting> meetings, @Nullable Room room, @Nullable String date) {
        List<Meeting> meetingsToAdd = new ArrayList<>();
        if((room == null) && (date == null)){
            meetingsListMediatorLiveData.setValue(map(meetings));

        }
        else{
            for (Meeting meeting : meetings) {
                if(meeting.getRoomName().equals(room) || meeting.getMeetingDate().equals(date)){
                    meetingsToAdd.add(meeting);

                }
                meetingsListMediatorLiveData.setValue(map(meetingsToAdd));

            }
        }
    }

    //Create meeting view state
    private List<MainViewState> map(List<Meeting> meetings){
        List<MainViewState> result = new ArrayList<>();

        for (Meeting meeting: meetings) {
            String humanReadableHour = meeting.getMeetingHour().toString();
            String humanReadableDate = meeting.getMeetingDate().toString();
            String humanReadableEmails = readableEmails(meeting);

            result.add(new MainViewState(
                    meeting.getMeetingId(),
                    humanReadableDate,
                    meeting.getMeetingName() + " - " + humanReadableHour + " - " + meeting.getRoomName().getRoomMeetingName(),
                    meeting.getRoomName().getRoomMeetingName(),
                    meeting.getRoomAvatar().getDrawableRoomIcon(),
                    humanReadableEmails

            ));
        }
        return result;
    }

    private String readableEmails(Meeting meeting){
        String humanReadableEmails;
        List<String> emails = meeting.getMailingList();
        String spaceDelimitation = " - ";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while ( i < emails.size() - 1){
            sb.append(emails.get(i));
            sb.append(spaceDelimitation);
            i++;

        }
        sb.append(emails.get(i));
        humanReadableEmails = sb.toString();
        return humanReadableEmails;

    }

    public void roomFilterValue(Room room){
        if(dateFilterMutableLiveData.getValue() != null){
            dateFilterMutableLiveData.setValue(null);

        }
        roomFilterMutableLiveData.setValue(room);
    }

    public void onDateSetMainViewModel(DatePicker view, int year, int month, int dayOfMonth) {

        int intYearValue = year;
        int intMonthValue = month;
        int intDayValue = dayOfMonth;

        String stringYearValue = String.valueOf(intYearValue);
        String stringMonthValue = String.valueOf(intMonthValue);
        String stringDayValue = String.valueOf(intDayValue);

        if(dayOfMonth < 10){
            stringDayValue = "0" + stringDayValue;

        }
        else if (month < 10){
            stringMonthValue = "0" + stringMonthValue;

        }
        String date = (stringDayValue + "-" + stringMonthValue + "-" + stringYearValue);
        dateFilterValue(date);

    }

    public void dateFilterValue(String date){
        if(roomFilterMutableLiveData != null){
            roomFilterMutableLiveData.setValue(null);

        }
        dateFilterMutableLiveData.setValue(date);

    }

    public void resetFilter(Boolean reset){
        dateFilterMutableLiveData.setValue(null);
        roomFilterMutableLiveData.setValue(null);
    }

    public void deleteMeeting(int meetingId){
        mMeetingsRepository.deleteMeeting(meetingId);
    }

    public LiveData<List<MainViewState>> getMeetingsListLiveData(){ return meetingsListMediatorLiveData; }

}