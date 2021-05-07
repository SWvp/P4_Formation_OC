package com.kardabel.mareu.ui.list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.ui.details.DetailsViewModel;
import com.kardabel.mareu.ui.details.DetailsViewState;
import com.kardabel.mareu.ui.list.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DetailsViewModelTest {

    @Rule
    public final InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private final MeetingsRepository mMeetingsRepository = Mockito.mock(MeetingsRepository.class);

    private final MutableLiveData<List<Meeting>> meetingsMutableLiveData = new MutableLiveData<>();

    private DetailsViewModel mDetailsViewModel;

    @Before
    public void setUp() {
        Mockito.doReturn(meetingsMutableLiveData).when(mMeetingsRepository).getMeetingsList();

        mDetailsViewModel = new DetailsViewModel(mMeetingsRepository);
    }

    @Test
    public void nominal_case() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals(result, null);

    }

    @Test
    public void given_meeting_id_is_0_should_display_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(0);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( 0, result.getDetailsMeetingId());

    }

    @Test
    public void given_meeting_id_is_2_should_display_meeting() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(2);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( 2, result.getDetailsMeetingId());

    }

    @Test
    public void given_meeting_id_is_3_should_display_string_hour() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(3);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( "16:50 to 15:10", result.getDetailsStartTime());

    }

    @Test
    public void given_meeting_id_is_2_should_display_string_date() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(2);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( "2021-06-15", result.getDetailsDate());

    }

    @Test
    public void given_meeting_id_is_0_should_display_room_name() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(0);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( "Mario", result.getDetailsRoomName());

    }

    @Test
    public void given_meeting_id_is_2_should_display_room_name() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(2);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( "Peach", result.getDetailsRoomName());

    }

    @Test
    public void given_meeting_id_is_3_should_display_meeting_name() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(3);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( "Reunion D", result.getDetailsMeetingName());

    }

    @Test
    public void given_meeting_id_is_1_should_display_meeting_name() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(1);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( "Reunion A", result.getDetailsMeetingName());

    }

    @Test
    public void given_meeting_id_is_2_should_display_2_chip() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(2);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( Arrays.asList(
                ("philibert@monmail.fr"),
                ("peteretsteven@monmail.fr")),
                result.getDetailsEmails());

    }

    @Test
    public void given_meeting_id_is_2_should_display_3_chip() throws InterruptedException {
        // Given
        meetingsMutableLiveData.setValue(getDefaultMeetings());

        // When
        mDetailsViewModel.init(3);
        DetailsViewState result = LiveDataTestUtils.getOrAwaitValue(mDetailsViewModel.getDetailsLiveData());

        // Then
        assertEquals( Arrays.asList(
                ("krabulbe@monmail.fr"),
                ("peteretsteven@monmail.fr"),
                ("unclebob@monmail.fr")),
                result.getDetailsEmails());

    }

    private List<Meeting> getDefaultMeetings() {
        return Arrays.asList(
                new Meeting(0, "Reunion A", Room.ROOM_MARIO, LocalTime.of(15, 10), LocalTime.of(15, 10),LocalDate.of(2021, 05, 12), Room.ROOM_MARIO, Arrays.asList(
                        new Email("stephane@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(1, "Reunion B", Room.ROOM_PEACH, LocalTime.of(9, 00), LocalTime.of(15, 10),LocalDate.of(2021, 11, 02), Room.ROOM_PEACH, Arrays.asList(
                        new Email("warin@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(2, "Reunion c", Room.ROOM_GOOMBA, LocalTime.of(11, 8), LocalTime.of(15, 10),LocalDate.of(2021, 06, 15), Room.ROOM_GOOMBA, Arrays.asList(
                        new Email("philibert@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(3, "Reunion D", Room.ROOM_BOO, LocalTime.of(16, 50), LocalTime.of(15, 10),LocalDate.of(2021, 10, 02), Room.ROOM_BOO, Arrays.asList(
                        new Email("krabulbe@monmail.fr"),
                        new Email("peteretsteven@monmail.fr"),
                        new Email("unclebob@monmail.fr")

                        ))
        );
    }
}
