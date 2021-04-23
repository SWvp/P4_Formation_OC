package com.kardabel.mareu.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kardabel.mareu.R;
import com.kardabel.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.ui.details.DetailsActivityViewModel;
import com.kardabel.mareu.ui.details.DetailsActivityViewState;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MEETINGID = "EXTRA_MEETINGID";

    private TextView meetingName;
    private TextView hour;
    private TextView date;
    private ImageView meetingAvatar;
    private TextView roomMeetingName;
    private ImageButton backButton;
    private DetailsActivityViewModel detailsActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_details);

        meetingName = findViewById(R.id.detail_meeting_name);
        hour = findViewById(R.id.detail_hour);
        date = findViewById(R.id.detail_date);
        roomMeetingName = findViewById(R.id.detail_room_name);
        meetingAvatar = findViewById(R.id.detail_room_avatar);
        backButton = findViewById(R.id.back_button_meeting_details);


        detailsActivityViewModel =
                new ViewModelProvider(this, MareuViewModelFactory.getInstance()).get(DetailsActivityViewModel.class);

        Intent intent = getIntent();
        detailsActivityViewModel.init(intent.getIntExtra(EXTRA_MEETINGID, -1));


        detailsActivityViewModel.getDetailsLiveData().observe(this, new Observer<DetailsActivityViewState>() {
            @Override
            public void onChanged(DetailsActivityViewState meeting) {
                meetingName.setText(meeting.getDetailsMeetingName());
                hour.setText(meeting.getDetailsHour());
                date.setText(meeting.getDetailsDate());
                roomMeetingName.setText(meeting.getDetailsRoomName());
                meetingAvatar.setImageResource(meeting.getDetailsAvatarIcon());

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
