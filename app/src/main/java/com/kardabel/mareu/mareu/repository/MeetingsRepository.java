package com.kardabel.mareu.mareu.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.kardabel.mareu.mareu.data.FakeDataStore;
import com.kardabel.mareu.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 31/03/2021.
 */
public class MeetingsRepository {

    private List<Meeting> nMeetings = FakeDataStore.generateMeetingList();

    private final MutableLiveData<List<Meeting>> mutableMeetingList = new MutableLiveData<>(nMeetings);

    //Modify meetings list
    public void insertMeeting(Meeting meeting){
        nMeetings.add(meeting);
        mutableMeetingList.setValue(nMeetings);
    }

    public void deleteMeeting(int meetingId){
        for (Iterator<Meeting> iterator = nMeetings.iterator(); iterator.hasNext();){
            Meeting meeting = iterator.next();
            if(meeting.getMeetingId()==meetingId){
                iterator.remove();
            }
        }
        mutableMeetingList.setValue(nMeetings);
    }

    public void deleteAllMeetings(){
        nMeetings.removeAll(nMeetings);
    }

    //call the list of meetings from DataStore
    public LiveData<List<Meeting>> getMeetingsList() {
        return mutableMeetingList;
    }
}
