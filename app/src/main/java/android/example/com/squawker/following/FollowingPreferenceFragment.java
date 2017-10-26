/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package android.example.com.squawker.following;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.com.squawker.R;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;

import static android.example.com.squawker.provider.SquawkContract.ASSER_KEY;
import static android.example.com.squawker.provider.SquawkContract.CEZANNE_KEY;
import static android.example.com.squawker.provider.SquawkContract.JLIN_KEY;
import static android.example.com.squawker.provider.SquawkContract.LYLA_KEY;
import static android.example.com.squawker.provider.SquawkContract.NIKITA_KEY;
import static android.example.com.squawker.provider.SquawkContract.TEST_ACCOUNT_KEY;


/**
 * Shows the list of instructors you can follow
 */

// TODO (1) Implement onSharedPreferenceChangeListener
public class FollowingPreferenceFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener{

    private final static String LOG_TAG = FollowingPreferenceFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Add visualizer preferences, defined in the XML file in res->xml->preferences_squawker
        addPreferencesFromResource(R.xml.following_squawker);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(LOG_TAG, "sharePreferences Changed Key: " + key);

        if (sharedPreferences.getBoolean(key, true)){
            FirebaseMessaging.getInstance().subscribeToTopic(key);
            Log.d(LOG_TAG, "Subscribing to " + key);
        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(key);
            Log.d(LOG_TAG, "Un-Subscribing to " + key);
        }

    }
    // TODO (2) When a SharedPreference changes, check which preference it is and subscribe or
    // un-subscribe to the correct topics.

    // Ex. FirebaseMessaging.getInstance().subscribeToTopic("key_lyla");
    // subscribes to Lyla's squawks.

    // HINT: Checkout res->xml->following_squawker.xml. Note how the keys for each of the
    // preferences matches the topic to subscribe to for each instructor.

    // TODO (3) Make sure to register and unregister this as a Shared Preference Change listener, in
    // onCreate and onDestroy.

    public static void setupFollowings(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (sharedPreferences.getBoolean(ASSER_KEY, true)){
            FirebaseMessaging.getInstance().subscribeToTopic(ASSER_KEY);
        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(ASSER_KEY);
        }

        if (sharedPreferences.getBoolean(CEZANNE_KEY, true)){
            FirebaseMessaging.getInstance().subscribeToTopic(CEZANNE_KEY);
        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(CEZANNE_KEY);
        }

        if (sharedPreferences.getBoolean(JLIN_KEY, true)){
            FirebaseMessaging.getInstance().subscribeToTopic(JLIN_KEY);
        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(JLIN_KEY);
        }

        if (sharedPreferences.getBoolean(LYLA_KEY, true)){
            FirebaseMessaging.getInstance().subscribeToTopic(LYLA_KEY);
        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(LYLA_KEY);
        }

        if (sharedPreferences.getBoolean(NIKITA_KEY, true)){
            FirebaseMessaging.getInstance().subscribeToTopic(NIKITA_KEY);
        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(NIKITA_KEY);
        }

        if (sharedPreferences.getBoolean(TEST_ACCOUNT_KEY, true)){
            FirebaseMessaging.getInstance().subscribeToTopic(TEST_ACCOUNT_KEY);
        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(TEST_ACCOUNT_KEY);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        // the second way to get SharedPreferences
        // SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);

        super.onDestroy();

    }
}
