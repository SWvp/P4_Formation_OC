package com.kardabel.mareu;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kardabel.mareu.ui.list.MainActivity;
import com.kardabel.mareu.utils.ItemViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class DetailsActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule(MainActivity.class);

    @Before
    public void launchActivity() { ActivityScenario.launch(MainActivity.class); }

    @Test
    public void details_activity_display_neighbour_name(){
        // When: click on recyclerview item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new ItemViewAction()));
        // Then: display meeting name
        onView(withId(R.id.detail_meeting_name)).check(matches((withText("Reunion c"))));

    }

    @Test
    public void details_Activity_display_room_name(){
        // When: click on recyclerview item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ItemViewAction()));
        // Then: display meeting room name
        onView(withId(R.id.detail_room_name)).check(matches((withText("Mario"))));

    }

    @Test
    public void details_Activity_display_date(){
        // When: click on recyclerview item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new ItemViewAction()));
        // Then: display meeting date
        onView(withId(R.id.detail_date)).check(matches((withText("2021-05-01"))));

    }

    @Test
    public void details_Activity_display_time(){
        // When: click on recyclerview item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, new ItemViewAction()));
        // Then: display meeting time
        onView(withId(R.id.detail_hour)).check(matches((withText("16:50 to 18:00"))));

    }

    @Test
    public void details_Activity_display_time_with_0_first_number_if_inferior_to_ten(){
        // When: click on recyclerview item
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new ItemViewAction()));
        // Then: display meeting time
        onView(withId(R.id.detail_hour)).check(matches((withText("09:00 to 11:20"))));

    }
}