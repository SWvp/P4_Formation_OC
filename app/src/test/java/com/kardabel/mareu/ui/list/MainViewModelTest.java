package com.kardabel.mareu.ui.list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.kardabel.mareu.R;
import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.repository.MeetingsRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MainViewModelTest {

    @Rule
    public final InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private final MeetingsRepository mMeetingsRepository = Mockito.mock(MeetingsRepository.class);

    private final MutableLiveData<List<Meeting>> meetingsMutableLiveData = new MutableLiveData<>();

    private MainViewModel mMainViewModel;

    @Before
    public void setUp() {
        Mockito.doReturn(meetingsMutableLiveData).when(mMeetingsRepository).getMeetingsList();

        mMainViewModel = new MainViewModel(mMeetingsRepository);
    }

    @Test
    public void nominal_case() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(result, getDefaultMainViewState());
    }

    @Test
    public void given_0_meeting_should_display_empty_state() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(new ArrayList<>());

        // When
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(0, result.size());
    }


    @Test
    public void given_room_filter_is_mario_should_display_1_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.roomFilterValue(Room.ROOM_MARIO);
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(1, result.size());
    }

    // IN
    private List<Meeting> getDefaultMeetings() {
        return Arrays.asList(
            new Meeting(
                0,
                "Reuninon A",
                Room.ROOM_MARIO,
                "11h20",
                "12-05-2021",
                Room.ROOM_MARIO,
                Arrays.asList(
                    new Email("Stéphane", "stephane@monmail.fr"),
                    new Email("Nino", "nino@monmail.fr")
                )
            )
        );
    }

    // OUT
    private List<MainViewState> getDefaultMainViewState() {
        return  Arrays.asList(
            new MainViewState(
                0,
                "12-05-2021",
                "Reuninon A - 11h20 - Mario",
                "Mario",
                R.drawable.mario,
                "stephane@monmail.fr - nino@monmail.fr"
            )
        );
    }
}