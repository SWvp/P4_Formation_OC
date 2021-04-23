package com.kardabel.mareu.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Email> mailingList;

    public Meeting(int meetingId, String meetingName, Room avatar, String hour, String date, Room roomName, List<Email> mailingList) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.hour = hour;
        this.date = date;
        this.roomName = roomName;
        this.roomAvatar = avatar;
        this.mailingList = mailingList;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public String getMeetingHour() {
        return hour;
    }

    public String getMeetingDate() {
        return date;
    }

    public Room getRoomName() {
        return roomName;
    }

    public Room getRoomAvatar() {
        return roomAvatar;
    }

    public List<String> getMailingList() {

        List<String> emailsString = new ArrayList<>();

        for (Email email : mailingList) {
            emailsString.add(email.getUrl());
        }

        return emailsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return meetingId == meeting.meetingId &&
            Objects.equals(meetingName, meeting.meetingName) &&
            Objects.equals(hour, meeting.hour) &&
            Objects.equals(date, meeting.date) &&
            roomName == meeting.roomName &&
            roomAvatar == meeting.roomAvatar &&
            Objects.equals(mailingList, meeting.mailingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId, meetingName, hour, date, roomName, roomAvatar, mailingList);
    }

    @Override
    public String toString() {
        return "Meeting{" +
            "meetingId=" + meetingId +
            ", meetingName='" + meetingName + '\'' +
            ", hour='" + hour + '\'' +
            ", date='" + date + '\'' +
            ", roomName=" + roomName +
            ", roomAvatar=" + roomAvatar +
            ", mailingList=" + mailingList +
            '}';
    }
}

