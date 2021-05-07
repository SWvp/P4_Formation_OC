package com.kardabel.mareu.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.kardabel.mareu.R;
import com.kardabel.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.ui.details.DetailsViewModel;
import com.kardabel.mareu.ui.details.DetailsViewState;

import java.util.List;


public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MEETINGID = "EXTRA_MEETINGID";

    private TextView meetingName;
    private TextView startAndEndTime;
    private TextView date;
    private TextView roomMeetingName;
    private ImageView meetingAvatar;
    private ImageButton backButton;

    private ChipGroup chipGroup;

    private DetailsViewModel mDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_details);

        meetingName = findViewById(R.id.detail_meeting_name);
        startAndEndTime = findViewById(R.id.detail_hour);
        date = findViewById(R.id.detail_date);
        roomMeetingName = findViewById(R.id.detail_room_name);
        meetingAvatar = findViewById(R.id.detail_room_avatar);
        backButton = findViewById(R.id.back_button_meeting_details);
        chipGroup = findViewById(R.id.chipgroup_detail_emails);


        mDetailsViewModel =
                new ViewModelProvider(this, MareuViewModelFactory.getInstance()).get(DetailsViewModel.class);

        Intent intent = getIntent();
        mDetailsViewModel.init(intent.getIntExtra(EXTRA_MEETINGID, -1));


        mDetailsViewModel.getDetailsLiveData().observe(this, new Observer<DetailsViewState>() {
            @Override
            public void onChanged(DetailsViewState meeting) {
                List<String> emails;
                meetingName.setText(meeting.getDetailsMeetingName());
                startAndEndTime.setText(meeting.getDetailsStartTime());
                date.setText(meeting.getDetailsDate());
                roomMeetingName.setText(meeting.getDetailsRoomName());
                meetingAvatar.setImageResource(meeting.getDetailsAvatarIcon());
                emails = meeting.getDetailsEmails();
                for(String email : emails){
                    addNewChipEmails(email);

                }
            }
        });

        // Back to main activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }

    private  void addNewChipEmails(String email){
        LayoutInflater inflater = LayoutInflater.from(this);
        Chip newChip = (Chip) inflater.inflate(R.layout.mail_chip_item, this.chipGroup, false);
        newChip.setText(email);
        this.chipGroup.addView(newChip);

    }
}
