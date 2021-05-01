package com.kardabel.mareu.data;

import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FakeDataStore {

    public static List<Meeting> Fake_Meetings = Arrays.asList(
            new Meeting(0, "Reunion A", Room.ROOM_MARIO, LocalTime.of(15, 10), LocalTime.of(16, 50), LocalDate.of(2021, 05, 12), Room.ROOM_MARIO, Arrays.asList(
                    new Email("stephane@monmail.fr"),
                    new Email("peteretsteven@monmail.fr")
            )),
            new Meeting(1, "Reunion B", Room.ROOM_PEACH, LocalTime.of(9, 00), LocalTime.of(11, 20), LocalDate.of(2021, 05, 01), Room.ROOM_PEACH, Arrays.asList(
                    new Email("warin@monmail.fr"),
                    new Email("peteretsteven@monmail.fr")
            )),
            new Meeting(2, "Reunion c", Room.ROOM_GOOMBA, LocalTime.of(11, 8), LocalTime.of(13,4), LocalDate.of(2021, 06, 15), Room.ROOM_GOOMBA, Arrays.asList(
                    new Email("philibert@monmail.fr"),
                    new Email("peteretsteven@monmail.fr")
            )),
            new Meeting(3, "Reunion D", Room.ROOM_BOO, LocalTime.of(16, 50), LocalTime.of(18,00), LocalDate.of(2021, 10, 02), Room.ROOM_BOO, Arrays.asList(
                    new Email("krabulbe@monmail.fr"),
                    new Email("peteretsteven@monmail.fr")
            ))

    );

    public static List<Meeting> generateMeetingList() {
        return new ArrayList<>(Fake_Meetings);
    }

}
