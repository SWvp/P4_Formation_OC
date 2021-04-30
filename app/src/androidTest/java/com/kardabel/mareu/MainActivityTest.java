package com.kardabel.mareu;

import android.widget.DatePicker;

import androidx.test.core.app.ActivityScenario;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.kardabel.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity mMainActivity;
    private int ITEMS_COUNT = 4;

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void launchActivity() { ActivityScenario.launch(MainActivity.class); }

    @Test
    public void recycleview_should_display_four_meetings() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(matches(hasMinimumChildCount(4)));

    }

    @Test
    public void recycleview_delete_action_should_remove_one_meeting() {
        // Given: check if 4 meetings on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When: click on delete button on item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then: one meeting less on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 1));

    }

    @Test
    public void choose_a_date_menu_should_display_filtered_date_list(){
        // Given: check if  4 meetings on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When: click on specific date
        onView(withId(R.id.filters)).perform(click());
        onView(withText("Choose a date")).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 06, 12));
        onView(withText("OK")).perform(click());
        // Then: got filtered list
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 3));


    }

    @Test
    public void choose_a_room_menu_should_display_filtered_room_list(){
        // Given: check if 4 meetings on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When: click on specific room
        onView(withId(R.id.filters)).perform(click());
        onView(withText("Choose a room")).perform(click());
        onView(withText("Peach")).perform(click());
        // Then: got filtered list with room choice only
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 3));


    }

    @Test
    public void reset_filters_should_display_all_meetings(){
        // Given: check if 4 meetings on board
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
    public void clik_on_item_should_display_details_activity(){
        // Given: check if 4 meetings on board
        onView(ViewMatchers.withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When: click on recyclerview item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new ItemViewAction()));
        // Then: got to details activity
        onView(ViewMatchers.withId(R.id.meeting_details)).check(matches(isDisplayed()));

    }

    @Test
    public void clik_on_FAB_should_display_add_meeting_activity(){
        // When: click on the fab button
        onView(ViewMatchers.withId(R.id.add_meeting_button)).perform(click());
        // Then: Go to addMeeting activity
        onView(ViewMatchers.withId(R.id.add_meeting)).check(matches(isDisplayed()));

    }


}
