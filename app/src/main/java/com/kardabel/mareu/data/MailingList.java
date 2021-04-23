package com.kardabel.mareu.data;

import com.kardabel.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 17/04/2021.
 */
public class MailingList {
    private int meetingId;
    private String emailingList;

    public MailingList(int meetingId, String emailingList) {
        this.meetingId = meetingId;
        this.emailingList = emailingList;
    }

    public static List<MailingList> mailingLists = Arrays.asList(
            new MailingList(0, "eyeyye@eueue.com")

    );

    public int getMeetingId() {
        return meetingId;
    }

    public List<MailingList> getMailingList() {
        return mailingLists;
    }
}





