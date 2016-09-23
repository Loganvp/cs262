/**
 * @Authors: Logan and Drew
 *
 */

package edu.calvin.lv33.lab02;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;


import java.text.NumberFormat;

public class Lab02 extends AppCompatActivity {

    private EditText editText;
    private TextView billAmountText;
    private Button doubleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab02);

        //doubleButton.setOnClickListener(this);
    }
    public void calculate(View v)
    {
        editText = (EditText) findViewById(R.id.editText);
        billAmountText = (TextView) findViewById(R.id.billAmountText);

        int value = Integer.parseInt(editText.getText().toString());
        billAmountText.setText(Integer.toString(value * 2));


    }


}
