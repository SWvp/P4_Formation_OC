package com.kardabel.mareu.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.kardabel.mareu.data.FakeDataStore;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;


public class MeetingsRepository {

    private List<Meeting> nMeetings = FakeDataStore.generateMeetingList();

    private final MutableLiveData<List<Meeting>> mutableMeetingList = new MutableLiveData<>(nMeetings);

    public void addNewMeeting(Meeting meeting){
        nMeetings.add(meeting);
        refreshMutableMeetingList(nMeetings);

    }

    public void deleteMeeting(int meetingId){
        for (Iterator<Meeting> iterator = nMeetings.iterator(); iterator.hasNext();){
            Meeting meeting = iterator.next();
            if(meeting.getMeetingId()==meetingId){
                iterator.remove();

            }
        }
        refreshMutableMeetingList(nMeetings);

    }

    public boolean compareDate(LocalDate date) {
        if(nMeetings.contains(date)){ return true; }
        else{ return false; }

    }

    public boolean compareRoom(Room room){
        if(nMeetings.contains(room)){ return true; }
        else{ return false; }

    }

    public boolean compareStartTime(LocalTime time){
        for(Meeting meeting : nMeetings){
            if((meeting.getMeetingStart().isBefore(time))&&(meeting.getMeetingEnd().isAfter(time))){
                return true;
            }
            else if ((meeting.getMeetingStart().equals(time))&&(meeting.getMeetingEnd().isAfter(time))){
                return true;
            }
        }
        return false;

    }

    public boolean compareEndTime(LocalTime time){
        for(Meeting meeting : nMeetings){
            if((meeting.getMeetingStart().isBefore(time))&&(meeting.getMeetingEnd().isAfter(time))){
                    return true;
            }
            else if ((meeting.getMeetingStart().isBefore(time))&&(meeting.getMeetingEnd().equals(time))){
                return true;
            }
        }
        return false;

    }

    public int findLastMeetingId(){
        int size = nMeetings.size() - 1;
        int id = nMeetings.get(size).getMeetingId();
        return id;

    }

    private void refreshMutableMeetingList(List<Meeting> meeting){ mutableMeetingList.setValue(meeting); }

    public LiveData<List<Meeting>> getMeetingsList() { return mutableMeetingList; }
}
