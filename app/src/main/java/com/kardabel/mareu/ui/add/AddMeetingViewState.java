package com.kardabel.mareu.ui.add;

import com.kardabel.mareu.model.Meeting;

import java.util.List;

public class AddMeetingViewState {

    private  int meetingId;
    private  String meetingName;
    private  String roomName;
    private  String date;
    private  String hour;
    private  List<String> emails;

    public AddMeetingViewState(int meetingId, String meetingName, String roomName, String date, String hour, List<String> emails) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.roomName = roomName;
        this.date = date;
        this.hour = hour;
        this.emails = emails;
    }

    public String getAddMeetingName() {
        return meetingName;
    }

    public String getAddMeetingRoomName() {
        return roomName;
    }

    public String getAddMeetingDate() {
        return date;
    }

    public String getAddMeetingHour() {
        return hour;
    }

    public List<String> getAddMeetingEmails() {
        return emails;
    }


    public void setMeetingId(int meetingId) { this.meetingId = meetingId; }

    public void setAddMeetingName(String meetingName) { this.meetingName = meetingName; }

    public void setAddMeetingRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setAddMeetingDate(String date) {
        this.date = date;
    }

    public void setAddMeetingHour(String hour) { this.hour = hour; }

    public void getAddMeetingEmails(List<String> emails) { this.emails = emails; }

    public boolean completeReunion(AddMeetingViewState meeting)
    {
        if (meetingName != null && hour != null && date !=null && roomName!= null && emails!= null)
        {
            return true;

        }
        else
        {
            return false;

        }
    }

}