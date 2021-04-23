package com.kardabel.mareu.mareu.data;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kardabel.mareu.R;
import com.kardabel.mareu.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.model.Room;
import com.kardabel.mareu.mareu.ui.details.DetailsActivityViewModel;
import com.kardabel.mareu.mareu.ui.details.DetailsActivityViewState;

import java.util.ArrayList;
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





