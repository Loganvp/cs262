package edu.calvin.lv33.Lab05;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
/*
@author: Logan
Description: this is for lab5
 */


// Main class
public class Lab05 extends AppCompatActivity {

    private SharedPreferences prefs;
    private Boolean rememberPref = false;

    // Oncreate sets up things for when the app opens
    // Override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab05);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        showPreferences();
    }

    // showPreferences is called when onCreate is initiated so it can show if the preferences were changed.
    private void showPreferences() {
        TextView preferenceTextView = (TextView) findViewById(R.id.textChange);
        preferenceTextView.setText("Did cool things happen? : " + prefs.getBoolean("preference", false));
    }

    // Initiates the menu to show the About and Settings page
    // Override onCreateOptionsMenu method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(edu.calvin.lv33.Lab05.R.menu.optionmenu, menu);
        return true;
    }

    // This switch/case function decides what activity to go to when a user selects
    // Override onOptionsItemSelected method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            // Go to other activity if selected
            case edu.calvin.lv33.Lab05.R.id.goToAbout:
                startActivity(new Intent(Lab05.this, Lab05about.class));
                return true;
            case edu.calvin.lv33.Lab05.R.id.goToSettings:
                startActivity(new Intent(Lab05.this, Lab05settings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // this keeps the preference while the app is open
    @Override
    public void onPause() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("preference", rememberPref);
        editor.commit();
        super.onPause();
    }

    // this will reset the preference to false when re opened
    @Override
    public void onResume() {
        super.onResume();
        rememberPref = prefs.getBoolean("preference", false);
        showPreferences();
    }
}