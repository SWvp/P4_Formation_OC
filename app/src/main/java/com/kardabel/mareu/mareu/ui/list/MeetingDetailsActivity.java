package com.kardabel.mareu.mareu.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kardabel.mareu.R;
import com.kardabel.mareu.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.ui.details.DetailsActivityViewModel;
import com.kardabel.mareu.mareu.ui.details.DetailsActivityViewState;

import java.util.List;

/**
 * Created by stéphane Warin OCR on 26/03/2021.
 */
public class MeetingDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MEETINGID = "EXTRA_MEETINGID";

    private TextView meetingDetails;
    private ImageView meetingAvatar;
    private TextView roomMeetingName;
    private DetailsActivityViewModel detailsActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_details);

        detailsActivityViewModel =
                new ViewModelProvider(this, MareuViewModelFactory.getInstance()).get(DetailsActivityViewModel.class);

        DetailsActivityViewState viewState;


        meetingDetails = findViewById(R.id.meeting_name);
        meetingAvatar = findViewById(R.id.room_avatar);
        roomMeetingName = findViewById(R.id.room_name);

        Intent intent = getIntent();

        detailsActivityViewModel.init(intent.getIntExtra(EXTRA_MEETINGID, -1));

        //meetingDetails.setText(viewState.getMeetingName());



    }

}
