package com.kardabel.mareu.ui.list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.kardabel.mareu.repository.MeetingsRepository;
import com.kardabel.mareu.ui.add.utils.AddMeetingViewAction;
import com.kardabel.mareu.ui.add.AddMeetingViewModel;
import com.kardabel.mareu.ui.list.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class AddMeetingViewModelTest {

    private AddMeetingViewModel mAddMeetingViewModel;

    @Rule
    public final InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private final MeetingsRepository mMeetingsRepository = Mockito.mock(MeetingsRepository.class);

    @Before
    public void setUp() {
        mAddMeetingViewModel = new AddMeetingViewModel(mMeetingsRepository);
    }

    @Test
    public void picked_startTime_in_right_range_should_display_startTime() throws InterruptedException {
        // When
        mAddMeetingViewModel.onStartTimeSet(10,55);

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());
        // Then
        assertEquals(AddMeetingViewAction.DISPLAY_START_HOUR, result);

    }

    @Test
    public void picked_endTime_in_right_range_should_display_startTime() throws InterruptedException {
        // When
        mAddMeetingViewModel.onEndTimeSet(15,32);

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());
        // Then
        assertEquals(AddMeetingViewAction.DISPLAY_END_HOUR, result);

    }

    @Test
    public void picked_startTime_out_of_range_should_display_toast_error() throws InterruptedException {
        // When
        mAddMeetingViewModel.onStartTimeSet(07,05);

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());
        // Then
        assertEquals(AddMeetingViewAction.DISPLAY_START_OOB, result);

    }

    @Test
    public void picked_endTime_out_of_range_should_display_toast_error() throws InterruptedException {
        // When
        mAddMeetingViewModel.onEndTimeSet(22, 10);

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());
        // Then
        assertEquals(AddMeetingViewAction.DISPLAY_END_OOB, result);

    }

    @Test
    public void picked_startTime_after_endTime_should_display_error() throws InterruptedException {
        // When
        mAddMeetingViewModel.onEndTimeSet(15,00);
        mAddMeetingViewModel.onStartTimeSet(15,01);

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());
        // Then
        assertEquals(AddMeetingViewAction.DISPLAY_START_AFTER_ERROR, result);

    }

    @Test
    public void picked_endTime_before_startTime_should_display_error() throws InterruptedException {
        // When
        mAddMeetingViewModel.onStartTimeSet(16,50);
        mAddMeetingViewModel.onEndTimeSet(16,28);

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());
        // Then
        assertEquals(AddMeetingViewAction.DISPLAY_END_BEFORE_ERROR, result);

    }

    @Test
    public void picked_date_in_right_range_should_display_date() throws InterruptedException {
        // When
        mAddMeetingViewModel.onDateSet(2021, 12, 25);

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());
        // Then
        assertEquals(AddMeetingViewAction.DISPLAY_DATE, result);

    }

    @Test
    public void picked_date_out_of_range_should_display_error() throws InterruptedException {
        // When
        mAddMeetingViewModel.onDateSet(2020, 12, 25);

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());
        // Then
        assertEquals(AddMeetingViewAction.DATE_ERROR, result);

    }

    @Test
    public void on_save_click_button_if_complete_meeting_should_add_new_meeting() throws InterruptedException {
        // Feed the fields
        mAddMeetingViewModel.addMeetingName("Reunion A");
        mAddMeetingViewModel.addMeetingRoom("Peach");
        mAddMeetingViewModel.onStartTimeSet(15, 5);
        mAddMeetingViewModel.onEndTimeSet(17, 25);
        mAddMeetingViewModel.onDateSet(2021,05,23);
        mAddMeetingViewModel.addEmails("alloa@test.mail");

        mAddMeetingViewModel.onSaveButtonClick();

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());

        assertEquals(AddMeetingViewAction.FINISH_ACTIVITY, result);

    }

    @Test
    public void on_save_click_button_if_incomplete_meeting_should_display_error() throws InterruptedException {
        // Feed the fields
        mAddMeetingViewModel.addMeetingRoom("Peach");
        mAddMeetingViewModel.onStartTimeSet(15, 5);
        mAddMeetingViewModel.onEndTimeSet(17, 25);
        mAddMeetingViewModel.onDateSet(2021,05,23);
        mAddMeetingViewModel.addEmails("alloa@test.mail");

        mAddMeetingViewModel.onSaveButtonClick();

        AddMeetingViewAction result = LiveDataTestUtils.getOrAwaitValue(mAddMeetingViewModel.getViewActionSingleLiveEvent());

        assertEquals(AddMeetingViewAction.DISPLAY_FIELDS_ERROR, result);

    }
}
