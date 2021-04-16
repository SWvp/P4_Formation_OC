package com.kardabel.mareu.mareu.ui.details;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.mareu.ui.MeetingsViewState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 08/04/2021.
 */
public class DetailsActivityViewModel {
    private MeetingsRepository mMeetingsRepository;
    private LiveData<List<DetailsActivityViewState>> meetingsListLiveData;

    public DetailsActivityViewModel(@NonNull MeetingsRepository meetingsRepository) {
        mMeetingsRepository = meetingsRepository;
        meetingsListLiveData = Transformations.map(mMeetingsRepository.getMeetingsList(), new Function<List<Meeting>, List<DetailsActivityViewState>>() {
            @Override
            public List<DetailsActivityViewState> apply(List<Meeting> meetingList) {
                return map(meetingList);
            }
        });
    }

    private List<DetailsActivityViewState> map(List<Meeting> meetingList){
        List<DetailsActivityViewState> result = new ArrayList<>();

        for (Meeting meeting: meetingList) {
            String humanReadableHour = meeting.getMeetingHour();

            result.add(new DetailsActivityViewState(
                    meeting.getMeetingId(),
                    meeting.getMeetingName(),
                    meeting.getMeetingHour(),
                    meeting.getRoomName().getRoomMeetingName(),
                    meeting.getRoomAvatar().getDrawableRoomIcon(),
                    meeting.getMailingList()
            ));
        }
        return result;
    }
}
