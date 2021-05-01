package com.kardabel.mareu.ui.list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.kardabel.mareu.model.Email;
import com.kardabel.mareu.model.Meeting;
import com.kardabel.mareu.model.Room;
import com.kardabel.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.ui.add.AddMeetingViewModel;
import com.kardabel.mareu.ui.add.AddMeetingViewState;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class AddMeetingViewModelTest {

    @Rule
    public final InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private final MeetingsRepository mMeetingsRepository = Mockito.mock(MeetingsRepository.class);

    private final MutableLiveData<List<Meeting>> meetingsMutableLiveData = new MutableLiveData<>();

    private AddMeetingViewModel mAddMeetingViewModel;

    @Before
    public void setUp() {
        Mockito.doReturn(meetingsMutableLiveData).when(mMeetingsRepository).getMeetingsList();

        mAddMeetingViewModel = new AddMeetingViewModel(mMeetingsRepository);
    }

    @Test
    public void nominal_case() throws InterruptedException {

    }

    private List<Meeting> getDefaultMeetings() {
        return Arrays.asList(
                new Meeting(0, "Reuninon A", Room.ROOM_MARIO, LocalTime.of(15, 10),LocalTime.of(15, 10), LocalDate.of(2021, 05, 12), Room.ROOM_MARIO, Arrays.asList(
                        new Email("stephane@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(1, "Reuninon B", Room.ROOM_PEACH, LocalTime.of(9, 00),LocalTime.of(15, 10), LocalDate.of(2021, 11, 02), Room.ROOM_PEACH, Arrays.asList(
                        new Email("warin@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(2, "Reuninon c", Room.ROOM_GOOMBA, LocalTime.of(11, 8), LocalTime.of(15, 10),LocalDate.of(2021, 06, 15), Room.ROOM_GOOMBA, Arrays.asList(
                        new Email("philibert@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                )),
                new Meeting(3, "Reuninon D", Room.ROOM_BOO, LocalTime.of(16, 50), LocalTime.of(15, 10),LocalDate.of(2021, 10, 02), Room.ROOM_BOO, Arrays.asList(
                        new Email("krabulbe@monmail.fr"),
                        new Email("peteretsteven@monmail.fr")
                ))
        );
    }
}
