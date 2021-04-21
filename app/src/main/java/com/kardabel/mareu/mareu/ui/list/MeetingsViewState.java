package com.kardabel.mareu.mareu.ui.list;

import androidx.annotation.DrawableRes;

import java.util.List;

/**
 * Created by stéphane Warin OCR on 01/04/2021.
 */
public class MeetingsViewState {
    private final int meetingId;
    private final String meetingDate;
    private final String meetingDetails;
    private final String roomName;
    @DrawableRes
    private final int avatarIcon;
    private final String mailingList;


    public MeetingsViewState(int meetingId, String meetingDate, String meetingDetails, String roomName, @DrawableRes int avatarIcon, String mailingList) {
        this.meetingId = meetingId;
        this.meetingDate = meetingDate;
        this.meetingDetails = meetingDetails;
        this.roomName = roomName;
        this.avatarIcon = avatarIcon;
        this.mailingList = mailingList;
    }

    public int getMeetingId(){

        return meetingId;
    }

    public String getMeetingDateToDisplay(){
        return meetingDate;

    }

    public String getMeetingDetailsToDisplay(){
        return meetingDetails;

    }

    public String getRoomNameToDisplay(){
        return roomName;

    }

    public int getAvatarToDisplay(){
        return avatarIcon;

    }

    public String getMailingListToDisplay(){
        return mailingList;

    }

}
