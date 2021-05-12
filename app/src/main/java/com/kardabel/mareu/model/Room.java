package com.kardabel.mareu.model;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.kardabel.mareu.R;


public enum Room {
    ROOM_RESET(R.color.white, "Reset"),
    ROOM_PEACH(R.color.peach, "Peach"),
    ROOM_MARIO(R.color.mario, "Mario"),
    ROOM_LUIGI(R.color.luigi, "Luigi"),
    ROOM_TOAD(R.color.toad, "Toad"),
    ROOM_YOSHI(R.color.yoshi, "Yoshi"),
    ROOM_DONKEY(R.color.donkey, "Donkey"),
    ROOM_KOOPA(R.color.koopa, "Koopa"),
    ROOM_BOO(R.color.boo, "Boo"),
    ROOM_GOOMBA(R.color.goomba, "Goomba"),
    ROOM_KAMEK(R.color.kamek, "Kamek");

    @ColorRes
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
