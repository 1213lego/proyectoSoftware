package com.example.asd.instafood.UI;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.asd.instafood.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class hjgj {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_CONTACTS");

    @Test
    public void hjgj() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnIniciarSesion),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.txtEmail),
                        childAtPosition(
                                allOf(withId(R.id.linearLayoutLogin),
                                        childAtPosition(
                                                withId(R.id.linearLayout),
                                                2)),
                                0),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(click());

        ViewInteraction appCompatAutoCompleteTextView2 = onView(
                allOf(withId(R.id.txtEmail),
                        childAtPosition(
                                allOf(withId(R.id.linearLayoutLogin),
                                        childAtPosition(
                                                withId(R.id.linearLayout),
                                                2)),
                                0),
                        isDisplayed()));
        appCompatAutoCompleteTextView2.perform(click());

        ViewInteraction appCompatAutoCompleteTextView3 = onView(
                allOf(withId(R.id.txtEmail),
                        childAtPosition(
                                allOf(withId(R.id.linearLayoutLogin),
                                        childAtPosition(
                                                withId(R.id.linearLayout),
                                                2)),
                                0),
                        isDisplayed()));
        appCompatAutoCompleteTextView3.perform(replaceText("usuario1@usuario1.com"), closeSoftKeyboard());

        ViewInteraction appCompatAutoCompleteTextView4 = onView(
                allOf(withId(R.id.txtEmail), withText("usuario1@usuario1.com"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayoutLogin),
                                        childAtPosition(
                                                withId(R.id.linearLayout),
                                                2)),
                                0),
                        isDisplayed()));
        appCompatAutoCompleteTextView4.perform(click());

        pressBack();

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.txtPassword),
                        childAtPosition(
                                allOf(withId(R.id.linearLayoutLogin),
                                        childAtPosition(
                                                withId(R.id.linearLayout),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("usuario1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnIniciarSesion),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView3.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView4.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView5.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView6.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView7.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView8.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction recyclerView9 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView9.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView10 = onView(
                allOf(withId(R.id.recyclerViewResFavoritos),
                        childAtPosition(
                                withId(R.id.layoutRestauranteFavoritos),
                                1)));
        recyclerView10.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnBuscarRestaurantes), withText("¡TENGO HAMBRE!"),
                        childAtPosition(
                                allOf(withId(R.id.layoutBuscarRestaurantes),
                                        childAtPosition(
                                                withId(R.id.layoutAll),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
