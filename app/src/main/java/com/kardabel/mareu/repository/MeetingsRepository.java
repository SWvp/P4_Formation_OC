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

    private List<Meeting> nMeetingsInitial = FakeDataStore.generateMeetingList();

    private final MutableLiveData<List<Meeting>> mutableMeetingList = new MutableLiveData<>(nMeetings);

    public void addNewMeeting(Meeting meeting){
        nMeetings.add(meeting);
        mutableMeetingList.setValue(nMeetings);

    }

    public void deleteMeeting(int meetingId){
        for (Iterator<Meeting> iterator = nMeetings.iterator(); iterator.hasNext();){
            Meeting meeting = iterator.next();
            if(meeting.getMeetingId() == meetingId){
                iterator.remove();

            }
        }
        mutableMeetingList.setValue(nMeetings);

    }

    public int findLastMeetingId(){
        int size = nMeetings.size() - 1;
        int id = nMeetings.get(size).getMeetingId();
        return id;

    }

    public void resetListForNonPersistentData(){
        nMeetings = nMeetingsInitial;
        mutableMeetingList.setValue(nMeetings);

    }

    public LiveData<List<Meeting>> getMeetingsList() { return mutableMeetingList; }

}
