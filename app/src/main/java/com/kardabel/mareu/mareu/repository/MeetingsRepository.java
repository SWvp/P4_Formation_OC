package com.kardabel.mareu.mareu.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kardabel.mareu.mareu.data.FakeDataStore;
import com.kardabel.mareu.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 31/03/2021.
 */
public class MeetingsRepository {
    private  MutableLiveData<List<Meeting>> mutableMeetingList = new MutableLiveData<>();
    private List<Meeting> nMeetings = FakeDataStore.generateMeetingList();

    //Modify meetings list
    public void insertMeeting(Meeting meeting){
        nMeetings.add(meeting);

    }

    public void deleteMeeting(Meeting meeting){
        nMeetings.remove(meeting);

    }

    public void deleteAllMeetings(){

    }

    //call the list of meetings from DataStore
    public LiveData<List<Meeting>> getMeetingsList() {
        mutableMeetingList.setValue(nMeetings);
        return mutableMeetingList;
    }


}
