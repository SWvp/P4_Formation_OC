package com.kardabel.mareu;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kardabel.mareu.ui.list.MainActivity;
import com.kardabel.mareu.utils.DeleteViewAction;
import com.kardabel.mareu.utils.ItemViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.kardabel.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    private int ITEMS_COUNT = 4;

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void launchActivity() { ActivityScenario.launch(MainActivity.class); }

    @Test
    public void click_on_recyclerview_item_should_display_details_activity(){
        // Given: check if 4 meetings on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When: click on recyclerview item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new ItemViewAction()));
        // Then: got to details activity
        onView(ViewMatchers.withId(R.id.detail_room_avatar)).check(matches(isDisplayed()));

    }

    @Test
    public void recyclerview_delete_action_should_remove_one_meeting() {
        // Given: check if 4 meetings on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When: click on delete button on item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then: one meeting less on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 1));

    }

    @Test
    public void choose_a_room_menu_should_display_filtered_list(){
        // Given: check if now 3 meetings on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 1));
        // When: click on specific room
        onView(withId(R.id.filters)).perform(click());
        onView(withText("Choose a room")).perform(click());
        onView(withText("Mario")).perform(click());
        // Then: got filtered list with room choice only
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(1));

    }

    @Test
    public void click_on_FAB_should_display_add_meeting_activity(){
        // When: click on the fab button
        onView(ViewMatchers.withId(R.id.add_meeting_button)).perform(click());
        // Then: Go to addMeeting activity
        onView(ViewMatchers.withId(R.id.add_meeting)).check(matches(isDisplayed()));

    }

    @Test
    public void save_button_should_shows_main_activity_with_new_meeting_if_meeting_fields_complete(){
        // Check number of item (we delete one before)
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 1));
        // Navigate to add meeting activity
        onView(ViewMatchers.withId(R.id.add_meeting_button)).perform(click());
        // When: click on the fab button and check all fields with correct data
        onView(ViewMatchers.withId(R.id.meeting_name_input)).perform(replaceText("test 1"));
        onView(ViewMatchers.withId(R.id.dropdown_autocomplete)).perform(replaceText("Luigi"));
        onView(ViewMatchers.withId(R.id.date_setter)).perform(scrollTo(), click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2022, 10, 3));
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(R.id.start_time_setter)).perform(scrollTo(), click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(8, 05));
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(R.id.end_time_setter)).perform(scrollTo(), click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(10, 55));
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(R.id.email_input)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.email_text)).perform(replaceText("test@test.com"));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.add_mail_button)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.create)).perform(click());
        // Then: 5 meetings on main view
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));

    }

    @Test
    public void reset_filters_should_display_all_meetings(){
        // Given: check if 4 meetings on board (one was delete, and then one was added)
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When: click on specific room
        onView(withId(R.id.filters)).perform(click());
        onView(withText("Choose a room")).perform(click());
        onView(withText("Boo")).perform(click());
        // Then: we got filtered list
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 3));
        // and finally reset filters
        onView(withId(R.id.filters)).perform(click());
        onView(withText("RESET FILTERS")).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));

    }

    @Test
    public void choose_a_date_menu_should_display_filtered_list(){
        // Given: check if  4 meetings on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When: click on specific date
        onView(withId(R.id.filters)).perform(click());
        onView(withText("Choose a date")).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 06, 15));
        onView(withText("OK")).perform(click());
        // Then: got filtered list
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(1));

    }

    @Test
    public void recyclerview_should_now_display_four_meetings() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));

    }
}
