package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
            Button btn2;
            TextView text;
            CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        text = findViewById(R.id.text);
        check = findViewById(R.id.check);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    text.setText("чекед");
                } else {
                    text.setText("анчекед");
                }
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn1:
                check.setChecked(true);
                text.setBackgroundColor(Color.parseColor("#FFDDFF"));
                break;
            case R.id.btn2:
                check.setChecked(false);
                text.setBackgroundColor(Color.parseColor("#DDDDFF"));
                break;
        }
    }
}