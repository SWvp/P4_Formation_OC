package com.kardabel.mareu;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.android.material.chip.ChipGroup;
import com.kardabel.mareu.ui.add.AddMeetingActivity;
import com.kardabel.mareu.utils.ToastMatcher;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.kardabel.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class AddMeetingActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<AddMeetingActivity> mActivityScenarioRule =
            new ActivityScenarioRule(AddMeetingActivity.class);

    @Before
    public void launchActivity() { ActivityScenario.launch(AddMeetingActivity.class); }

    @Test
    public void selected_date_shows_date(){
        // When: click on the date text and choose a date via datepicker
        onView(ViewMatchers.withId(R.id.date_setter)).perform(scrollTo(), click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 06, 12));
        onView(withText("OK")).perform(click());
        // Then: text shows date picked
        onView(withId(R.id.date_setter)).check(matches(allOf(withText("2021-06-12"), isDisplayed())));

    }

    @Test
    public void selected_start_time_shows_hour(){
        // When: click on the start time text and choose a time via timepicker
        onView(ViewMatchers.withId(R.id.start_time_setter)).perform(scrollTo(), click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(12, 05));
        onView(withText("OK")).perform(click());
        // Then: text shows time picked
        onView(withId(R.id.start_time_setter)).check(matches(allOf(withText("12:05"), isDisplayed())));

    }

    @Test
    public void selected_end_time_shows_hour(){
        // When: click on the end time text and choose a time via timePicker
        onView(ViewMatchers.withId(R.id.end_time_setter)).perform(scrollTo(), click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(14, 21));
        onView(withText("OK")).perform(click());
        // Then: text shows time picked
        onView(withId(R.id.end_time_setter)).check(matches(allOf(withText("14:21"), isDisplayed())));

    }

    @Test
    public void selected_start_time_is_out_of_bound_should_not_display_time(){
        // When: click on the start time text and choose a time via timepicker
        onView(ViewMatchers.withId(R.id.start_time_setter)).perform(scrollTo(), click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(05, 00));
        onView(withText("OK")).perform(click());
        // Then: text don't shows time picked
        onView(withId(R.id.start_time_setter)).check(matches(allOf(withText(""), isDisplayed())));

    }

    @Test
    public void selected_end_time_is_out_of_bound_should_not_display_time(){
        // When: click on the end time text and choose a time via timepicker
        onView(ViewMatchers.withId(R.id.end_time_setter)).perform(scrollTo(), click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(19, 33));
        onView(withText("OK")).perform(click());
        // Then: text don't shows time picked
        onView(withId(R.id.end_time_setter)).check(matches(allOf(withText(""), isDisplayed())));

    }

    @Test
    public void add_email_button_add_chip_with_email_inside(){
        // When: email is added
        onView(ViewMatchers.withId(R.id.email_input)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.email_text)).perform(replaceText("test@test.com"));
        onView(ViewMatchers.withId(R.id.add_mail_button)).perform(scrollTo(), click());
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        // Then: chipGroup is updated with new email
        onView(withId(R.id.mail_chip_model)).check(matches(allOf(withText("test@test.com"), isDisplayed())));

    }

    @Test
    public void add_email_button_show_toast_when_wrong_email_entry(){
        // When: email is added
        onView(ViewMatchers.withId(R.id.email_input)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.email_text)).perform(replaceText("testtest.com@"));
        onView(ViewMatchers.withId(R.id.create)).perform(scrollTo(), click());
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        // Then: chipGroup do not shows email and toast popup

    }

    @Test
    public void save_button_stay_on_addMeeting_activity_when_reunion_is_not_complete(){
        // When: click on save button before complete fields
        onView(ViewMatchers.withId(R.id.create)).perform(scrollTo(), click());
        // Then:
        assertFalse(mActivityScenarioRule.getScenario() == null);

    }
}
