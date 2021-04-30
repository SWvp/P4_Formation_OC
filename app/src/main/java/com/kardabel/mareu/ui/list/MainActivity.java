package com.kardabel.mareu.ui.list;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kardabel.mareu.R;
import com.kardabel.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.ui.add.AddMeetingActivity;
import com.kardabel.mareu.ui.DatePickerFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {

    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mareu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MeetingsRecyclerViewAdapter mAdapter = new MeetingsRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mMainViewModel =
                new ViewModelProvider(this, MareuViewModelFactory.getInstance()).get(MainViewModel.class);

        mMainViewModel.getMeetingsListLiveData().observe(this, new Observer<List<MainViewState>>() {
            @Override
            public void onChanged(List<MainViewState> meetings) {
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

        //Delete Button, launch details from recycler items
        mAdapter.setOnItemClickListener(new MeetingsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onMeetingItemClick(MainViewState meeting) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.EXTRA_MEETINGID, meeting.getMeetingId());
                startActivity(intent);

            }

            //callback delete button
            @Override
            public void onDeleteMeetingClick(int meeting) {
                mMainViewModel.deleteMeeting(meeting);

            }
        });
    }

    //Topbar overflow for filters dropdown menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar, menu);
        return true;

    }

    //Filters action on viewmodel
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        { case R.id.reset_filter:
                mMainViewModel.resetFilter(true);
                return true;
            case R.id.date_filter:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "filter date picker");
                return true;
            case R.id.peach:
                mMainViewModel.roomFilterValue(Room.ROOM_PEACH);
                return true;
            case R.id.mario:
                mMainViewModel.roomFilterValue(Room.ROOM_MARIO);
                return true;
            case R.id.luigi:
                mMainViewModel.roomFilterValue(Room.ROOM_LUIGI);
                return true;
            case R.id.toad:
                mMainViewModel.roomFilterValue(Room.ROOM_TOAD);
                return true;
            case R.id.yoshi:
                mMainViewModel.roomFilterValue(Room.ROOM_YOSHI);
                return true;
            case R.id.donkey:
                mMainViewModel.roomFilterValue(Room.ROOM_DONKEY);
                return true;
            case R.id.koopa:
                mMainViewModel.roomFilterValue(Room.ROOM_KOOPA);
                return true;
            case R.id.boo:
                mMainViewModel.roomFilterValue(Room.ROOM_BOO);
                return true;
            case R.id.goomba:
                mMainViewModel.roomFilterValue(Room.ROOM_GOOMBA);
                return true;
            case R.id.kamek:
                mMainViewModel.roomFilterValue(Room.ROOM_KAMEK);
                return true;
            case R.id.allMeeting:
                mMainViewModel.roomFilterValue(Room.ROOM_RESET);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    //When time filter picked
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mMainViewModel.onDateFilterSetMainViewModel(view, year, month, dayOfMonth);

    }
}
