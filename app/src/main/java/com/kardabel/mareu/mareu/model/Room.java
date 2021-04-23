package com.kardabel.mareu.mareu.model;

import androidx.annotation.DrawableRes;

import com.kardabel.mareu.R;

/**
 * Created by stéphane Warin OCR on 12/04/2021.
 */
public enum Room {
    ROOM_RESET(R.drawable.peach, "Reset"),
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
        this.roomName =roomName;

    }

    public int getDrawableRoomIcon(){
        return roomIcon;
    }

    public String getRoomMeetingName(){ return roomName; }

}
