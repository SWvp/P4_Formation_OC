package com.kardabel.mareu.mareu.ui.list;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kardabel.mareu.R;
import com.kardabel.mareu.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.mareu.ui.MeetingsRecyclerViewAdapter;
import com.kardabel.mareu.mareu.ui.MeetingsViewState;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mareu);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MeetingsRecyclerViewAdapter mAdapter = new MeetingsRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        final MeetingViewModel meetingViewModel =
                new ViewModelProvider(this, MareuViewModelFactory.getInstance()).get(MeetingViewModel.class);

        meetingViewModel.getMeetingsListLiveData().observe(this, new Observer<List<MeetingsViewState>>() {
            @Override
            public void onChanged(List<MeetingsViewState> meetings) {
                mAdapter.setMeetings(meetings);
            }
        });

        //launch add meeting activity
        FloatingActionButton floatingActionButton = findViewById(R.id.add_meeting_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });

        //launch details meeting activity
        mAdapter.setOnItemClickListener(new MeetingsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onMeetingItemClick(MeetingsViewState meeting) {
                Intent intent = new Intent(MainActivity.this, MeetingDetailsActivity.class);
                intent.putExtra(MeetingDetailsActivity.EXTRA_DETAILS, meeting.getMeetingDetailsToDisplay());
                intent.putExtra(MeetingDetailsActivity.EXTRA_ROOM, meeting.getRoomName());
                intent.putExtra("picture", meeting.getAvatarToDisplay());
                startActivity(intent);
            }

            //callback delete button
            @Override
            public void onDeleteMeetingClick(int meeting) {
                meetingViewModel.deleteMeeting(meeting);
            }
        });
    }

    //topbar menu for filters options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.peach:

        }
        return super.onOptionsItemSelected(item);
    }
}
