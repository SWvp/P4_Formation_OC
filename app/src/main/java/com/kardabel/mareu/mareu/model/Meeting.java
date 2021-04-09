package com.kardabel.mareu.mareu.model;


/**
 * Created by stéphane Warin OCR on 25/03/2021.
 */
public class Meeting {
    private int meetingId;
    private String meetingDetails;
    private String hour;
    private String room;
    private String roomAvatar;

    private String mailingList;

    public Meeting(int meetingId, String meetingDetails, String avatar, String hour, String room, String mailingList) {
        this.meetingId = meetingId;
        this.meetingDetails = meetingDetails;
        this.hour = hour;
        this.room = room;
        this.roomAvatar = avatar;
        this.mailingList = mailingList;
    }

    public int getMeetingId(){return meetingId;}

    public String getMeetingDetails() { return meetingDetails; }

    public String getMeetingHour() { return hour; }

    public String getMeetingRoom() { return room; }

    public String getRoomAvatar() {
        return roomAvatar;
    }

    public String getMailingList() {
        return mailingList;
    }
}

