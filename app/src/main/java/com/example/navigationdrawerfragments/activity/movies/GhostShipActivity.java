package com.example.navigationdrawerfragments.activity.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.activity.playtrailer.PlayTrailerActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GhostShipActivity extends AppCompatActivity {

    Button backBtn;
    Button playTrailer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghostship);

        backBtn = findViewById(R.id.btnBackGhost);
        playTrailer = findViewById(R.id.btnPlayGhost);

        playTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GhostShipActivity.this, PlayTrailerActivity.class);
                intent.putExtra("ghost", 3);
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
