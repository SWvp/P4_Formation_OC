package com.kardabel.mareu.ui.details;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.repository.MeetingsRepository;

import java.util.List;

public class DetailsViewModel extends ViewModel {
    private MeetingsRepository mMeetingsRepository;

    private LiveData<DetailsViewState> meetingsDetailsLiveData;

    private int meetingId = -1;

    private DetailsViewState result;

    public DetailsViewModel(@NonNull MeetingsRepository meetingsRepository) {
        mMeetingsRepository = meetingsRepository;
        meetingsDetailsLiveData = Transformations.map(mMeetingsRepository.getMeetingsList(), new Function<List<Meeting>, DetailsViewState>() {
            @Override
            public DetailsViewState apply(List<Meeting> meeting) {
                return map(meeting);

            }
        });
    }

    public void init(int meetingId){
        this.meetingId = meetingId;
    }

    private DetailsViewState map(List<Meeting> meetings){
        for (Meeting meeting: meetings) {
            String humanReadableStartTime = meeting.getMeetingStart().toString();
            String humanReadableEndTime = meeting.getMeetingEnd().toString();
            String humanReadableDate = meeting.getMeetingDate().toString();
            if (meetingId == meeting.getMeetingId()) {

                result = new DetailsViewState(
                        meeting.getMeetingId(),
                        meeting.getMeetingName(),
                        humanReadableStartTime + " to " + humanReadableEndTime,
                        humanReadableEndTime,
                        humanReadableDate,
                        meeting.getRoomName().getRoomMeetingName(),
                        meeting.getRoomAvatar().getDrawableRoomIcon(),
                        meeting.getMailingList()

                );
            }
        }
        return result;

    }

    public LiveData<DetailsViewState> getDetailsLiveData(){ return meetingsDetailsLiveData; }

}
