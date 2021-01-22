package com.example.beach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.button);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "Ваше путешествие заказано", Toast.LENGTH_SHORT).show();
        Snackbar.make(findViewById(R.id.coordinator), "Ваше путешествие заказано", Snackbar.LENGTH_LONG).show();
    }
}