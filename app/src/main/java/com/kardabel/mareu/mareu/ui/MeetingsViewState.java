package com.kardabel.mareu.mareu.ui;

import androidx.annotation.DrawableRes;

import java.util.List;

/**
 * Created by stéphane Warin OCR on 01/04/2021.
 */
public class MeetingsViewState {
    private final int meetingId;
    private final String meetingDetails;
    private final String roomName;
    @DrawableRes
    private final int avatarIcon;
    private final List<String> mailingList;


    public MeetingsViewState(int meetingId, String meetingDetails, String roomName, @DrawableRes int avatarIcon, List<String> mailingList) {
        this.meetingId = meetingId;
        this.meetingDetails = meetingDetails;
        this.roomName = roomName;
        this.avatarIcon = avatarIcon;
        this.mailingList = mailingList;
    }

    public int getMeetingId(){

        return meetingId;
    }

    public String getMeetingDetailsToDisplay(){
        return meetingDetails;

    }

    public String getRoomName(){
        return roomName;

    }

    public int getAvatarToDisplay(){
        return avatarIcon;

    }

    public List<String> getMailingListToDisplay(){
        return mailingList;

    }

}
