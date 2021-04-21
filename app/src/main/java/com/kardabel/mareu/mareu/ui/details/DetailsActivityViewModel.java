package com.kardabel.mareu.mareu.ui.details;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.mareu.ui.list.MeetingsViewState;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivityViewModel extends ViewModel {
    private MeetingsRepository mMeetingsRepository;
    private LiveData<DetailsActivityViewState> meetingsDetailsLiveData;

    private int meetingId = -1;

    public DetailsActivityViewModel(@NonNull MeetingsRepository meetingsRepository) {
        mMeetingsRepository = meetingsRepository;
        meetingsDetailsLiveData = Transformations.map(mMeetingsRepository.getMeetingsList(), new Function<List<Meeting>, DetailsActivityViewState>() {
            @Override
            public DetailsActivityViewState apply(List<Meeting> meeting) {
                return map(meeting);
            }
        });
    }

    public void init(int meetingId){
        this.meetingId = meetingId;
    }

    private DetailsActivityViewState map(List<Meeting> meetingList){
        DetailsActivityViewState result = null;

        for (Meeting meeting: meetingList) {
            if (meetingId == meeting.getMeetingId()) {
                String humanReadableHour = meeting.getMeetingHour();

                result = new DetailsActivityViewState(
                        meeting.getMeetingId(),
                        meeting.getMeetingName(),
                        meeting.getMeetingHour(),
                        meeting.getRoomName().getRoomMeetingName(),
                        meeting.getRoomAvatar().getDrawableRoomIcon(),
                        meeting.getMailingList()
                );

            }
        }
        return result;
    }
}
