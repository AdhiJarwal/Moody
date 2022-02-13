package com.example.moody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.moody.ButtonsActivity.AngryActivity;
import com.example.moody.ButtonsActivity.HappyActivity;
import com.example.moody.ButtonsActivity.OtherActivity;
import com.example.moody.ButtonsActivity.SadActivity;
import com.example.moody.databinding.ActivitySelectMoodBinding;

public class SelectMoodActivity extends AppCompatActivity {

    ActivitySelectMoodBinding binding;
    public static String Happy_remove="H";
    public static String Sad_remove="S";
    public static String Angry_remove="A";
    public static String Other_remove="O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectMoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.happyMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectMoodActivity.this, HappyActivity.class);
                startActivity(intent);

            }
        });

        binding.sadMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectMoodActivity.this, SadActivity.class);
                startActivity(intent);

            }
        });

        binding.angryMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SelectMoodActivity.this, AngryActivity.class);
                startActivity(intent);

            }
        });

        binding.otherMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectMoodActivity.this, OtherActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch ((item.getItemId()))
        {
            case (R.id.settings):
                Intent intent1 = new Intent(SelectMoodActivity.this,AboutUsActivity.class);
                startActivity(intent1);
                break;

            case (R.id.logout):

                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                Intent intent2 = new Intent(SelectMoodActivity.this,MainActivity.class);
                startActivity(intent2);
                break;

        }
        return true;
    }
}