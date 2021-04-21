package com.kardabel.mareu.mareu.ui.add;

import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.repository.MeetingsRepository;

/**
 * Created by stéphane Warin OCR on 08/04/2021.
 */
public class AddActivityViewModel {
    private MeetingsRepository mMeetingsRepository;








    public void insertMeeting(Meeting meeting){
        mMeetingsRepository.insertMeeting(meeting);
    }
}
