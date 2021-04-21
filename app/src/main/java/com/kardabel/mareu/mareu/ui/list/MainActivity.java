package com.kardabel.mareu.mareu.ui.list;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

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
import com.kardabel.mareu.mareu.di.MareuViewModelFactory;
import com.kardabel.mareu.mareu.model.Room;
import com.kardabel.mareu.mareu.ui.MeetingsRecyclerViewAdapter;
import com.kardabel.mareu.mareu.ui.add.AddMeetingActivity;
import com.kardabel.mareu.mareu.ui.DatePickerFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {

    private MeetingViewModel meetingViewModel;

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

        meetingViewModel =
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
                intent.putExtra(MeetingDetailsActivity.EXTRA_MEETINGID, meeting.getMeetingId());
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

    //Topbar overflow for filters dropdown menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {   case R.id.date_filter:
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "filter date picker");
                return true;
            case R.id.peach:
                meetingViewModel.roomFilterValue(Room.ROOM_PEACH);
                return true;
            case R.id.mario:
                meetingViewModel.roomFilterValue(Room.ROOM_MARIO);
                return true;
            case R.id.luigi:
                meetingViewModel.roomFilterValue(Room.ROOM_LUIGI);
                return true;
            case R.id.toad:
                meetingViewModel.roomFilterValue(Room.ROOM_TOAD);
                return true;
            case R.id.yoshi:
                meetingViewModel.roomFilterValue(Room.ROOM_YOSHI);
                return true;
            case R.id.donkey:
                meetingViewModel.roomFilterValue(Room.ROOM_DONKEY);
                return true;
            case R.id.koopa:
                meetingViewModel.roomFilterValue(Room.ROOM_KOOPA);
                return true;
            case R.id.boo:
                meetingViewModel.roomFilterValue(Room.ROOM_BOO);
                return true;
            case R.id.goomba:
                meetingViewModel.roomFilterValue(Room.ROOM_GOOMBA);
                return true;
            case R.id.kamek:
                meetingViewModel.roomFilterValue(Room.ROOM_KAMEK);
                return true;
            case R.id.allMeeting:
                meetingViewModel.roomFilterValue(Room.ROOM_RESET);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "";
        //convertir les valeurs en string
        //les concaténer, puis les envoyer
        meetingViewModel.dateFilterValue(date);

    }
}
