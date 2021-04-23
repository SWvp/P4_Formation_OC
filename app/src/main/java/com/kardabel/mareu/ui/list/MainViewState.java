package com.kardabel.mareu.ui.list;

import androidx.annotation.DrawableRes;

import java.util.Objects;

/**
 * Created by stéphane Warin OCR on 01/04/2021.
 */
public class MainViewState {
    private final int meetingId;
    private final String meetingDate;
    private final String meetingDetails;
    private final String roomName;
    @DrawableRes
    private final int avatarIcon;
    private final String mailingList;


    public MainViewState(int meetingId, String meetingDate, String meetingDetails, String roomName, @DrawableRes int avatarIcon, String mailingList) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainViewState that = (MainViewState) o;
        return meetingId == that.meetingId &&
            avatarIcon == that.avatarIcon &&
            Objects.equals(meetingDate, that.meetingDate) &&
            Objects.equals(meetingDetails, that.meetingDetails) &&
            Objects.equals(roomName, that.roomName) &&
            Objects.equals(mailingList, that.mailingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId, meetingDate, meetingDetails, roomName, avatarIcon, mailingList);
    }

    @Override
    public String toString() {
        return "MainViewState{" +
            "meetingId=" + meetingId +
            ", meetingDate='" + meetingDate + '\'' +
            ", meetingDetails='" + meetingDetails + '\'' +
            ", roomName='" + roomName + '\'' +
            ", avatarIcon=" + avatarIcon +
            ", mailingList='" + mailingList + '\'' +
            '}';
    }
}
