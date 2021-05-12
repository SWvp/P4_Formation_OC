package com.kardabel.mareu.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Meeting {
    private int meetingId;
    private String meetingName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Room roomName;
    private Room roomAvatar;
    private List<Email> mailingList;

    public Meeting(int meetingId, String meetingName, Room avatar, LocalTime startTime, LocalTime endTime, LocalDate date, Room roomName, List<Email> mailingList) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalTime getMeetingStart() {
        return startTime;
    }

    public LocalTime getMeetingEnd() {
        return endTime;
    }

    public LocalDate getMeetingDate() { return date; }

    public Room getRoomName() { return roomName; }

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
            Objects.equals(startTime, meeting.startTime) &&
            Objects.equals(endTime, meeting.endTime) &&
            Objects.equals(date, meeting.date) &&
            roomName == meeting.roomName &&
            roomAvatar == meeting.roomAvatar &&
            Objects.equals(mailingList, meeting.mailingList);

    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId, meetingName, startTime, endTime, date, roomName, roomAvatar, mailingList);

    }

    @Override
    public String toString() {
        return "Meeting{" +
            "meetingId=" + meetingId +
            ", meetingName='" + meetingName + '\'' +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", date='" + date + '\'' +
            ", roomName=" + roomName +
            ", roomAvatar=" + roomAvatar +
            ", mailingList=" + mailingList +
            '}';

    }
}

