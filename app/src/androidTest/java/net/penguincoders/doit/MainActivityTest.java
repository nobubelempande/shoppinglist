package net.penguincoders.doit;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.assertNotNull;

import android.app.Instrumentation;
import android.content.ClipData;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;


import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;



import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mLandingTest = new
            ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity = null;
    Instrumentation.ActivityMonitor monitor =
            getInstrumentation().addMonitor(MainActivity.class.getName(), null,
                    false);

    @Before
    public void setUp(){
        mActivity = mLandingTest.getActivity();
    }

    @Ignore
    public void InputText() {
        EditText txt_search = mActivity.findViewById(R.id.newListName);
        Espresso.onView(withId(R.id.newListName)).perform(typeText(""));
        ViewActions.closeSoftKeyboard();
        Espresso.onView(withId(R.id.newTaskButton)).perform(click());
        Instrumentation.ActivityMonitor activityMonitor =
                getInstrumentation().addMonitor(itemAddingActivity.class.getName(),
                        null, false);
        assertNotNull(activityMonitor);

    }

    @Ignore
    public void checkShoppingListInput(){
        TextView Shop = mActivity.findViewById(R.id.newListName);
        String LShop = Shop.getText().toString().trim();
        assertEquals("Pick-n-Pay",LShop);
    }

    @Test
    public void CheckButtonClick(){
        //Button BtnCheck = mActivity.findViewById(R.id.fab);
        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click());

    }

    @Ignore
    public void ClickableRecycleViewer(){
        Espresso.onView(withId(R.id.tasksRecyclerView)).perform(ViewActions.click());
    }

    @Ignore
    public void OpenCustomDialog(){

        final Button btnCheck = mActivity.findViewById(R.id.fab);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCheck.isPressed()){


                }
            }
        });

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}

