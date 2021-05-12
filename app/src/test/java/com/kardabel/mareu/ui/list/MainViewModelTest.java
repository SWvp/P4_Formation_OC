package com.kardabel.mareu.ui.list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.kardabel.mareu.R;
import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.ui.list.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


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
    public void given_3_meetings_should_display_3_meetings() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getThreeMeetings());

        // When
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(3, result.size());

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
        MainViewState firstResult = result.get(0);
        assertEquals(new MainViewState(0, "2021-05-12", "Reunion A - 15:10 - Mario", "Mario", R.color.mario, "stephane@monmail.fr - peteretsteven@monmail.fr"), firstResult);

    }

    @Test
    public void given_date_filter_is_out_of_all_meetings_should_display_empty_list() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.dateFilterValue(LocalDate.of(2020, 05, 05));
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(0, result.size());

    }

    @Test
    public void given_date_filter_is_meeting_id_0_should_display_1_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.dateFilterValue(LocalDate.of(2021, 05, 12));
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(1, result.size());

    }

    @Test
    public void given_date_filter_is_meeting_id_1_should_display_1_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.dateFilterValue(LocalDate.of(2021, 11, 02));
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(1, result.size());

    }

    @Test
    public void given_reset_filter_after_date_filter_should_display_4_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.dateFilterValue(LocalDate.of(2021, 05, 12));
        mMainViewModel.resetFilters();
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(4, result.size());

    }

    @Test
    public void given_reset_filter_after_room_filter_should_display_4_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.roomFilterValue(Room.ROOM_BOO);
        mMainViewModel.resetFilters();
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(4, result.size());

    }

    @Test
    public void given_date_and_room_filters_then_reset_should_display_all_meetings() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.dateFilterValue(LocalDate.of(2021, 11, 02));
        mMainViewModel.roomFilterValue(Room.ROOM_GOOMBA);
        mMainViewModel.resetFilters();
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(4, result.size());

    }

    @Test
    public void given_meeting_to_delete_should_display_3_meetings() throws InterruptedException {

        // When
        mMainViewModel.deleteMeeting(0);

        // Then
        Mockito.verify(mMeetingsRepository).deleteMeeting(0);

    }

    // IN
    private List<Meeting> getDefaultMeetings() {
        return Arrays.asList(
                new Meeting(0, "Reunion A", Room.ROOM_MARIO, LocalTime.of(15, 10), LocalTime.of(16, 50), LocalDate.of(2021, 05, 12), Room.ROOM_MARIO, Arrays.asList(
                        new Email("stephane@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(1, "Reunion B", Room.ROOM_PEACH, LocalTime.of(9, 00), LocalTime.of(11, 8), LocalDate.of(2021, 11, 02), Room.ROOM_PEACH, Arrays.asList(
                        new Email("warin@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(2, "Reunion C", Room.ROOM_GOOMBA, LocalTime.of(11, 8), LocalTime.of(12, 35), LocalDate.of(2021, 06, 15), Room.ROOM_GOOMBA, Arrays.asList(
                        new Email("philibert@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(3, "Reunion D", Room.ROOM_BOO, LocalTime.of(16, 50), LocalTime.of(17, 58), LocalDate.of(2021, 10, 02), Room.ROOM_BOO, Arrays.asList(
                        new Email("krabulbe@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                ))
        );
    }

    private List<Meeting> getThreeMeetings() {
        return Arrays.asList(
                new Meeting(0, "Reunion A", Room.ROOM_MARIO, LocalTime.of(15, 10), LocalTime.of(16, 50), LocalDate.of(2021, 05, 12), Room.ROOM_MARIO, Arrays.asList(
                        new Email("stephane@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(1, "Reunion B", Room.ROOM_PEACH, LocalTime.of(9, 00), LocalTime.of(11, 8), LocalDate.of(2021, 05, 12), Room.ROOM_PEACH, Arrays.asList(
                        new Email("warin@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(2, "Reunion c", Room.ROOM_GOOMBA, LocalTime.of(11, 8), LocalTime.of(17, 58), LocalDate.of(2021, 06, 15), Room.ROOM_GOOMBA, Arrays.asList(
                        new Email("philibert@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                ))
        );
    }

    // OUT
    private List<MainViewState> getDefaultMainViewState() {
        return  Arrays.asList(
                new MainViewState(0, "2021-05-12", "Reunion A - 15:10 - Mario", "Mario", R.color.mario, "stephane@monmail.fr - peteretsteven@monmail.fr"
                ),
                new MainViewState(1, "2021-11-02", "Reunion B - 09:00 - Peach", "Peach", R.color.peach, "warin@monmail.fr - peteretsteven@monmail.fr"
                ),
                new MainViewState(2, "2021-06-15", "Reunion C - 11:08 - Goomba", "Goomba", R.color.goomba, "philibert@monmail.fr - peteretsteven@monmail.fr"
                ),
                new MainViewState(3, "2021-10-02", "Reunion D - 16:50 - Boo", "Boo", R.color.boo, "krabulbe@monmail.fr - peteretsteven@monmail.fr"
                )

        );
    }
}