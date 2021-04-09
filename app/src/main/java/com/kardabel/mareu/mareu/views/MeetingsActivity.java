package com.kardabel.mareu.mareu.views;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kardabel.mareu.R;
import com.kardabel.mareu.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.mareu.model.Meeting;
import com.kardabel.mareu.mareu.viewmodel.MeetingActivityViewModel;


import java.util.List;

public class MeetingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mareu);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MeetingsRecyclerViewAdapter mAdapter = new MeetingsRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        final MeetingActivityViewModel mareuViewModel = new ViewModelProvider(this, MareuViewModelFactory.getInstance()).get(MeetingActivityViewModel.class);


        mareuViewModel.getMeetingsList().observe(this, new Observer<List<Meeting>>() {
            @Override
            public void onChanged(@Nullable List<Meeting> meetings ) {
                mAdapter.setMeetings(meetings);
                //mAdapter.submitList(meetings);
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.add_meeting_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeetingsActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnItemClickListener(new MeetingsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onMeetingItemClick(Meeting meeting) {
                Intent intent = new Intent(MeetingsActivity.this, MeetingDetailsActivity.class);
                startActivity(intent);

            }
        });

    }

}