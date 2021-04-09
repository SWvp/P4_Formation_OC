package com.kardabel.mareu.mareu.data;

import com.kardabel.mareu.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 31/03/2021.
 */
public class FakeDataStore {

    public static List<Meeting> Fake_Meetings = Arrays.asList(
            new Meeting(1,"Reuninon A","https://www.nicepng.com/png/full/32-328642_user-avatar-super-mario-avatar.png", "11h20", "Mario", "blake@gpamail.com"),
            new Meeting(2,"Reuninon B","https://www.nicepng.com/png/full/6-62950_paper-peach-color-splash-paper-mario-color-splash.png", "08h20", "Peach", "garogorille@gtromail.com")
    );


    public static List<Meeting> generateMeetingList() {

        return new ArrayList<>(Fake_Meetings);
    }

}
