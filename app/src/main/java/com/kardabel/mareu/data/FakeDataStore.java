package com.kardabel.mareu.data;

import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stéphane Warin OCR on 31/03/2021.
 */
public class FakeDataStore {

    public static List<Meeting> Fake_Meetings = List.of(
            new Meeting(
                0,
                "Reuninon A",
                Room.ROOM_MARIO,
                "11h20",
                "12-05-2021",
                Room.ROOM_MARIO,
                List.of(
                    new Email("Stéphane", "stephane@monmail.fr"),
                    new Email("Nino", "nino@monmail.fr")
                )
            ),
            new Meeting(1,"Reuninon B",Room.ROOM_PEACH, "08h20","13-05-2021", Room.ROOM_PEACH, List.of(
                new Email("Stéphane", "stephane@monmail.fr"),
                new Email("Nino", "nino@monmail.fr")
            )),
            new Meeting(2,"Reuninon c",Room.ROOM_GOOMBA, "15h30", "14-05-2021",Room.ROOM_GOOMBA, List.of(
                new Email("Stéphane", "stephane@monmail.fr"),
                new Email("Nino", "nino@monmail.fr")
            )),
            new Meeting(3,"Reuninon D",Room.ROOM_BOO, "13h00", "15-05-2021",Room.ROOM_BOO, List.of(
                new Email("Stéphane", "stephane@monmail.fr"),
                new Email("Nino", "nino@monmail.fr")
            ))

    );

    public static List<Meeting> generateMeetingList() {
        return new ArrayList<>(Fake_Meetings);
    }

}
