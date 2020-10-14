package com.example.navigationdrawerfragments.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.navigationdrawerfragments.R;
import com.example.navigationdrawerfragments.fragment.HomeFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TheRingActivity extends AppCompatActivity {

    Button backBtn;
    Button playTrailer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thering);

        backBtn = findViewById(R.id.btnBackTheRing);
        playTrailer = findViewById(R.id.btnPlayTheRing);

        playTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                switch (view.getId()){
//                    case R.id.btnPlayTheRing:
//                        alertDialog();
//                        break;
//                }
                Intent intent = new Intent(TheRingActivity.this, PlayTrailerActivity.class);
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

//    private void alertDialog() {
//
//        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
//        dialog.setTitle("Trailer");
//        dialog.setMessage("Hi, this is a test");
//        AlertDialog alertDialog = dialog.create();
//        alertDialog.show();
//    }
}
