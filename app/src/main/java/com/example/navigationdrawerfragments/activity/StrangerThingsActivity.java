package com.example.navigationdrawerfragments.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawerfragments.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StrangerThingsActivity extends AppCompatActivity {

    Button backBtn;
    Button playTrailer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_strangerthings);

        backBtn = findViewById(R.id.btnBackStranger);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}
