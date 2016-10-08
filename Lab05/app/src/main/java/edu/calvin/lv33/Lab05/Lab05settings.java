package edu.calvin.lv33.Lab05;

/**
 * Created by Logan
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Lab05settings extends AppCompatActivity {

    // it initializes the fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new Lab05settingsFragment()).commit();
    }
}

