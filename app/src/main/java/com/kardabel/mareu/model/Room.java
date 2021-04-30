package com.kardabel.mareu.model;

import androidx.annotation.DrawableRes;

import com.kardabel.mareu.R;

/**
 * Created by stéphane Warin OCR on 12/04/2021.
 */
public enum Room {
    ROOM_RESET(R.drawable.blank, "Reset"),
    ROOM_PEACH(R.drawable.peach, "Peach"),
    ROOM_MARIO(R.drawable.mario, "Mario"),
    ROOM_LUIGI(R.drawable.luigi, "Luigi"),
    ROOM_TOAD(R.drawable.toad, "Toad"),
    ROOM_YOSHI(R.drawable.yoshi, "Yoshi"),
    ROOM_DONKEY(R.drawable.donkey, "Donkey"),
    ROOM_KOOPA(R.drawable.koopa, "Koopa"),
    ROOM_BOO(R.drawable.boo, "Boo"),
    ROOM_GOOMBA(R.drawable.goomba, "Goomba"),
    ROOM_KAMEK(R.drawable.kamek, "Kamek");

    @DrawableRes
    private final int roomIcon;
    private final String roomName;

    Room(int roomIcon, String roomName) {
        this.roomIcon = roomIcon;
        this.roomName = roomName;

    }

    public int getDrawableRoomIcon(){
        return roomIcon;
    }

    public String getRoomMeetingName(){ return roomName; }

    public static Room contains(String addRoomName) {
        Room room2 = ROOM_RESET;

        for (Room room : Room.values()) {
            if (room.roomName.equals(addRoomName)) {
                room2 = room;
            }
        }
        return room2;
    }



}
