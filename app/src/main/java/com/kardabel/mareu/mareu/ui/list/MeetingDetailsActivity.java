package com.kardabel.mareu.mareu.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kardabel.mareu.R;
import com.kardabel.mareu.mareu.model.Meeting;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class MeetingDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DETAILS = "EXTRA_DETAILS";
    public static final String EXTRA_ROOM = "EXTRA_ROOM";
    public static final String EXTRA_DATE = "EXTRA_DATE";
    public static final String EXTRA_MAILINGLIST = "EXTRA_MAILINGLIST";
    private TextView meetingDetails;
    private ImageView meetingAvatar;
    private TextView roomMeetingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_details);

        meetingDetails = findViewById(R.id.meeting_name);
        meetingAvatar = findViewById(R.id.room_avatar);
        roomMeetingName = findViewById(R.id.room_name);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        meetingDetails.setText(intent.getStringExtra(EXTRA_DETAILS));
        roomMeetingName.setText(intent.getStringExtra(EXTRA_ROOM));

        int picture = bundle.getInt("picture");
        meetingAvatar.setImageResource(picture);



    }

}
