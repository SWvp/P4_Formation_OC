package com.kardabel.mareu.mareu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.mareu.views.MeetingsViewState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class MeetingActivityViewModel extends ViewModel {
    private MeetingsRepository mMeetingsRepository;
    private LiveData<List<Meeting>> meetingsList;

    public MeetingActivityViewModel(@NonNull MeetingsRepository meetingsRepository) {
        mMeetingsRepository = new MeetingsRepository();
        meetingsList = mMeetingsRepository.getMeetingsList();

    }

    public void insertMeeting(Meeting meeting){
        mMeetingsRepository.insertMeeting(meeting);

    }

    public void deleteMeeting(Meeting meeting){
        mMeetingsRepository.deleteMeeting(meeting);
    }

    public void deleteAllMeetings(){
        mMeetingsRepository.deleteAllMeetings();

    }

    public LiveData<List<Meeting>> getMeetingsList(){
        return meetingsList;

    }
}
