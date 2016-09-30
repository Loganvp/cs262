package edu.lv33calvin.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    EditText edit1 = (EditText) findViewById(R.id.edit1);
    EditText edit2 = (EditText) findViewById(R.id.edit2);
    EditText answers = (EditText) findViewById(R.id.answers);
    Button button1 = (Button) findViewById(R.id.button1);

    final Button button1 = (Button) findViewById(R.id.button1);
    button1.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            int num1 = Integer.parseFloat(edit1.getText().toString());
            int num2 = Integer.parseFloat(edit2.getText().toString());

            answers.setText(num1 + num2);
        }
    });
}
