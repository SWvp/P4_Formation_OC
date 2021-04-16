package com.kardabel.mareu.mareu.ui.list;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.mareu.ui.MeetingsViewState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static androidx.lifecycle.Transformations.map;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class MeetingViewModel extends ViewModel {
    private MeetingsRepository mMeetingsRepository;
    private LiveData<List<MeetingsViewState>> meetingsListLiveData;

    //Meeting list -> meeting viewState
    public MeetingViewModel(@NonNull MeetingsRepository meetingsRepository) {
        mMeetingsRepository = meetingsRepository;
        meetingsListLiveData = Transformations.map(mMeetingsRepository.getMeetingsList(), new Function<List<Meeting>, List<MeetingsViewState>>() {
            @Override
            public List<MeetingsViewState> apply(List<Meeting> meetingList) {
                return map(meetingList);
            }
        });
    }

    //method to put elements from meeting list to meeting view state
    private List<MeetingsViewState> map(List<Meeting> meetingList){
        List<MeetingsViewState> result = new ArrayList<>();

        for (Meeting meeting: meetingList) {
            String humanReadableHour = meeting.getMeetingHour();

            result.add(new MeetingsViewState(
                    meeting.getMeetingId(),
                    meeting.getMeetingName() + " - " + humanReadableHour + " - " + meeting.getRoomName().getRoomMeetingName(),
                    meeting.getRoomName().getRoomMeetingName(),
                    meeting.getRoomAvatar().getDrawableRoomIcon(),
                    meeting.getMailingList()
            ));
        }
        return result;
    }

    public void roomFilter(){

    }

    public void insertMeeting(Meeting meeting){
        mMeetingsRepository.insertMeeting(meeting);

    }

    public void deleteMeeting(int meetingId){

        mMeetingsRepository.deleteMeeting(meetingId);
    }

    public void deleteAllMeetings(){
        mMeetingsRepository.deleteAllMeetings();

    }

    public LiveData<List<MeetingsViewState>> getMeetingsListLiveData(){
        return meetingsListLiveData;

    }
}
