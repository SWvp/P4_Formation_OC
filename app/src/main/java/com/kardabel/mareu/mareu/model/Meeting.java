package com.kardabel.mareu.mareu.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 25/03/2021.
 */
public class Meeting {
    private int meetingId;
    private String meetingName;
    private String hour;
    private String date;
    private Room roomName;
    private Room roomAvatar;
    private List<String> mailingList;

    public Meeting(int meetingId, String meetingName, Room avatar, String hour, String date, Room roomName, List<String> mailingList) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.hour = hour;
        this.date = date;
        this.roomName = roomName;
        this.roomAvatar = avatar;
        this.mailingList = mailingList;
    }

    public int getMeetingId(){return meetingId;}

    public String getMeetingName() { return meetingName; }

    public String getMeetingHour() { return hour; }

    public String getMeetingDate() { return date; }

    public Room getRoomName() { return roomName; }

    public Room getRoomAvatar() {
        return roomAvatar;
    }

    public List<String> getMailingList() {
        return mailingList;
    }
}

