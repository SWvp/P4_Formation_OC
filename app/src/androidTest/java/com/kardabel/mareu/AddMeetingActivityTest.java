package com.kardabel.mareu;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.kardabel.mareu.ui.add.AddMeetingActivity;
import com.kardabel.mareu.ui.list.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by stéphane Warin OCR on 29/04/2021.
 */
@RunWith(JUnit4.class)
public class AddMeetingActivityTest {
    @Rule
    public ActivityScenarioRule<AddMeetingActivity> mActivityScenarioRule =
            new ActivityScenarioRule(AddMeetingActivity.class);

    @Before
    public void setup(){

    }
}
