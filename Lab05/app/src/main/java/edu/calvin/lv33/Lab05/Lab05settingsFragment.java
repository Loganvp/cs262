package edu.calvin.lv33.Lab05;

/**
 * Created by Logan
 */

import android.os.Bundle;
import android.preference.PreferenceFragment;


public class Lab05settingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}

