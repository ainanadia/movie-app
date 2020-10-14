package com.example.navigationdrawerfragments.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawerfragments.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DevilAllTheTimeActivity extends AppCompatActivity {

    Button backBtn;
    Button playTrailer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devil);

        backBtn = findViewById(R.id.btnBackDevil);
        playTrailer = findViewById(R.id.btnPlayDevil);

        playTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DevilAllTheTimeActivity.this, DevilAllTheTimeActivity.class);
                startActivity(intent);
                return;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
