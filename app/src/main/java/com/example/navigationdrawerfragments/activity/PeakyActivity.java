package com.example.navigationdrawerfragments.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawerfragments.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PeakyActivity extends AppCompatActivity {

    Button backBtn;
    Button playTrailer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_peaky);
        backBtn = findViewById(R.id.btnBackPeaky);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
    }

