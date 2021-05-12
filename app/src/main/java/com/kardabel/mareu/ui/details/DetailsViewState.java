package com.kardabel.mareu.ui.details;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class DetailsViewState extends ViewModel {

    private final int meetingId;
    private final String meetingName;
    private final String startTime;
    private final String date;
    private final String roomName;
    @ColorRes
    private final int avatarIcon;
    private final List<String> mailingList;

    public DetailsViewState(int meetingId, String meetingName, String startTime, String date, String roomName, int avatarIcon, List<String> mailingList) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.startTime = startTime;
        this.date = date;
        this.roomName = roomName;
        this.avatarIcon = avatarIcon;
        this.mailingList = mailingList;

    }

    public int getDetailsMeetingId() {
        return meetingId;
    }

    public String getDetailsMeetingName() {
        return meetingName;
    }

    public String getDetailsStartTime() {
        return startTime;
    }

    public String getDetailsDate() {
        return date;
    }

    public String getDetailsRoomName() {
        return roomName;
    }

    public int getDetailsAvatarIcon() {
        return avatarIcon;
    }

    public List<String> getDetailsEmails() {
        return mailingList;
    }

}
