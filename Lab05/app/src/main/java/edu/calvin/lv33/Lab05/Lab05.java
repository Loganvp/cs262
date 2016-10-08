package edu.calvin.lv33.Lab05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


// Main class
public class Lab05 extends AppCompatActivity {

    // Override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab05);
    }

    // Override onCreateOptionsMenu method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(edu.calvin.lv33.Lab05.R.menu.optionmenu, menu);
        return true;
    }

    // Override onOptionsItemSelected method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            // Go to other activity if selected
            case edu.calvin.lv33.Lab05.R.id.goToAbout:
                startActivity(new Intent(Lab05.this, Lab05about.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}