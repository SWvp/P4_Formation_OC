package com.kardabel.mareu.ui.add;

import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AddMeetingViewState {

    private  String meetingName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Room roomName;
    private Room avatar;
    private  List<Email> emails;

    public AddMeetingViewState(String meetingName, LocalTime startTime, LocalTime endTime, LocalDate date, Room roomName, Room avatar, List<Email> emails) {
        this.meetingName = meetingName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.roomName = roomName;
        this.avatar = avatar;
        this.emails = emails;
    }

    //GETTERS
    public String getAddMeetingName() {
        return meetingName;
    }

    public LocalTime getAddMeetingStartTime() {
        return startTime;
    }

    public LocalTime getAddMeetingEndTime() {
        return endTime;
    }

    public LocalDate getAddMeetingDate() { return date; }

    public Room getAddMeetingRoomName() {
        return roomName;
    }

    public Room getAddMeetingAvatar() {
        return avatar;
    }

    public List<Email> getAddMeetingEmails() {
        return emails;
    }


    //SETTERS
    public void setAddMeetingName(String meetingName) { this.meetingName = meetingName; }

    public void setAddMeetingStartTime(LocalTime startTime) { this.startTime = startTime; }

    public void setAddMeetingEndTime(LocalTime endTime) { this.endTime = endTime; }

    public void setAddMeetingDate(LocalDate date) {
        this.date = date;
    }

    public void setAddMeetingRoomName(Room roomName) {
        this.roomName = roomName;
    }

    public void setAddMeetingAvatar(Room avatar) {
        this.avatar = avatar;
    }

    public void setAddMeetingEmails(List<Email> emails) { this.emails = emails; }


    //CHECK MEETING
    public boolean completeReunion(AddMeetingViewState meeting)
    {
        if (meetingName != null && startTime != null && endTime != null && date !=null && roomName!= null && emails!= null)
        {
            return true;

        }
        else
        {
            return false;

        }
    }

}
