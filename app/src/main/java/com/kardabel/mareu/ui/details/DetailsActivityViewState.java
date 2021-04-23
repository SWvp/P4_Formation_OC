package com.kardabel.mareu.ui.details;

import androidx.annotation.DrawableRes;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by stéphane Warin OCR on 16/04/2021.
 */
public class DetailsActivityViewState extends ViewModel {

    private final int meetingId;
    private final String meetingName;
    private final String hour;
    private final String date;
    private final String roomName;
    @DrawableRes
    private final int avatarIcon;
    private final List<String> mailingList;

    public DetailsActivityViewState(int meetingId, String meetingName, String hour, String date, String roomName, int avatarIcon, List<String> mailingList) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.hour = hour;
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

    public String getDetailsHour() {
        return hour;
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

    public List<String> getDetailsMailingList() {
        return mailingList;
    }
}
