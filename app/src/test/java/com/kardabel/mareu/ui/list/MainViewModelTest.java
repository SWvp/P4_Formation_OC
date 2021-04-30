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
    }

    @Test
    public void given_room_filter_is_mario_should_contains_mario_avatar() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.roomFilterValue(Room.ROOM_MARIO);
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(true, result.contains(R.drawable.mario));
    }

    @Test
    public void given_room_filter_is_peach_should_display_peach() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.roomFilterValue(Room.ROOM_PEACH);
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(getPeach(), result);
    }

    @Test
    public void given_room_filter_is_goomba_should_display_1_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.roomFilterValue(Room.ROOM_GOOMBA);
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(1, result.size());
    }

    @Test
    public void given_date_filter_out_of_all_meetings_should_display_empty_list() throws InterruptedException {
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
    public void given_list_of_email_should_display_list_of_string() throws InterruptedException {
//      // Given
//      meetingsMutableLiveData.setValue(getDefaultMeetings());

//      // When
//      mMainViewModel.dateFilterValue(LocalDate.of(2021, 05, 12));
//      List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

//      // Then
//      assertEquals("stephane@monmail.fr - peteretsteven@monmail.fr", result);
    }

    @Test
    public void given_reset_filter_should_display_4_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.dateFilterValue(LocalDate.of(2021, 05, 12));
        mMainViewModel.resetFilter(true);
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(4, result.size());
    }

    @Test
    public void given_meeting_to_delete_should_display_3_meetings() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mMainViewModel.deleteMeeting(0);
        List<MainViewState> result = LiveDataTestUtils.getOrAwaitValue(mMainViewModel.getMeetingsListLiveData());

        // Then
        assertEquals(3, result.size());
    }

    // IN
    private List<Meeting> getDefaultMeetings() {
        return Arrays.asList(
                new Meeting(0, "Reuninon A", Room.ROOM_MARIO, LocalTime.of(15, 10), LocalTime.of(16, 50), LocalDate.of(2021, 05, 12), Room.ROOM_MARIO, Arrays.asList(
                        new Email("stephane@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(1, "Reuninon B", Room.ROOM_PEACH, LocalTime.of(9, 00), LocalTime.of(11, 8), LocalDate.of(2021, 11, 02), Room.ROOM_PEACH, Arrays.asList(
                        new Email("warin@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(2, "Reuninon c", Room.ROOM_GOOMBA, LocalTime.of(11, 8), LocalTime.of(12, 35), LocalDate.of(2021, 06, 15), Room.ROOM_GOOMBA, Arrays.asList(
                        new Email("philibert@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(3, "Reuninon D", Room.ROOM_BOO, LocalTime.of(16, 50), LocalTime.of(17, 58), LocalDate.of(2021, 10, 02), Room.ROOM_BOO, Arrays.asList(
                        new Email("krabulbe@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                ))
        );
    }

    private List<Meeting> getThreeMeetings() {
        return Arrays.asList(
                new Meeting(0, "Reuninon A", Room.ROOM_MARIO, LocalTime.of(15, 10), LocalTime.of(16, 50), LocalDate.of(2021, 05, 12), Room.ROOM_MARIO, Arrays.asList(
                        new Email("stephane@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(1, "Reuninon B", Room.ROOM_PEACH, LocalTime.of(9, 00), LocalTime.of(11, 8), LocalDate.of(2021, 05, 12), Room.ROOM_PEACH, Arrays.asList(
                        new Email("warin@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(2, "Reuninon c", Room.ROOM_GOOMBA, LocalTime.of(11, 8), LocalTime.of(17, 58), LocalDate.of(2021, 06, 15), Room.ROOM_GOOMBA, Arrays.asList(
                        new Email("philibert@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                ))
        );
    }

    // OUT
    private List<MainViewState> getDefaultMainViewState() {
        return  Arrays.asList(
                new MainViewState(0, "15:10", "Reuninon A", "Mario", R.drawable.mario, "stephane@monmail.fr - peteretsteven@monmail.fr"
                ),
                new MainViewState(1, "09:00", "Reuninon B", "Peach", R.drawable.peach, "warin@monmail.fr - peteretsteven@monmail.fr"
                ),
                new MainViewState(2, "11:08", "Reuninon C", "Goomba", R.drawable.goomba, "philibert@monmail.fr - peteretsteven@monmail.fr"
                ),
                new MainViewState(3, "16:50", "Reuninon D", "Boo", R.drawable.boo, "krabulbe@monmail.fr - peteretsteven@monmail.fr"
                )

        );
    }

    private List<MainViewState> getPeach() {
        return  Arrays.asList(
                new MainViewState(1, "09:00", "Reuninon B", "Peach", R.drawable.peach, "warin@monmail.fr - peteretsteven@monmail.fr"
                )

        );
    }
}