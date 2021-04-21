package com.kardabel.mareu.mareu.ui.list;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.model.Room;
import com.kardabel.mareu.mareu.repository.MeetingsRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    private MeetingsRepository mMeetingsRepository;
    private MediatorLiveData<List<MeetingsViewState>> meetingsListMediatorLiveData = new MediatorLiveData<>();

    private MutableLiveData<Room> roomFilterMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<String> dateFilterMutableLiveData = new MutableLiveData<>();

    //Meeting list -> meeting viewState
    public MeetingViewModel(@NonNull MeetingsRepository meetingsRepository) {
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
        if((room == null) && (date == null) || room == Room.ROOM_RESET){
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
    private List<MeetingsViewState> map(List<Meeting> meetingList){
        List<MeetingsViewState> result = new ArrayList<>();

        for (Meeting meeting: meetingList) {
            String humanReadableHour = meeting.getMeetingHour().toString();
            String humanReadableDate = meeting.getMeetingDate().toString();

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

            result.add(new MeetingsViewState(
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

    public void roomFilterValue(Room room){
        roomFilterMutableLiveData.setValue(room);
    }

    public void dateFilterValue(String date){
        dateFilterMutableLiveData.setValue(date);
    }

    public void deleteMeeting(int meetingId){
        mMeetingsRepository.deleteMeeting(meetingId);
    }

    public LiveData<List<MeetingsViewState>> getMeetingsListLiveData(){ return meetingsListMediatorLiveData; }
}
