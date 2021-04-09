package com.kardabel.mareu.mareu.views;

/**
 * Created by stéphane Warin OCR on 01/04/2021.
 */
public class MeetingsViewState {
    private final String meetingDetails;
    private final String mailingList;
    private final String avatarUrl;


    public MeetingsViewState(String meetingDetails, String mailingList, String avatarUrl) {
        this.meetingDetails = meetingDetails;
        this.mailingList = mailingList;
        this.avatarUrl = avatarUrl;
    }

    public String getMeetingDetailsToDisplay(){
        return meetingDetails;

    }

    public String getMailingListToDisplay(){
        return mailingList;

    }

    public String getAvatarUrlToDisplay(){
        return avatarUrl;

    }
}
